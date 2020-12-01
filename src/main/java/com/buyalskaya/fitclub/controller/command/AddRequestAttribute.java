package com.buyalskaya.fitclub.controller.command;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.*;
import com.buyalskaya.fitclub.model.service.*;
import com.buyalskaya.fitclub.util.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * The type Add request attribute.
 */
public class AddRequestAttribute {
    /**
     * The constant START_PAGE is store the number of the first page
     */
    public static final int START_PAGE = 1;
    /**
     * The constant AMOUNT_OF_SHOWED_RECORDS is store the number of records that will show in the jsp
     */
    private static final int AMOUNT_OF_SHOWED_RECORDS = 10;

    /**
     * For instructor page.
     * Add request attributes to open instructor's private cabinet page
     *
     * @param request the request
     * @param login   the login
     * @throws ServiceException the service exception
     */
    public static void forInstructorPage(HttpServletRequest request, String login) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        Optional<Staff> staff = userService.findStaffByLogin(login);
        HttpSession session = request.getSession();
        if (staff.isPresent()) {
            session.setAttribute(AttributeName.SESSION_USER, staff.get());
            ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
            List<ClientSchedule> instructorSchedule = scheduleService.findInstructorSchedule(staff.get().getIdUser());
            request.setAttribute(AttributeName.SCHEDULE, instructorSchedule);
            hallsNames(request, instructorSchedule);
        }
    }

    /**
     * For client page.
     * Add request attributes to open client's private cabinet page
     *
     * @param request the request
     * @param idUser  the id user
     * @throws ServiceException the service exception
     */
    public static void forClientPage(HttpServletRequest request, String login) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.findUserByLogin(login).get();
        HttpSession session = request.getSession();
        session.setAttribute(AttributeName.SESSION_USER, user);
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        List<ClientSchedule> clientSchedule = scheduleService.findClientSchedule(user.getIdUser());
        request.setAttribute(AttributeName.SCHEDULE, clientSchedule);
        hallsNames(request, clientSchedule);
        MembershipService membershipService = ServiceFactory.getInstance().getMembershipService();
        List<ClientMembership> memberships = membershipService.findActiveClientMemberships(user.getIdUser());
        request.setAttribute(AttributeName.CLIENT_MEMBERSHIPS, memberships);
    }

    /**
     * For administrator page.
     * Add request attributes to open administrator's private cabinet page
     *
     * @param request the request
     * @param login   the login
     * @throws ServiceException the service exception
     */
    public static void forAdministratorPage(HttpServletRequest request, String login) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        Optional<Staff> staff = userService.findStaffByLogin(login);
        HttpSession session = request.getSession();
        if (staff.isPresent()) {
            session.setAttribute(AttributeName.SESSION_USER, staff.get());
        }
    }

    /**
     * For workout.
     * Add request attributes to open page with workouts
     *
     * @param request the request
     * @throws ServiceException the service exception
     */
    public static void forWorkout(HttpServletRequest request) throws ServiceException {
        WorkoutService workoutService = ServiceFactory.getInstance().getWorkoutService();
        List<Workout> workoutsLow = workoutService.findByLevel(Workout.Level.LOW);
        List<Workout> workoutsMiddle = workoutService.findByLevel(Workout.Level.MIDDLE);
        List<Workout> workoutsHigh = workoutService.findByLevel(Workout.Level.HIGH);
        request.setAttribute(AttributeName.WORKOUTS_LOW, workoutsLow);
        request.setAttribute(AttributeName.WORKOUTS_MIDDLE, workoutsMiddle);
        request.setAttribute(AttributeName.WORKOUTS_HIGH, workoutsHigh);
    }

    /**
     * For check presence.
     * Add request attributes to open page on which administrator check client's presence
     *
     * @param request  the request
     * @param idClient the id client
     * @throws ServiceException the service exception
     */
    public static void forCheckPresence(HttpServletRequest request, String idClient) throws ServiceException {
        MembershipService membershipService = ServiceFactory.getInstance().getMembershipService();
        List<ClientMembership> memberships = membershipService.findActiveClientMemberships(idClient);
        request.setAttribute(AttributeName.CLIENT_MEMBERSHIPS, memberships);
        UserService userService = ServiceFactory.getInstance().getUserService();
        String clientName = userService.findClientName(idClient);
        request.setAttribute(AttributeName.CLIENT_NAME, clientName);
    }

    /**
     * HallsNames.
     * Add set of hall's names from schedule to the request
     *
     * @param request   the request
     * @param schedules the schedules
     */

    private static void hallsNames(HttpServletRequest request, List<? extends Schedule> schedules) {
        if (!schedules.isEmpty()) {
            Set<String> nameHalls = CommonUtil.findNameHalls(schedules);
            request.setAttribute(AttributeName.HALLS_NAME, nameHalls);
        }
    }

    /**
     * For schedule page.
     * Add request attributes to page with schedule
     *
     * @param request the request
     * @throws ServiceException the service exception
     */
    public static void forSchedulePage(HttpServletRequest request) throws ServiceException {
        String numberWeek = request.getParameter(ParameterName.SCHEDULE_NUMBER_WEEK);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.SESSION_USER);
        ScheduleService scheduleService = ServiceFactory.getInstance().getScheduleService();
        List<Schedule> schedules = scheduleService.findScheduleWeek(numberWeek, user);
        boolean hasNextWeekSchedule = scheduleService.hasScheduleNextWeek(numberWeek);
        request.setAttribute(AttributeName.HAS_NEXT_WEEK_SCHEDULE, hasNextWeekSchedule);
        request.setAttribute(AttributeName.SCHEDULES, schedules);
        request.setAttribute(AttributeName.HALLS_TIME, CommonUtil.findTimesInEachHall(schedules));
        request.setAttribute(AttributeName.HALLS_DATE, CommonUtil.findDatesInEachHall(schedules));
        request.setAttribute(AttributeName.SCHEDULE_NUMBER_WEEK, (numberWeek == null) ? 0 : numberWeek);
    }

    /**
     * For change schedule page.
     * Add request attributes to schedule page on which administrator can edit schedule
     *
     * @param request the request
     * @throws ServiceException the service exception
     */
    public static void forChangeSchedulePage(HttpServletRequest request) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        Map<Integer, String> instructorsName = userService.findNameInstructors();
        request.setAttribute(AttributeName.INSTRUCTORS, instructorsName);
        HallService hallService = ServiceFactory.getInstance().getHallService();
        Map<Integer, String> hallsName = hallService.findNameHalls();
        request.setAttribute(AttributeName.HALLS_NAME, hallsName);
        WorkoutService workoutService = ServiceFactory.getInstance().getWorkoutService();
        Map<Integer, String> workoutsName = workoutService.findNameWorkouts();
        request.setAttribute(AttributeName.WORKOUTS_NAME, workoutsName);
    }

    /**
     * For all users page.
     * Add request attributes to all users page on which administrator can change user's role
     *
     * @param request       the request
     * @param surnameSearch the surname search
     * @param numberPage    the number page
     * @throws ServiceException the service exception
     */
    public static void forAllUsersPage(HttpServletRequest request,
                                       String surnameSearch, String numberPage) throws ServiceException {
        List<Staff> users;
        UserService userService = ServiceFactory.getInstance().getUserService();
        if (surnameSearch == null || surnameSearch.isEmpty()) {
            int pagesAmount = userService.countPagesAmount(AMOUNT_OF_SHOWED_RECORDS);
            request.setAttribute(AttributeName.PAGE_AMOUNT, pagesAmount);
            users = userService.findUsersInRange(numberPage, AMOUNT_OF_SHOWED_RECORDS);
        } else {
            users = userService.findUserBySurname(surnameSearch);
            request.setAttribute(AttributeName.PAGE_AMOUNT, START_PAGE);
        }
        request.setAttribute(AttributeName.USERS, users);
        Map<Integer, String> userRoles = userService.findUserRoles();
        Map<Integer, String> userStatuses = userService.findUserStatuses();
        request.setAttribute(AttributeName.USER_ROLES, userRoles);
        request.setAttribute(AttributeName.USER_STATUSES, userStatuses);
        request.setAttribute(AttributeName.USERS_NUMBER_PAGE, (numberPage == null) ? START_PAGE : numberPage);
    }

    private AddRequestAttribute() {
    }
}