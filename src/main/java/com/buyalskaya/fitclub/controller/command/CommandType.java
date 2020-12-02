package com.buyalskaya.fitclub.controller.command;

import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.impl.*;
import com.buyalskaya.fitclub.controller.command.impl.navigation.*;
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;

/**
 * The enum Command type.
 * It consists of all commands that use in this project
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public enum CommandType {
    HOME_PAGE(new HomePageCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    WORKOUT_PAGE(new WorkoutPageCommand()),
    LOW_WORKOUT_PAGE(new LowWorkoutPageCommand()),
    MIDDLE_WORKOUT_PAGE(new MiddleWorkoutPageCommand()),
    HIGH_WORKOUT_PAGE(new HighWorkoutPageCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    CONFIRM_REGISTRATION(new ConfirmRegistrationCommand()),
    INSTRUCTORS_PAGE(new InstructorsPageCommand()),
    SCHEDULE_PAGE(new SchedulePageCommand()),
    UNSUBSCRIBE_IN_PRIVATE_CABINET(new UnsubscribeInPrivateCabinetCommand()),
    SUBSCRIBE_IN_SCHEDULE(new SubscribeInSchedule()),
    UPDATE_USER_INFO(new UpdateUserInfoCommand()),
    PRIVATE_CABINET_PAGE(new PrivateCabinetPageCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    ADD_PHOTO(new AddPhotoCommand()),
    EDIT_SCHEDULE_PAGE(new EditSchedulePageCommand()),
    CHECK_PRESENCE_PAGE(new CheckPresencePageCommand()),
    SALE_MEMBERSHIP_PAGE(new SaleMembershipPageCommand()),
    SALE_MEMBERSHIP(new SaleMembershipCommand()),
    CLIENT_PRESENT(new ClientPresentCommand()),
    CLIENT_ABSENT(new ClientAbsentCommand()),
    DELETE_WORKOUT_FROM_SCHEDULE(new DeleteWorkoutFromScheduleCommand()),
    EDIT_SCHEDULE(new EditScheduleCommand()),
    ADD_SCHEDULE_PAGE(new AddSchedulePageCommand()),
    ADD_SCHEDULE(new AddScheduleCommand()),
    ALL_USER_PAGE(new AllUserPageCommand()),
    SEARCH_USER(new SearchUserCommand()),
    CHANGE_USER_ROLE(new ChangeUserRoleCommand()),
    MEMBERSHIP_PAGE(new MembershipPageCommand()),
    CONTACT_US(new ContactUsCommand()),
    COPY_SCHEDULE(new CopyScheduleCommand()),
    CONTACT_US_PAGE(request -> new Router(ConfigurationManager.getProperty(PageConfigName.CONTACT_US))),
    REGISTRATION_PAGE(request -> new Router(ConfigurationManager.getProperty(PageConfigName.REGISTRATION))),
    ABOUT_US_PAGE(request -> new Router(ConfigurationManager.getProperty(PageConfigName.ABOUT_US))),
    CONTACTS_PAGE(request -> new Router(ConfigurationManager.getProperty(PageConfigName.CONTACTS))),
    TELEGRAM_PAGE(request -> new Router(Router.DisPathType.REDIRECT,
            ConfigurationManager.getProperty(PageConfigName.TELEGRAM))),
    INSTAGRAM_PAGE(request -> new Router(Router.DisPathType.REDIRECT,
            ConfigurationManager.getProperty(PageConfigName.INSTAGRAM))),
    VKONTAKTE_PAGE(request -> new Router(Router.DisPathType.REDIRECT,
            ConfigurationManager.getProperty(PageConfigName.VKONTAKTE)));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public Command getCommand() {
        return command;
    }
}