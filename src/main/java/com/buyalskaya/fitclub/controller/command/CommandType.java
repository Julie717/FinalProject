package com.buyalskaya.fitclub.controller.command;

import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.impl.*;
import com.buyalskaya.fitclub.controller.command.impl.navigation.*;
import com.buyalskaya.fitclub.pageconfiguration.ConfigurationManager;
import com.buyalskaya.fitclub.pageconfiguration.PageConfigName;

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
    UPDATE_CLIENT_INFO(new UpdateClientInfoCommand()),
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
    REGISTRATION_PAGE(request -> new Router(ConfigurationManager.getProperty(PageConfigName.REGISTRATION))),
    ADD_SCHEDULE_PAGE(new AddSchedulePageCommand()),
    ADD_SCHEDULE(new AddScheduleCommand()),
    ALL_USER_PAGE(new AllUserPageCommand()),
    SEARCH_USER(new SearchUserCommand()),
    CHANGE_USER_ROLE(new ChangeUserRoleCommand()),
    MEMBERSHIP_PAGE(new MembershipPageCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}