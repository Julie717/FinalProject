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
    /**
     * The Home page is command that allows to go to the home page
     */
    HOME_PAGE(new HomePageCommand()),
    /**
     * The Change locale is command that allows to switch language
     */
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    /**
     * The Workout page is command that allows to go to the page with all workouts
     */
    WORKOUT_PAGE(new WorkoutPageCommand()),
    /**
     * The Low workout page is command that allows to go to the page with workouts of low level
     */
    LOW_WORKOUT_PAGE(new LowWorkoutPageCommand()),
    /**
     * The Middle workout page is command that allows to go to the page with workouts of middle level
     */
    MIDDLE_WORKOUT_PAGE(new MiddleWorkoutPageCommand()),
    /**
     * The High workout page is command that allows to go to the page with workouts of high level
     */
    HIGH_WORKOUT_PAGE(new HighWorkoutPageCommand()),
    /**
     * The Login is command that allows to pass the authorisation
     */
    LOGIN(new LoginCommand()),
    /**
     * The Logout is command that allows to logout from the site
     */
    LOGOUT(new LogoutCommand()),
    /**
     * The Registration is command that allows to register in the site
     */
    REGISTRATION(new RegistrationCommand()),
    /**
     * The Confirm registration is command that allows to confirm registration
     */
    CONFIRM_REGISTRATION(new ConfirmRegistrationCommand()),
    /**
     * The Instructors page is command that allows to go to the page with information about all instructors
     */
    INSTRUCTORS_PAGE(new InstructorsPageCommand()),
    /**
     * The Schedule page is command that allows to go to the page with information about all schedule
     */
    SCHEDULE_PAGE(new SchedulePageCommand()),
    /**
     * The Unsubscribe in private cabinet is command that allows clients unsubscribe from workouts
     * that shows in their private cabinet
     */
    UNSUBSCRIBE_IN_PRIVATE_CABINET(new UnsubscribeInPrivateCabinetCommand()),
    /**
     * The Subscribe in schedule is command that allows clients subscribe on workouts in schedule page
     */
    SUBSCRIBE_IN_SCHEDULE(new SubscribeInSchedule()),
    /**
     * The Update user info is command that allows users update their information
     * that they entered during the registration
     */
    UPDATE_USER_INFO(new UpdateUserInfoCommand()),
    /**
     * The Private cabinet page is command that allows to go to the private cabinet
     */
    PRIVATE_CABINET_PAGE(new PrivateCabinetPageCommand()),
    /**
     * The Change password is command that allows to change password in private cabinet
     */
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    /**
     * The Add photo is command that allows to add a photo in the private cabinet
     */
    ADD_PHOTO(new AddPhotoCommand()),
    /**
     * The Edit schedule page is command that allows administrators edit schedule parameters
     */
    EDIT_SCHEDULE_PAGE(new EditSchedulePageCommand()),
    /**
     * The Check presence page is command that allows administrators to go to page for checking client's presence
     */
    CHECK_PRESENCE_PAGE(new CheckPresencePageCommand()),
    /**
     * The Sale membership page is command that allows administrators to go to the page
     * for the sale of memberships to the clients
     */
    SALE_MEMBERSHIP_PAGE(new SaleMembershipPageCommand()),
    /**
     * The Sale membership is command that allows administrators to sale of memberships to the clients
     */
    SALE_MEMBERSHIP(new SaleMembershipCommand()),
    /**
     * The Client present is command that allows administrators to mark the client's presents
     */
    CLIENT_PRESENT(new ClientPresentCommand()),
    /**
     * The Client absent is command that allows administrators to unmark the client's presents
     */
    CLIENT_ABSENT(new ClientAbsentCommand()),
    /**
     * The Delete workout from schedule is command that allows administrators to cancel a workout
     */
    DELETE_WORKOUT_FROM_SCHEDULE(new DeleteWorkoutFromScheduleCommand()),
    /**
     * The Edit schedule is command that allows administrators to edit workout's parameters in the schedule
     */
    EDIT_SCHEDULE(new EditScheduleCommand()),
    /**
     * The Add schedule page is command that allows administrators to go to the page on which he can
     * add a workout into schedule
     */
    ADD_SCHEDULE_PAGE(new AddSchedulePageCommand()),
    /**
     * The Add schedule is command  that allows administrators to add a workout into schedule
     */
    ADD_SCHEDULE(new AddScheduleCommand()),
    /**
     * The All user page is command that allows administrators to go to the page with all users for changing a role
     */
    ALL_USER_PAGE(new AllUserPageCommand()),
    /**
     * The Search user is command that allows administrators for searching user by surname
     * on the page with all users
     */
    SEARCH_USER(new SearchUserCommand()),
    /**
     * The Change user role is command that allows administrators to change user's role
     */
    CHANGE_USER_ROLE(new ChangeUserRoleCommand()),
    /**
     * The Membership page is command that allows administrators to go to the page with all memberships
     */
    MEMBERSHIP_PAGE(new MembershipPageCommand()),
    /**
     * The Contact us is command that allows users to send a message to administrators
     */
    CONTACT_US(new ContactUsCommand()),
    /**
     * The Copy schedule is used to copy all workouts in schedule (stored in database) from the last week,
     * to the next week
     */
    COPY_SCHEDULE(new CopyScheduleCommand()),
    /**
     * The Contact us page is command that allows users to go to the page
     * on witch they can send a message to administrators
     */
    CONTACT_US_PAGE(request -> new Router(ConfigurationManager.getProperty(PageConfigName.CONTACT_US))),
    /**
     * The Registration page is command that allows users to go to the page
     * on witch they can register
     */
    REGISTRATION_PAGE(request -> new Router(ConfigurationManager.getProperty(PageConfigName.REGISTRATION))),
    /**
     * The About us page is command that allows users to go to the page
     * on witch they can see information about fitness club
     */
    ABOUT_US_PAGE(request -> new Router(ConfigurationManager.getProperty(PageConfigName.ABOUT_US))),
    /**
     * The Contacts page is command that allows users to go to the page
     * on witch they can see information about contacts
     */
    CONTACTS_PAGE(request -> new Router(ConfigurationManager.getProperty(PageConfigName.CONTACTS))),
    /**
     * The Telegram page is command that allows users to go to the telegram channel
     */
    TELEGRAM_PAGE(request -> new Router(Router.DisPathType.REDIRECT,
            ConfigurationManager.getProperty(PageConfigName.TELEGRAM))),
    /**
     * The Instagram page is command that allows users to go to the instagram
     */
    INSTAGRAM_PAGE(request -> new Router(Router.DisPathType.REDIRECT,
            ConfigurationManager.getProperty(PageConfigName.INSTAGRAM))),
    /**
     * The Vkontakte page is command that allows users to go to the VK group
     */
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