package com.rabbitown.yalib.command;

import com.rabbitown.yalib.annotation.command.Action;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Yoooooory
 */
public class CommandProcessor {

    final static Pattern pattern = Pattern.compile("\\{(\\w+)(: ?(.+))?}");

    public static boolean executeCommand(CommandSender sender, Command command, String label, String[] args, List<CommandHandler> handlers) throws InvocationTargetException, IllegalAccessException {
        StringBuilder sb = new StringBuilder(args[0]);
        for (int i = 1; i < args.length; i++) {
            sb.append(args[i]);
        }
        // handlers -> detect paths -> detect actions -> execute command
        for (CommandHandler handler : handlers) {
            // Looking for an effective handler. (detect paths)
            if (handler.getPath().length == 0 || Stream.of(handler.getPath()).anyMatch(s -> {
                Matcher m = Pattern.compile(s).matcher(sb.toString());
                if (m.find() && m.start() == 0) {
                    sb.substring(m.end());
                    return true;
                }
                return false;
            })) {
                // Path matches -> An effective handler.
                // Looking for an effective action. (detect actions)
                // Get all actions of the handler and sort them by their priority.
                List<Method> actions = Stream.of(handler.getClass().getMethods()).filter(s -> s.getAnnotation(Action.class) != null).sorted(Comparator.comparingInt(s -> s.getAnnotation(Action.class).priority())).collect(Collectors.toList());
                // Check if matches.
                for (Method action : actions) {
                    String ac = action.getAnnotation(Action.class).action();
                    String replaced = "";
                    Matcher matcher = pattern.matcher(ac);
                    while (matcher.find())
                        matcher.reset(replaced = matcher.replaceFirst(matcher.group(3) == null ? ".*" : matcher.group(3)));
                    if (sb.toString().matches(replaced)) {
                        // Action regex matches -> An effective action.
                        // Executing command.
                        // Map: Parameter, Value
                        Map<String, String> map = new HashMap<>();
                        List<String> params = new ArrayList<>();
                        { // Get all params.
                            String str = "";
                            Matcher m = pattern.matcher(ac.replace("\\", "\\\\").replace("(", "\\("));
                            while (m.find()) {
                                params.add(m.group(1));
                                m.reset(str = m.replaceFirst(m.group(3) == null ? "(.*)" : "(" + m.group(3) + ")"));
                            }
                            m = Pattern.compile(str).matcher(sb.toString());
                            for (int i = 0; m.find(); i++) map.put(params.get(i), m.group(1));
                        }
                        List<Object> arguments = new ArrayList<>();
                        for (Parameter parameter : action.getParameters()) {
                            String name = parameter.getName();
                            if (map.containsKey(name)) {
                                arguments.add(name);
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
                        if (action.getReturnType() == boolean.class)
                            return (boolean) action.invoke(handler, arguments.toArray());
                        else
                            action.invoke(handler, arguments.toArray());
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
