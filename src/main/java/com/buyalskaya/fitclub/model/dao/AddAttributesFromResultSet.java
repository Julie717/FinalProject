package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.model.entity.*;
import com.buyalskaya.fitclub.util.CommonUtil;
import com.buyalskaya.fitclub.util.DateTimeTransformer;
import com.buyalskaya.fitclub.util.ImageTransformer;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * The type Add attributes from result set.
 * It is used to add parameters to objects that was received from the database.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class AddAttributesFromResultSet {
    private AddAttributesFromResultSet() {
    }

    /**
     * Add user attributes.
     *
     * @param user      the user
     * @param resultSet the result set
     * @throws SQLException the sql exception
     */
    public static void addUserAttributes(User user, ResultSet resultSet) throws SQLException {
        addIdLoginName(user, resultSet);
        addRoleAndStatus(user, resultSet);
        user.setPhoneNumber(resultSet.getString(ColumnName.USERS_PHONE));
        user.setEmail(resultSet.getString(ColumnName.USERS_EMAIL));
        long birthday = resultSet.getLong(ColumnName.USERS_BIRTHDAY);
        LocalDate birthdayDate = DateTimeTransformer.fromLongToLocalDate(birthday);
        user.setBirthday(birthdayDate);
        InputStream inputStream = resultSet.getBinaryStream(ColumnName.USERS_PHOTO);
        String photo = ImageTransformer.transformImageToStringBase64(inputStream);
        user.setPhoto(photo);
    }

    /**
     * Add role and status.
     *
     * @param user      the user
     * @param resultSet the result set
     * @throws SQLException the sql exception
     */
    public static void addRoleAndStatus(User user, ResultSet resultSet) throws SQLException {
        int idRole = resultSet.getInt(ColumnName.USERS_ROLE);
        UserRole userRole = CommonUtil.findRole(idRole);
        user.setRole(userRole);
        int idStatus = resultSet.getInt(ColumnName.USERS_STATUS);
        UserStatus userStatus = CommonUtil.findStatus(idStatus);
        user.setStatus(userStatus);
    }

    /**
     * Add id login name.
     *
     * @param user      the user
     * @param resultSet the result set
     * @throws SQLException the sql exception
     */
    public static void addIdLoginName(User user, ResultSet resultSet) throws SQLException {
        user.setIdUser(resultSet.getInt(ColumnName.USERS_ID));
        user.setLogin(resultSet.getString(ColumnName.USERS_LOGIN));
        user.setName(resultSet.getString(ColumnName.USERS_NAME));
        user.setSurname(resultSet.getString(ColumnName.USERS_SURNAME));
    }

    /**
     * Add staff attributes.
     *
     * @param staff     the staff
     * @param resultSet the result set
     * @throws SQLException the sql exception
     */
    public static void addStaffAttributes(Staff staff, ResultSet resultSet) throws SQLException {
        addUserAttributes(staff, resultSet);
        staff.setWorkExperience(resultSet.getInt(ColumnName.STAFFS_WORK_EXPERIENCE));
        long start_working_date = resultSet.getLong(ColumnName.STAFFS_START_WORKING_DATE);
        LocalDate start_work = DateTimeTransformer.fromLongToLocalDate(start_working_date);
        staff.setStartWorkingDate(start_work);
        long end_working_date = resultSet.getLong(ColumnName.STAFFS_END_WORKING_DATE);
        LocalDate end_work = DateTimeTransformer.fromLongToLocalDate(end_working_date);
        staff.setEndWorkingDate(end_work);
        staff.setDescription(resultSet.getString(ColumnName.STAFFS_DESCRIPTION));
    }


    /**
     * Add workout attributes.
     *
     * @param workout   the workout
     * @param resultSet the result set
     * @throws SQLException the sql exception
     */
    public static void addWorkoutAttributes(Workout workout, ResultSet resultSet) throws SQLException {
        workout.setIdWorkout(resultSet.getInt(ColumnName.WORKOUTS_ID));
        workout.setName(resultSet.getString(ColumnName.WORKOUTS_NAME));
        int idLevel = resultSet.getInt(ColumnName.WORKOUTS_LEVEL);
        Workout.Level level = Arrays.stream(Workout.Level.values())
                .filter(lev -> lev.getIdLevel() == idLevel)
                .findFirst()
                .orElse(Workout.Level.LOW);
        workout.setLevel(level);
        workout.setDescription(resultSet.getString(ColumnName.WORKOUTS_DESCRIPTION));
    }

    /**
     * Add schedule attributes.
     *
     * @param schedule  the schedule
     * @param resultSet the result set
     * @throws SQLException the sql exception
     */
    public static void addScheduleAttributes(Schedule schedule, ResultSet resultSet) throws SQLException {
        schedule.setIdSchedule(resultSet.getInt(ColumnName.SCHEDULES_ID));
        Workout workout = new Workout();
        workout.setIdWorkout(resultSet.getInt(ColumnName.WORKOUTS_ID));
        schedule.setWorkout(workout);
        schedule.setIdInstructor(resultSet.getInt(ColumnName.USERS_ID));
        LocalDate startDate = DateTimeTransformer.fromLongToLocalDate(resultSet.getLong(ColumnName.SCHEDULES_START_DATE));
        schedule.setStartDate(startDate);
        LocalTime startTime = DateTimeTransformer.fromLongToLocalTime(resultSet.getLong(ColumnName.SCHEDULES_START_TIME));
        schedule.setStartTime(startTime);
        schedule.setCapacity(resultSet.getInt(ColumnName.SCHEDULES_CAPACITY));
        schedule.setIdHall(resultSet.getInt(ColumnName.HALLS_ID));
        schedule.setDuration(resultSet.getInt(ColumnName.SCHEDULES_DURATION));
    }

    /**
     * Add additional schedule attributes.
     *
     * @param schedule  the schedule
     * @param resultSet the result set
     * @throws SQLException the sql exception
     */
    public static void addAdditionalScheduleAttributes(Schedule schedule, ResultSet resultSet) throws SQLException {
        addScheduleAttributes(schedule,resultSet);
        Workout workout = new Workout();
        addWorkoutAttributes(workout, resultSet);
        schedule.setWorkout(workout);
        schedule.setNameInstructor(resultSet.getString(ColumnName.USERS_NAME));
        schedule.setSurnameInstructor(resultSet.getString(ColumnName.USERS_SURNAME));
        schedule.setFreeCapacity(resultSet.getInt(ColumnName.SCHEDULES_FREE_CAPACITY));
        schedule.setNameHall(resultSet.getString(ColumnName.HALLS_NAME_HALL));
    }

    /**
     * Add client schedule attributes.
     *
     * @param schedule  the schedule
     * @param resultSet the result set
     * @throws SQLException the sql exception
     */
    public static void addClientScheduleAttributes(ClientSchedule schedule, ResultSet resultSet) throws SQLException {
        addAdditionalScheduleAttributes(schedule, resultSet);
        int subscribed = resultSet.getInt(ColumnName.SUBSCRIBED);
        schedule.setSubscribed(subscribed > 0);
    }

    /**
     * Add client membership attributes.
     *
     * @param clientMembership the client membership
     * @param resultSet        the result set
     * @throws SQLException the sql exception
     */
    public static void addClientMembershipAttributes(ClientMembership clientMembership, ResultSet resultSet) throws SQLException {
        clientMembership.setIdClientMembership(
                resultSet.getInt(ColumnName.CLIENT_SCHEDULE_ID_CLIENT_MEMBERSHIP));
        clientMembership.setTypeByNumberPeople(
                resultSet.getString(ColumnName.TYPE_CLASSES_TYPE_BY_NUMBER_PEOPLE));
        clientMembership.setPeriodTime(resultSet.getString(ColumnName.TYPE_CLASSES_PERIOD_TIME));
        clientMembership.setRemainingClasses(resultSet.getInt(ColumnName.CLIENT_MEMBERSHIPS_REMAINING_CLASSES));
        LocalDate openDate = DateTimeTransformer.fromLongToLocalDate(
                resultSet.getLong(ColumnName.CLIENT_MEMBERSHIPS_OPEN_DATE));
        clientMembership.setOpenDate(openDate);
        LocalDate closeDate = DateTimeTransformer.fromLongToLocalDate(
                resultSet.getLong(ColumnName.CLIENT_MEMBERSHIPS_CLOSE_DATE));
        clientMembership.setCloseDate(closeDate);
    }

    /**
     * Add client attributes for edit schedule.
     *
     * @param client    the client
     * @param resultSet the result set
     * @throws SQLException the sql exception
     */
    public static void addClientAttributesForEditSchedule(Client client, ResultSet resultSet) throws SQLException {
        addIdLoginName(client, resultSet);
        client.setEmail(resultSet.getString(ColumnName.USERS_EMAIL));
        client.setPhoneNumber(resultSet.getString(ColumnName.USERS_PHONE));
        client.setIdClientMembership(resultSet.getInt(ColumnName.CLIENT_SCHEDULE_ID_CLIENT_MEMBERSHIP));
    }

    /**
     * Add membership attributes.
     *
     * @param membership the membership
     * @param resultSet  the result set
     * @throws SQLException the sql exception
     */
    public static void addMembershipAttributes(Membership membership, ResultSet resultSet) throws SQLException {
        membership.setIdMembership(resultSet.getInt(ColumnName.MEMBERSHIPS_ID));
        membership.setDuration(resultSet.getInt(ColumnName.MEMBERSHIPS_DURATION));
        membership.setClassesAmount(resultSet.getInt(ColumnName.MEMBERSHIPS_CLASSES_AMOUNT));
        membership.setPrice(resultSet.getBigDecimal(ColumnName.MEMBERSHIPS_PRICE));
        LocalDate startSale = DateTimeTransformer.fromLongToLocalDate(
                resultSet.getLong(ColumnName.MEMBERSHIPS_START_SALE));
        membership.setStartSale(startSale);
        LocalDate endSale = DateTimeTransformer.fromLongToLocalDate(
                resultSet.getLong(ColumnName.MEMBERSHIPS_END_SALE));
        if (endSale.equals(DateTimeTransformer.START_COUNTING)) {
            endSale = null;
        }
        membership.setEndSale(endSale);
        membership.setTypeByNumberPeople(
                resultSet.getString(ColumnName.TYPE_CLASSES_TYPE_BY_NUMBER_PEOPLE));
        membership.setPeriodTime(resultSet.getString(ColumnName.TYPE_CLASSES_PERIOD_TIME));
    }

    /**
     * Add user or staff attributes.
     *
     * @param user      the user
     * @param resultSet the result set
     * @throws SQLException the sql exception
     */
    public static void addUserOrStaffAttributes(Staff user, ResultSet resultSet) throws SQLException {
        addRoleAndStatus(user, resultSet);
        addIdLoginName(user, resultSet);
        long startWorking = resultSet.getLong(ColumnName.STAFFS_START_WORKING_DATE);
        LocalDate startWorkingDate = DateTimeTransformer.fromLongToLocalDate(startWorking);
        user.setStartWorkingDate(startWorkingDate);
        LocalDate endWorkingDate = DateTimeTransformer.
                fromLongToLocalDate(resultSet.getLong(ColumnName.STAFFS_END_WORKING_DATE));
        if (endWorkingDate.equals(DateTimeTransformer.START_COUNTING)) {
            endWorkingDate = null;
        }
        user.setEndWorkingDate(endWorkingDate);
    }
}