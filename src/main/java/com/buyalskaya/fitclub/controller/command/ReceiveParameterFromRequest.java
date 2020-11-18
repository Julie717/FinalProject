package com.buyalskaya.fitclub.controller.command;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.util.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Receive parameter from request.
 * It uses for creating a map of parameters that were entered by users
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class ReceiveParameterFromRequest {
    /**
     * User parameters map.
     * Create map with user parameters that were entered on registration page
     *
     * @param request the request
     * @return the map
     */
    public static Map<String, String> userParameters(HttpServletRequest request) {
        Map<String, String> userParameters = new HashMap();
        userParameters.put(ParameterName.USER_LOGIN, request.getParameter(ParameterName.USER_LOGIN));
        userParameters.put(ParameterName.USER_PASSWORD, request.getParameter(ParameterName.USER_PASSWORD));
        userParameters.put(ParameterName.REPEATED_PASSWORD, request.getParameter(ParameterName.REPEATED_PASSWORD));
        userParameters.put(ParameterName.USER_NAME, request.getParameter(ParameterName.USER_NAME));
        userParameters.put(ParameterName.USER_SURNAME, request.getParameter(ParameterName.USER_SURNAME));
        String phone = CommonUtil.transformPhone(request.getParameter(ParameterName.USER_PHONE));
        userParameters.put(ParameterName.USER_PHONE, phone);
        userParameters.put(ParameterName.USER_EMAIL, request.getParameter(ParameterName.USER_EMAIL));
        userParameters.put(ParameterName.USER_BIRTHDAY, request.getParameter(ParameterName.USER_BIRTHDAY));
        return userParameters;
    }

    /**
     * Schedule parameters map.
     * Create map with schedule parameters that were entered on the add schedule page
     *
     * @param request the request
     * @return the map
     */
    public static Map<String, String> scheduleParameters(HttpServletRequest request) {
        Map<String, String> scheduleParameters = new HashMap();
        scheduleParameters.put(ParameterName.SCHEDULE_ID, (String) request.getSession().getAttribute(AttributeName.SCHEDULE_ID));
        scheduleParameters.put(ParameterName.SCHEDULE_WORKOUT_ID, request.getParameter(ParameterName.SCHEDULE_WORKOUT_ID));
        scheduleParameters.put(ParameterName.SCHEDULE_START_DATE, request.getParameter(ParameterName.SCHEDULE_START_DATE));
        scheduleParameters.put(ParameterName.SCHEDULE_START_TIME, request.getParameter(ParameterName.SCHEDULE_START_TIME));
        scheduleParameters.put(ParameterName.SCHEDULE_DURATION, request.getParameter(ParameterName.SCHEDULE_DURATION));
        scheduleParameters.put(ParameterName.SCHEDULE_CAPACITY, request.getParameter(ParameterName.SCHEDULE_CAPACITY));
        scheduleParameters.put(ParameterName.SCHEDULE_HALL_ID, request.getParameter(ParameterName.SCHEDULE_HALL_ID));
        scheduleParameters.put(ParameterName.SCHEDULE_INSTRUCTOR_ID, request.getParameter(ParameterName.SCHEDULE_INSTRUCTOR_ID));
        return scheduleParameters;
    }

    /**
     * Contact us parameters map.
     * Create map with user parameters that were entered on the contact us page
     *
     * @param request the request
     * @return the map
     */
    public static Map<String, String> contactUsParameters(HttpServletRequest request) {
        Map<String, String> contactUsParameters = new HashMap();
        contactUsParameters.put(ParameterName.CONTACT_US_NAME, request.getParameter(ParameterName.CONTACT_US_NAME));
        String phone = CommonUtil.transformPhone(request.getParameter(ParameterName.USER_PHONE));
        contactUsParameters.put(ParameterName.CONTACT_US_PHONE,phone);
        contactUsParameters.put(ParameterName.CONTACT_US_EMAIL, request.getParameter(ParameterName.CONTACT_US_EMAIL));
        contactUsParameters.put(ParameterName.CONTACT_US_MESSAGE, request.getParameter(ParameterName.CONTACT_US_MESSAGE));
        return contactUsParameters;
    }

    private ReceiveParameterFromRequest() {
    }
}