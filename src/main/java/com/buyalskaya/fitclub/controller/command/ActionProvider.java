package com.buyalskaya.fitclub.controller.command;

import com.buyalskaya.fitclub.controller.command.impl.EmptyCommand;

import java.util.Arrays;

public class ActionProvider {
    private ActionProvider() {
    }

    public static Command defineCommand(String commandName) {
        Command command = new EmptyCommand();
        if (commandName != null) {
            command = Arrays.stream(CommandType.values())
                    .filter(c -> c.name().equalsIgnoreCase(commandName))
                    .findFirst()
                    .map(CommandType::getCommand).orElse(command);
        }
        return command;
    }
}
