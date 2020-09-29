package com.buyalskaya.fitclub.controller.command;

import com.buyalskaya.fitclub.controller.command.impl.EnterCommand;
import com.buyalskaya.fitclub.controller.command.impl.LoginCommand;
import com.buyalskaya.fitclub.controller.command.impl.LogoutCommand;

public enum CommandType {
    ENTER(new EnterCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}