package com.buyalskaya.fitclub.controller.command;

import com.buyalskaya.fitclub.controller.command.impl.DefaultCommand;

import java.util.Arrays;

public class ActionProvider {
    private ActionProvider() {
    }

    public static Command defineCommand(String commandName) {
        Command command;
        if (commandName != null) {
            command = Arrays.stream(CommandType.values())
                    .filter(c -> c.name().equalsIgnoreCase(commandName))
                    .findFirst()
                    .map(CommandType::getCommand).orElse(new DefaultCommand());
        } else {
            command = new DefaultCommand();
        }
        return command;
    }
}
