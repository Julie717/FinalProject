package com.buyalskaya.fitclub.controller.command;

import java.util.EnumSet;
import java.util.Set;

import static com.buyalskaya.fitclub.controller.command.CommandType.*;

public enum CommandAccessControl {
    ADMINISTRATOR(EnumSet.of(
            HOME_PAGE,
            CHANGE_LOCALE,
            WORKOUT_PAGE,
            LOW_WORKOUT_PAGE,
            MIDDLE_WORKOUT_PAGE,
            HIGH_WORKOUT_PAGE,
            LOGOUT,
            INSTRUCTORS_PAGE,
            SCHEDULE_PAGE,
            UPDATE_CLIENT_INFO,
            PRIVATE_CABINET_PAGE,
            CHANGE_PASSWORD,
            ADD_PHOTO,
            EDIT_SCHEDULE_PAGE,
            CHECK_PRESENCE_PAGE,
            SALE_MEMBERSHIP_PAGE,
            SALE_MEMBERSHIP,
            CLIENT_PRESENT,
            CLIENT_ABSENT,
            DELETE_WORKOUT_FROM_SCHEDULE,
            EDIT_SCHEDULE,
            ADD_SCHEDULE_PAGE,
            ADD_SCHEDULE,
            ALL_USER_PAGE,
            SEARCH_USER,
            CHANGE_USER_ROLE,
            MEMBERSHIP_PAGE
    )),

    INSTRUCTOR(EnumSet.of(
            HOME_PAGE,
            CHANGE_LOCALE,
            WORKOUT_PAGE,
            LOW_WORKOUT_PAGE,
            MIDDLE_WORKOUT_PAGE,
            HIGH_WORKOUT_PAGE,
            LOGOUT,
            INSTRUCTORS_PAGE,
            SCHEDULE_PAGE,
            UPDATE_CLIENT_INFO,
            PRIVATE_CABINET_PAGE,
            CHANGE_PASSWORD,
            ADD_PHOTO,
            MEMBERSHIP_PAGE
    )),

    CLIENT(EnumSet.of(
            HOME_PAGE,
            CHANGE_LOCALE,
            WORKOUT_PAGE,
            LOW_WORKOUT_PAGE,
            MIDDLE_WORKOUT_PAGE,
            HIGH_WORKOUT_PAGE,
            LOGOUT,
            INSTRUCTORS_PAGE,
            SCHEDULE_PAGE,
            UNSUBSCRIBE_IN_PRIVATE_CABINET,
            SUBSCRIBE_IN_SCHEDULE,
            UPDATE_CLIENT_INFO,
            PRIVATE_CABINET_PAGE,
            CHANGE_PASSWORD,
            ADD_PHOTO,
            MEMBERSHIP_PAGE
    )),

    GUEST(EnumSet.of(
            HOME_PAGE,
            CHANGE_LOCALE,
            WORKOUT_PAGE,
            LOW_WORKOUT_PAGE,
            MIDDLE_WORKOUT_PAGE,
            HIGH_WORKOUT_PAGE,
            LOGIN,
            REGISTRATION,
            CONFIRM_REGISTRATION,
            INSTRUCTORS_PAGE,
            SCHEDULE_PAGE,
            REGISTRATION_PAGE,
            MEMBERSHIP_PAGE
    ));

    private Set<CommandType> availableCommands;

    CommandAccessControl(Set<CommandType> availableCommands) {
        this.availableCommands = availableCommands;
    }

    public Set<CommandType> getAvailableCommands() {
        return availableCommands;
    }
}