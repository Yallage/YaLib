package com.rabbitown.yalib.command;

import com.rabbitown.yalib.annotation.command.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Yoooooory
 */
public class CommandProcessor {

    final PluginCommand pluginCommand;

    public CommandProcessor(PluginCommand pluginCommand) {
        this.pluginCommand = pluginCommand;
    }

    final static Pattern pattern = Pattern.compile("\\{(\\w+)(: ?(.+))?}");

    protected boolean onCommand(CommandSender sender, Command command, String label, String[] args, List<CommandHandler> handlers) {
        List<Method> before = new ArrayList<>();
        List<Method> after = new ArrayList<>();
        List<Method> actions = new ArrayList<>();
        List<Method> alternates = new ArrayList<>();
        StringBuilder sb = new StringBuilder(args.length > 0 ? args[0] : "");
        for (int i = 1; i < args.length; i++) sb.append(" ").append(args[i]);
        for (CommandHandler handler : handlers) {
            // Looking for an effective handler. (detect paths)
            if (handler.getPath().size() == 0 || handler.getPath().stream().anyMatch(s -> {
                Matcher m = Pattern.compile(s).matcher(sb.toString());
                if (m.find() && m.start() == 0) {
                    sb.replace(0, sb.length(), sb.substring(m.end()));
                    return true;
                }
                return false;
            })) {
                for (Method method : handler.getClass().getMethods()) {
                    if (method.isAnnotationPresent(Before.class)) before.add(method);
                    if (method.isAnnotationPresent(After.class)) after.add(method);
                    if (method.isAnnotationPresent(Action.class)) actions.add(method);
                    if (method.isAnnotationPresent(Alternate.class)) alternates.add(method);
                }
                before.stream().sorted(Comparator.comparingInt(s -> s.getAnnotation(Alternate.class).priority())).forEach(s -> runAction(new HashMap<>(), s, handler, sender, command, label, args));
                boolean result = executeCommand(sender, command, label, args, handler, sb.toString(), actions, alternates);
                after.stream().sorted(Comparator.comparingInt(s -> s.getAnnotation(Alternate.class).priority())).forEach(s -> runAction(new HashMap<>(), s, handler, sender, command, label, args));
                return result;
            }
        }
        return false;
    }

    private boolean executeCommand(CommandSender sender, Command command, String label, String[] args, CommandHandler handler, String fullArgs, List<Method> actions, List<Method> alternates) {
        // Path matches -> An effective handler.
        // Looking for an effective action. (detect actions)
        // Get all actions of the handler and sort them by their priority.
        // Check if matches.
        actions.sort(Comparator.comparingInt(s -> s.getAnnotation(Action.class).priority()));
        for (Method action : actions) {
            for (Action annotation : action.getAnnotationsByType(Action.class)) {
                String ac = annotation.action();
                String replaced = fullArgs;
                Matcher matcher = pattern.matcher(ac);
                while (matcher.find())
                    matcher.reset(replaced = matcher.replaceFirst(matcher.group(3) == null ? ".*" : matcher.group(3)));
                if (fullArgs.matches(replaced)) {
                    // Action regex matches -> An effective action.
                    // Check permission
                    if (CommandFactory.getCommandResult(handler, pluginCommand, annotation, sender, true).name().startsWith("FAILED"))
                        return true;
                    // Executing command.
                    // Map: Parameter, Value
                    Map<String, String> map = new HashMap<>();
                    // Get all params.
                    List<String> params = new ArrayList<>();
                    String str = fullArgs;
                    Matcher m = pattern.matcher(ac.replace("\\", "\\\\").replace("(", "\\("));
                    while (m.find()) {
                        params.add(m.group(1));
                        m.reset(str = m.replaceFirst(m.group(3) == null ? "(.*)" : "(" + m.group(3) + ")"));
                    }
                    m = Pattern.compile(str).matcher(fullArgs);
                    if (m.find())
                        for (int i = 0; i < m.groupCount(); i++) map.put(params.get(i), m.group(i + 1));
                    return runAction(map, action, handler, sender, command, label, args);
                }
            }

        }
        // No matches action, looking for a alternate.
        alternates.stream().sorted(Comparator.comparingInt(s -> s.getAnnotation(Alternate.class).priority())).forEach(s -> runAction(new HashMap<>(), s, handler, sender, command, label, args));
        return true;
    }

    private List<Object> getArguments(Map<String, String> map, Method action, CommandSender sender, Command command, String label, String[] args) {
        List<Object> arguments = new ArrayList<>();
        for (Parameter parameter : action.getParameters()) {
            String name = parameter.getName();
            if (map.containsKey(name)) {
                arguments.add(map.get(name));
                continue;
            }
            switch (name) {
                case "sender":
                    arguments.add(sender);
                    continue;
                case "command":
                    arguments.add(command);
                case "label":
                    arguments.add(label);
                case "args":
                    arguments.add(args);
            }
            arguments.add(null);
        }
        return arguments;
    }

    private boolean runAction(Map<String, String> map, Method action, CommandHandler handler, CommandSender sender, Command command, String label, String[] args) {
        List<Object> arguments = getArguments(map, action, sender, command, label, args);
        try {
            if (action.getReturnType() == boolean.class)
                return (boolean) action.invoke(handler, arguments.toArray());
            else if (action.getReturnType() == String.class)
                sender.sendMessage((String) action.invoke(handler, arguments.toArray()));
            else
                action.invoke(handler, arguments.toArray());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    protected List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args, List<CommandHandler> handlers) throws InvocationTargetException, IllegalAccessException {
        StringBuilder sb = new StringBuilder(args.length > 0 ? args[0] : "");
        for (CommandHandler handler : handlers) {
            if (handler.getPath().size() == 0 || handler.getPath().stream().anyMatch(s -> {
                Matcher m = Pattern.compile(s).matcher(sb.toString());
                if (m.find() && m.start() == 0) {
                    sb.replace(0, sb.length(), sb.substring(m.end()));
                    return true;
                }
                return false;
            })) {
                for (Method tab : Arrays.stream(handler.getClass().getMethods()).filter(s -> s.isAnnotationPresent(Tab.class) && "java.util.List<java.lang.String>".equals(s.getGenericReturnType().getTypeName())).collect(Collectors.toList())) {
                    return (List<String>) tab.invoke(handler, getArguments(new HashMap<>(), tab, sender, command, label, args).toArray());
                }
            }
        }
        return new ArrayList<>();
    }

}
