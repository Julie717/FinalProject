package com.buyalskaya.fitclub.model.dao;

public class SqlQuery {
    public static final String SELECT_USER_BY_LOGIN = "SELECT id_user, login, password, id_role, " +
            "id_status, name, surname, phone_number, email, birthday, photo " +
            "FROM users " +
            "WHERE (login = ?)";

    public static final String SELECT_STAFF_BY_LOGIN = "SELECT users.id_user, login, password, id_role, " +
            "id_status, name, surname, phone_number, email, birthday, photo, work_experience, " +
            "start_working_date, end_working_date, description FROM users JOIN staffs " +
            "ON (users.id_user = staffs.id_user) WHERE (users.login = ?)";

    public static final String SELECT_PASSWORD_BY_LOGIN = "SELECT password " +
            "FROM users " +
            "WHERE (login = ?)";

    public static final String INSERT_USER = "INSERT INTO users (login, password, id_role, id_status, " +
            "name, surname, phone_number, email, birthday) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String CHANGE_USER_STATUS = "UPDATE users SET id_status = 1 WHERE (login = ?)";

    public static final String SELECT_ALL_USERS = "SELECT users.id_user, login, id_role, id_status " +
            "name, surname, phone_number, email, birthday, photo " +
            "FROM users";

    public static final String SELECT_STAFFS = "SELECT users.id_user, login, password, id_role, " +
            "id_status, name, surname, phone_number, email, birthday, photo, work_experience, " +
            "start_working_date, end_working_date, description FROM users JOIN staffs " +
            "ON (users.id_user = staffs.id_user) WHERE (id_role = ? and id_status = 1)";

    public static final String SELECT_ALL_WORKOUTS = "SELECT id_workout, name_workout, id_workout_level, " +
            "description FROM workouts";

    public static final String SELECT_WORKOUT_BY_LEVEL = "SELECT id_workout, name_workout, id_workout_level, " +
            "description FROM workouts WHERE (id_workout_level = ?)";

    public static final String SELECT_SCHEDULE = "SELECT schedules.id_schedule, start_date, start_time, " +
            "capacity, duration, workouts.id_workout, IFNULL(capacity-occupied_places, capacity) AS free_capacity, " +
            "name_workout, id_workout_level, description, users.id_user, users.surname, users.name, halls.id_hall, " +
            "halls.name_hall " +
            "FROM schedules JOIN workouts " +
            "ON (schedules.id_workout = workouts.id_workout) " +
            "JOIN users ON (schedules.id_instructor = users.id_user) " +
            "JOIN halls ON (halls.id_hall = schedules.id_hall) " +
            "LEFT JOIN (SELECT id_schedule, COUNT(id_client) AS occupied_places " +
            "           FROM client_schedules " +
            "           GROUP BY(id_schedule)) AS subscribed_users " +
            "ON (schedules.id_schedule = subscribed_users.id_schedule) " +
            "WHERE (start_date >= ? AND start_date <= ?)";

    public static final String SELECT_ALL_SCHEDULE_FOR_CLIENT = "SELECT schedules.id_schedule, start_date, start_time, " +
            "capacity, duration, workouts.id_workout, IFNULL(capacity-occupied_places, capacity) AS free_capacity, " +
            "name_workout, id_workout_level, description, users.id_user, users.surname, users.name, " +
            "halls.id_hall, halls.name_hall, " +
            "IFNULL(client_subscribed.subscribed, 0) AS subscribed " +
            "FROM schedules JOIN workouts " +
            "ON (schedules.id_workout = workouts.id_workout) " +
            "JOIN users ON (schedules.id_instructor = users.id_user) " +
            "JOIN halls ON (halls.id_hall = schedules.id_hall) " +
            "LEFT JOIN (SELECT id_schedule, COUNT(id_client) AS occupied_places " +
            "           FROM client_schedules " +
            "           GROUP BY(id_schedule)) AS amount_subscribed_users " +
            "ON (schedules.id_schedule = amount_subscribed_users.id_schedule) " +
            "LEFT JOIN (SELECT  id_schedule, COUNT(id_client) AS subscribed " +
            "           FROM client_schedules " +
            "           WHERE (id_client = ?) " +
            "           GROUP BY(id_schedule)) AS client_subscribed " +
            "ON (schedules.id_schedule = client_subscribed.id_schedule) " +
            "WHERE (start_date >= ? AND start_date <= ?)";

    public static final String SELECT_CLIENT_SCHEDULE = "SELECT schedules.id_schedule, start_date, start_time, " +
            "capacity, duration, workouts.id_workout, IFNULL(capacity-occupied_places, capacity) AS free_capacity, " +
            "name_workout, id_workout_level, workouts.description, users.id_user, users.surname, users.name, " +
            "halls.id_hall, halls.name_hall " +
            "FROM schedules JOIN workouts " +
            "ON (schedules.id_workout = workouts.id_workout) " +
            "JOIN users ON (schedules.id_instructor = users.id_user) " +
            "JOIN halls ON (halls.id_hall = schedules.id_hall) " +
            "LEFT JOIN (SELECT id_schedule, COUNT(id_client) AS occupied_places " +
            "           FROM client_schedules " +
            "           GROUP BY(id_schedule)) AS amount_subscribed_users " +
            "ON (schedules.id_schedule = amount_subscribed_users.id_schedule) " +
            "WHERE  schedules.id_schedule IN ( " +
            "           SELECT  schedules.id_schedule   FROM client_schedules " +
            "           JOIN schedules " +
            "           ON(client_schedules.id_schedule=schedules.id_schedule) " +
            "           WHERE (id_client = ?) AND (start_date > ?) " +
            "      UNION " +
            "           SELECT  schedules.id_schedule   FROM client_schedules " +
            "           JOIN schedules " +
            "           ON(client_schedules.id_schedule=schedules.id_schedule) " +
            "           WHERE (id_client = ?) AND (start_date = ?) AND (start_time >= ?)" +
            ")";

    public static final String SELECT_INSTRUCTOR_SCHEDULE = "SELECT schedules.id_schedule, start_date, start_time, " +
            "capacity, duration, workouts.id_workout, IFNULL(capacity-occupied_places, capacity) AS free_capacity, " +
            "name_workout, id_workout_level, workouts.description, users.id_user, users.surname, users.name, " +
            "halls.id_hall, halls.name_hall " +
            "FROM schedules JOIN workouts " +
            "ON (schedules.id_workout = workouts.id_workout) " +
            "JOIN users ON (schedules.id_instructor = users.id_user) " +
            "JOIN halls ON (halls.id_hall = schedules.id_hall) " +
            "LEFT JOIN (SELECT id_schedule, COUNT(id_client) AS occupied_places " +
            "           FROM client_schedules " +
            "           GROUP BY(id_schedule)) AS amount_subscribed_users " +
            "ON (schedules.id_schedule = amount_subscribed_users.id_schedule) " +
            "WHERE  schedules.id_schedule IN ( " +
            "                       SELECT  schedules.id_schedule   FROM  schedules " +
            "                       JOIN users " +
            "                       ON(schedules.id_instructor = users.id_user) " +
            "                       WHERE (id_user = ?) AND (start_date > ?) " +
            "                  UNION " +
            "                       SELECT  schedules.id_schedule   FROM  schedules " +
            "                       JOIN users " +
            "                       ON(schedules.id_instructor = users.id_user)  " +
            "                       WHERE (id_user = ?) AND (start_date = ?) AND (start_time >= ?))";

    public static final String SELECT_ACTIVE_CLIENT_MEMBERSHIPS = "SELECT id_client_membership, " +
            "type_by_number_people, period_time, " +
            "remaining_classes, open_date, SUM(open_date+duration) AS close_date " +
            "FROM users " +
            "JOIN client_memberships " +
            "ON (users.id_user = client_memberships.id_user) " +
            "JOIN memberships " +
            "ON (client_memberships.id_membership = memberships.id_membership) " +
            "JOIN type_classes " +
            "ON (type_classes.id_type_class=memberships.id_type_class) " +
            "WHERE (users.id_user = ?) " +
            "GROUP BY (id_client_membership) " +
            "HAVING (close_date >= ?)";

    public static final String UNSUBSCRIBE_CLIENT = "DELETE " +
            "FROM client_schedules " +
            "WHERE (id_client = ? AND id_schedule = ?)";

    public static final String SUBSCRIBE_CLIENT = "INSERT " +
            "INTO client_schedules " +
            "SET id_client = ?, id_schedule = ?";

    public static final String UPDATE_CLIENT_INFO = "UPDATE users " +
            "SET login = ?, name = ?, surname = ?, phone_number = ?, email = ?, birthday = ? " +
            "WHERE id_user = ?";

    public static final String UPDATE_PASSWORD = "UPDATE users " +
            "SET password = ? " +
            "WHERE id_user = ?";

    public static final String UPDATE_STAFF_INFO = "UPDATE staffs " +
            "SET work_experience = ?, description = ? " +
            "WHERE id_user = ?";

    public static final String UPDATE_PHOTO = "UPDATE users " +
            "SET photo = ? " +
            "WHERE id_user = ?";

    public static final String SELECT_ONE_SCHEDULE = "SELECT schedules.id_schedule, start_date, start_time, " +
            "capacity, duration, workouts.id_workout, IFNULL(capacity-occupied_places, capacity) AS free_capacity, " +
            "name_workout, id_workout_level, description, users.id_user, users.surname, users.name, " +
            "halls.id_hall, halls.name_hall " +
            "FROM schedules JOIN workouts " +
            "ON (schedules.id_workout = workouts.id_workout) " +
            "JOIN users ON (schedules.id_instructor = users.id_user) " +
            "JOIN halls ON (halls.id_hall = schedules.id_hall) " +
            "LEFT JOIN (SELECT id_schedule, COUNT(id_client) AS occupied_places " +
            "           FROM client_schedules " +
            "           GROUP BY(id_schedule)) AS subscribed_users " +
            "ON (schedules.id_schedule = subscribed_users.id_schedule) " +
            "WHERE (schedules.id_schedule = ?)";

    public static final String SELECT_SUBSCRIBED_CLIENTS = "SELECT id_user, login, " +
            "surname, name, phone_number, email, id_client_membership " +
            "FROM users " +
            "JOIN client_schedules " +
            "ON(users.id_user = client_schedules.id_client) " +
            "WHERE id_schedule = ?";

    public static final String SELECT_ALL_INSTRUCTOR_NAMES = "SELECT id_user AS id_instructor, " +
            "CONCAT(surname, \" \",name) " +
            "AS instructor_name " +
            "FROM users " +
            "WHERE id_role = 2 AND id_status = 1";

    public static final String SELECT_ALL_HALL_NAMES = "SELECT id_hall, name_hall FROM halls";

    public static final String SELECT_ALL_WORKOUT_NAMES = "SELECT id_workout, name_workout FROM workouts";

    public static final String SELECT_CLIENT_NAME = "SELECT CONCAT(surname, \" \",name) AS client_name " +
            "FROM users " +
            "WHERE id_user = ?";
    public static final String SELECT_ALL_MEMBERSHIPS = "SELECT id_membership, duration, " +
            "classes_amount, price, start_sale, end_sale, type_by_number_people, period_time " +
            "FROM memberships " +
            "JOIN type_classes " +
            "ON (memberships.id_type_class = type_classes.id_type_class) " +
            "WHERE end_sale IS NULL OR end_sale >= ?";

    public static final String SELECT_CLASSES_AMOUNT_IN_MEMBERSHIP = "SELECT classes_amount " +
            "FROM memberships " +
            "WHERE id_membership = ?";

    public static final String INSERT_CLIENT_MEMBERSHIP = "INSERT INTO client_memberships " +
            "(open_date, remaining_classes, id_user, id_membership) " +
            "VALUES(?, ?, ?, ?)";

    public static final String UPDATE_CLIENT_SCHEDULE = "UPDATE client_schedules " +
            "SET id_client_membership = ? " +
            "WHERE id_client = ? AND id_schedule = ?";

    public static final String CLIENT_MEMBERSHIP_MINUS_WORKOUT = "UPDATE client_memberships " +
            "SET remaining_classes = remaining_classes - 1 " +
            "WHERE id_client_membership = ?";

    public static final String CLIENT_MEMBERSHIP_PLUS_WORKOUT = "UPDATE client_memberships " +
            "SET remaining_classes = remaining_classes + 1 " +
            "WHERE id_client_membership = ?";

    public static final String SELECT_CLASSES_AMOUNT_IN_CLIENT_MEMBERSHIP = "SELECT classes_amount " +
            "FROM client_memberships " +
            "JOIN memberships " +
            "ON (client_memberships.id_membership = memberships.id_membership) " +
            "WHERE id_client_membership = ?";

    public static final String SELECT_REMAINING_CLASSES_IN_CLIENT_MEMBERSHIP = "SELECT remaining_classes " +
            "FROM client_memberships " +
            "JOIN memberships " +
            "ON (client_memberships.id_membership = memberships.id_membership) " +
            "WHERE id_client_membership = ?";

    public static final String DELETE_SCHEDULE = "DELETE FROM schedules " +
            "WHERE id_schedule = ?";

    public static final String DELETE_CLIENT_SCHEDULE = "DELETE FROM client_schedules " +
            "WHERE id_schedule =  ?";

    public static final String UPDATE_SCHEDULE = "UPDATE schedules " +
            "SET start_date = ?, start_time = ?, duration = ?, capacity = ?, id_workout = ?, " +
            "id_hall = ?, id_instructor = ? " +
            "WHERE id_schedule = ?";

    public static final String IS_WORKOUT_EXIST = "SELECT count(id_workout)>0 AS exist_workout " +
            "FROM workouts " +
            "WHERE id_workout = ?";

    public static final String IS_HALL_EXIST = "SELECT count(id_hall)>0 AS exist_hall " +
            "FROM halls " +
            "WHERE id_hall = ?";

    public static final String IS_INSTRUCTOR_EXIST = "SELECT count(users.id_user)>0 AS exist_instructor " +
            "FROM users " +
            "JOIN staffs " +
            "ON(users.id_user = staffs.id_user) " +
            "WHERE users.id_user = ?";

    public static final String IS_FREE_TIME = "SELECT count(id_schedule) AS exist_schedule " +
            "FROM schedules " +
            "WHERE id_hall = ? AND start_date = ? AND (start_time <= ? AND start_time+duration >= ? OR " +
            "            start_time <= ? AND start_time+duration >= ?) AND id_schedule != ?";

    public static final String INSERT_SCHEDULE = "INSERT INTO schedules (start_date, start_time, " +
            "duration, capacity, id_workout, id_hall, id_instructor) VALUES(?, ?, ?, ?, ?, ?, ?)";

    public static final String SELECT_ALL_ROLES = "SELECT id_role, name FROM user_roles";

    public static final String SELECT_ALL_STATUSES = "SELECT id_status, name FROM statuses";

    public static final String SELECT_USERS_AMOUNT = "SELECT COUNT(id_user) AS users_amount FROM users";

    public static final String SELECT_USERS_IN_RANGE = "SELECT * FROM ( " +
            "        SELECT users.id_user, login, surname, name, id_role, id_status, " +
            "start_working_date, end_working_date, ROW_NUMBER() OVER (ORDER BY surname) AS number_row " +
            "        FROM users " +
            "        LEFT JOIN staffs " +
            "        ON (users.id_user = staffs.id_user) " +
            ") AS all_users " +
            "WHERE number_row >= ? AND number_row <= ?";

    public static final String SELECT_USER_BY_SURNAME = "SELECT users.id_user, login, surname, name, " +
            "id_role, id_status, start_working_date, end_working_date " +
            "FROM users " +
            "LEFT JOIN staffs " +
            "ON (users.id_user = staffs.id_user) " +
            "WHERE (surname LIKE CONCAT( ?, '%'))";

    public static final String UPDATE_USER_ROLE_AND_STATUS = "UPDATE users " +
            "SET id_role = ?, id_status = ? " +
            "WHERE (users.id_user = ?)";

    public static final String INSERT_INTO_STAFF = "INSERT INTO staffs (start_working_date, end_working_date, " +
            "id_user) VALUES(?, ?, ?)";

    public static final String DELETE_FROM_STAFF = "DELETE FROM staffs WHERE id_user = ?";

    public static final String SELECT_USER_OR_STAFF_BY_ID = "SELECT users.id_user, login, surname, name, " +
            "id_role, id_status, start_working_date, end_working_date " +
            "FROM users " +
            "LEFT JOIN staffs " +
            "ON (users.id_user = staffs.id_user) " +
            "WHERE (users.id_user = ?)";

    public static final String UPDATE_STAFF_ROLE = "UPDATE users " +
            "LEFT JOIN staffs " +
            "ON (users.id_user = staffs.id_user) " +
            "SET id_role = ?, id_status = ?, start_working_date = ?, end_working_date = ? " +
            "WHERE (users.id_user = ?)";

    public static final String UPDATE_USER_STATUS = "UPDATE users " +
            "SET id_status = ? " +
            "WHERE (users.id_user = ?)";

    public static final String SELECT_SCHEDULE_BY_ID = "SELECT id_schedule, start_date, start_time, duration, " +
            "capacity, id_workout, id_hall, id_instructor AS id_user FROM schedules WHERE id_schedule = ?";

    public static final String SELECT_AMOUNT_OF_OCCUPIED_PLACES = "SELECT COUNT(id_client) AS occupied_places " +
            "FROM client_schedules " +
            "GROUP BY(id_schedule) " +
            "HAVING (id_schedule = ?)";

    public static final String SELECT_LAST_DATE_IN_SCHEDULE = "SELECT MAX(start_date) AS last_date " +
            "FROM schedules";

    public static final String COPY_SCHEDULE_OF_LAST_WEEK ="INSERT INTO schedules (start_date, start_time, " +
            "duration, capacity, id_workout, id_hall, id_instructor) " +
            "SELECT start_date + 7 AS start_date, start_time, duration, capacity, id_workout, id_hall, id_instructor " +
            "FROM schedules " +
            "WHERE (start_date >= ? AND start_date <= ?)";

    private SqlQuery() {
    }
}