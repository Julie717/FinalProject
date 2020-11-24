package com.buyalskaya.fitclub.model.dao;

/**
 * The type Column name.
 * Is used for store the database's field's names.
 * The first part of the constant name is a database's table's name,
 * the last part is a table's field's name
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class ColumnName {
    public static final String USERS_ID = "id_user";
    public static final String USERS_LOGIN = "login";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_ROLE = "id_role";
    public static final String USERS_STATUS = "id_status";
    public static final String USERS_NAME = "name";
    public static final String USERS_SURNAME = "surname";
    public static final String USERS_PHONE = "phone_number";
    public static final String USERS_EMAIL = "email";
    public static final String USERS_BIRTHDAY = "birthday";
    public static final String USERS_PHOTO = "photo";

    public static final String STAFFS_WORK_EXPERIENCE = "work_experience";
    public static final String STAFFS_START_WORKING_DATE = "start_working_date";
    public static final String STAFFS_END_WORKING_DATE = "end_working_date";
    public static final String STAFFS_DESCRIPTION = "description";

    public static final String WORKOUTS_ID = "id_workout";
    public static final String WORKOUTS_NAME = "name_workout";
    public static final String WORKOUTS_LEVEL = "id_workout_level";
    public static final String WORKOUTS_DESCRIPTION = "description";

    public static final String SCHEDULES_ID = "id_schedule";
    public static final String SCHEDULES_START_DATE = "start_date";
    public static final String SCHEDULES_START_TIME = "start_time";
    public static final String SCHEDULES_CAPACITY = "capacity";
    public static final String SCHEDULES_FREE_CAPACITY = "free_capacity";
    public static final String SCHEDULES_DURATION = "duration";

    public static final String HALLS_NAME_HALL = "name_hall";
    public static final String HALLS_ID = "id_hall";

    public static final String SUBSCRIBED = "subscribed";

    public static final String MEMBERSHIPS_ID = "id_membership";
    public static final String MEMBERSHIPS_DURATION = "duration";
    public static final String MEMBERSHIPS_CLASSES_AMOUNT = "classes_amount";
    public static final String MEMBERSHIPS_PRICE = "price";
    public static final String MEMBERSHIPS_START_SALE = "start_sale";
    public static final String MEMBERSHIPS_END_SALE = "end_sale";

    public static final String TYPE_CLASSES_TYPE_BY_NUMBER_PEOPLE = "type_by_number_people";
    public static final String TYPE_CLASSES_PERIOD_TIME = "period_time";

    public static final String CLIENT_MEMBERSHIPS_OPEN_DATE = "open_date";
    public static final String CLIENT_MEMBERSHIPS_REMAINING_CLASSES = "remaining_classes";
    public static final String CLIENT_MEMBERSHIPS_CLOSE_DATE = "close_date";

    public static final String INSTRUCTOR_NAME = "instructor_name";
    public static final String INSTRUCTOR_ID = "id_instructor";
    public static final String CLIENT_NAME = "client_name";

    public static final String CLIENT_SCHEDULE_ID_CLIENT_MEMBERSHIP = "id_client_membership";
    public static final String EXIST_WORKOUT = "exist_workout";
    public static final String EXIST_HALL = "exist_hall";
    public static final String EXIST_INSTRUCTOR = "exist_instructor";
    public static final String EXIST_SCHEDULE = "exist_schedule";

    public static final String USER_ROLES_ID = "id_role";
    public static final String USER_ROLES_NAME = "name";

    public static final String STATUSES_ID = "id_status";
    public static final String STATUSES_NAME = "name";

    public static final String USERS_AMOUNT = "users_amount";
    public static final String OCCUPIED_PLACES = "occupied_places";

    public static final String SCHEDULE_LAST_DATE = "last_date";

    private ColumnName() {
    }
}