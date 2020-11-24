package com.buyalskaya.fitclub.controller.command;

import java.util.EnumSet;
import java.util.Set;

import static com.buyalskaya.fitclub.controller.command.CommandType.*;

/**
 * The enum Command access control.
 * It uses to control an access for users with different roles
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public enum CommandAccessControl {
    /**
     * Administrator command access control.
     * The set of command is available for administrators
     */
    ADMINISTRATOR(EnumSet.of(
            HOME_PAGE,
            ABOUT_US_PAGE,
            CONTACT_US_PAGE,
            CONTACT_US,
            TELEGRAM_PAGE,
            INSTAGRAM_PAGE,
            VKONTAKTE_PAGE,
            CONTACTS_PAGE,
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
            MEMBERSHIP_PAGE,
            COPY_SCHEDULE
    )),

    /**
     * Instructor command access control.
     * The set of command is available for instructors
     */
    INSTRUCTOR(EnumSet.of(
            HOME_PAGE,
            ABOUT_US_PAGE,
            CONTACT_US_PAGE,
            CONTACT_US,
            CONTACTS_PAGE,
            TELEGRAM_PAGE,
            INSTAGRAM_PAGE,
            VKONTAKTE_PAGE,
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

    /**
     * Client command access control.
     * The set of command is available for clients
     */
    CLIENT(EnumSet.of(
            HOME_PAGE,
            ABOUT_US_PAGE,
            CONTACT_US_PAGE,
            CONTACT_US,
            CONTACTS_PAGE,
            TELEGRAM_PAGE,
            INSTAGRAM_PAGE,
            VKONTAKTE_PAGE,
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

    /**
     * Guest command access control.
     * The set of command is available for non-register users
     */
    GUEST(EnumSet.of(
            HOME_PAGE,
            ABOUT_US_PAGE,
            CONTACT_US_PAGE,
            CONTACT_US,
            CONTACTS_PAGE,
            TELEGRAM_PAGE,
            INSTAGRAM_PAGE,
            VKONTAKTE_PAGE,
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

    /**
     * Gets available commands.
     *
     * @return the available commands
     */
    public Set<CommandType> getAvailableCommands() {
        return availableCommands;
    }
}