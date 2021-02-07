package com.rabbitown.yalib.common.command;

import com.rabbitown.yalib.YaLib;
import com.rabbitown.yalib.module.command.SimpleCommandRemote;
import com.rabbitown.yalib.module.command.annotation.Access;
import com.rabbitown.yalib.module.command.annotation.Action;
import com.rabbitown.yalib.module.command.annotation.Path;
import com.rabbitown.yalib.util.SystemUtil;
import org.bukkit.command.CommandSender;

/**
 * @author Yoooooory
 */
@Path(path = "testJ")
@Access(permission = "yalib.admin")
public class CommandTestJ extends SimpleCommandRemote {

    public CommandTestJ() {
        super("yalib", YaLib.getInstance());
    }

    @Action(action = "test")
    public String test(CommandSender sender) {
        return "Nano time used: " + SystemUtil.measureNanoTime(() -> sender.sendMessage("Test message!"));
    }
}