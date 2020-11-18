package com.buyalskaya.fitclub.controller.command;

import com.buyalskaya.fitclub.controller.command.impl.DefaultCommand;

import java.util.Arrays;

/**
 * The type Command provider.
 * It uses to define command
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class CommandProvider {
    private CommandProvider() {
    }

    /**
     * Define command command.
     *
     * @param commandName the command name
     * @return the command
     */
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
