package com.buyalskaya.fitclub.util;

import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.model.entity.Schedule;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * The type Mail creator.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class MailCreator {
    private static final String FILENAME_WITH_MESSAGES = "prop.mail_message";
    private static final String MAIL_SUBJECT = "mail.confirmRegistration.subject";
    private static final String HELLO = "mail.confirmRegistration.hello";
    private static final String MAIN_TEXT = "mail.confirmRegistration.mainText";
    private static final String END_TEXT = "mail.confirmRegistration.endText";
    private static final String EXCLAMATION_MARK = "!";
    private static final String PARAGRAPH = "\n";
    private static final String SPACE = " ";
    private final static String UNDERLINE = "_";
    private static final String CONFIRM_LINK = "http://localhost:8080/controller?" +
            "commandName=confirm_registration&login=";
    private static final String CONFIRM_LINK_LANGUAGE = "&language=";

    private static final String MAIL_CANCEL_WORKOUT_SUBJECT = "mail.cancelWorkout.subject";
    private static final String WORKOUT = "mail.cancelWorkout.workout";
    private static final String INSTRUCTOR = "mail.cancelWorkout.instructor";
    private static final String MAIN_TEXT_CANCEL_WORKOUT = "mail.cancelWorkout.mainText";
    private static final String END_TEXT_CANCEL_WORKOUT = "mail.cancelWorkout.endText";

    private static final String MAIL_UPDATE_WORKOUT_SUBJECT = "mail.updateWorkout.subject";
    private static final String PREVIOUS_DATE = "mail.updateWorkout.workout";
    private static final String NEXT_DATE = "mail.updateWorkout.newDate";
    private static final String ADDITIONAL_INFO = "mail.updateWorkout.add";
    private static final String END_TEXT_UPDATE_WORKOUT = "mail.updateWorkout.endText";

    private static final String MAIL_TO_ADMIN_SUBJECT = "mail.toAdmin.subject";
    private static final String CONTACT_US_NAME = "mail.toAdmin.name";
    private static final String CONTACT_US_PHONE = "mail.toAdmin.phone";
    private static final String CONTACT_US_EMAIL = "mail.toAdmin.email";
    private static final String CONTACT_US_MESSAGE = "mail.toAdmin.message";

    private MailCreator() {
    }

    /**
     * Send mail to confirm registration boolean.
     * Is used to send message after registration. This message consists of
     * URL following which user confirm registration
     *
     * @param locale    the locale
     * @param userEmail the user email
     * @param userName  the user name
     * @param login     the login
     * @return the boolean
     */
    public static boolean sendMailToConfirmRegistration(String locale, String userEmail, String userName, String login) {
        String[] localeArray = locale.split(UNDERLINE);
        String language = localeArray[0];
        String country = localeArray[1];
        ResourceBundle resourceBundle = ResourceBundle.getBundle(FILENAME_WITH_MESSAGES, new Locale(language, country));
        String mailSubject = resourceBundle.getString(MAIL_SUBJECT);
        StringBuilder mailText = new StringBuilder();
        mailText.append(resourceBundle.getString(HELLO));
        mailText.append(SPACE);
        mailText.append(userName);
        mailText.append(EXCLAMATION_MARK);
        mailText.append(PARAGRAPH);
        mailText.append(resourceBundle.getString(MAIN_TEXT));
        mailText.append(PARAGRAPH);
        mailText.append(CONFIRM_LINK);
        mailText.append(login);
        mailText.append(CONFIRM_LINK_LANGUAGE);
        mailText.append(language.toLowerCase());
        mailText.append(PARAGRAPH);
        mailText.append(PARAGRAPH);
        mailText.append(resourceBundle.getString(END_TEXT));
        return MailSender.getINSTANCE().send(userEmail, mailSubject, mailText.toString());
    }

    /**
     * Send mail to cancel workout boolean.
     * Is used to send message for a client when a workout was canceled
     *
     * @param locale    the locale
     * @param userEmail the user email
     * @param userName  the user name
     * @param schedule  the schedule
     * @return the boolean
     */
    public static boolean sendMailToCancelWorkout(String locale, String userEmail, String userName, Schedule schedule) {
        String[] localeArray = locale.split(UNDERLINE);
        String language = localeArray[0];
        String country = localeArray[1];
        ResourceBundle resourceBundle = ResourceBundle.getBundle(FILENAME_WITH_MESSAGES, new Locale(language, country));
        String mailSubject = resourceBundle.getString(MAIL_CANCEL_WORKOUT_SUBJECT);
        StringBuilder mailText = new StringBuilder();
        mailText.append(resourceBundle.getString(HELLO));
        mailText.append(SPACE);
        mailText.append(userName);
        mailText.append(EXCLAMATION_MARK);
        mailText.append(PARAGRAPH);
        mailText.append(resourceBundle.getString(WORKOUT));
        mailText.append(SPACE);
        mailText.append(schedule.getWorkout().getName());
        mailText.append(SPACE);
        String date = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                .withLocale(new Locale(language)).format(schedule.getStartDate());
        String time = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(new Locale(language)).format(schedule.getStartTime());
        mailText.append(date);
        mailText.append(SPACE);
        mailText.append(time);
        mailText.append(SPACE);
        mailText.append(resourceBundle.getString(INSTRUCTOR));
        mailText.append(SPACE);
        mailText.append(schedule.getSurnameInstructor());
        mailText.append(SPACE);
        mailText.append(schedule.getNameInstructor());
        mailText.append(SPACE);
        mailText.append(resourceBundle.getString(MAIN_TEXT_CANCEL_WORKOUT));
        mailText.append(PARAGRAPH);
        mailText.append(PARAGRAPH);
        mailText.append(PARAGRAPH);
        mailText.append(resourceBundle.getString(END_TEXT_CANCEL_WORKOUT));
        return MailSender.getINSTANCE().send(userEmail, mailSubject, mailText.toString());
    }

    /**
     * Send mail to update workout boolean.
     * Is used to send message for a client when date or time of a workout was changed
     *
     * @param locale          the locale
     * @param userEmail       the user email
     * @param userName        the user name
     * @param schedule        the schedule
     * @param currentSchedule the current schedule
     * @return the boolean
     */
    public static boolean sendMailToUpdateWorkout(String locale, String userEmail, String userName, Schedule schedule,
                                                  Schedule currentSchedule) {
        String[] localeArray = locale.split(UNDERLINE);
        String language = localeArray[0];
        String country = localeArray[1];
        ResourceBundle resourceBundle = ResourceBundle.getBundle(FILENAME_WITH_MESSAGES, new Locale(language, country));
        String mailSubject = resourceBundle.getString(MAIL_UPDATE_WORKOUT_SUBJECT);
        StringBuilder mailText = new StringBuilder();
        mailText.append(resourceBundle.getString(HELLO));
        mailText.append(SPACE);
        mailText.append(userName);
        mailText.append(EXCLAMATION_MARK);
        mailText.append(PARAGRAPH);
        mailText.append(resourceBundle.getString(PREVIOUS_DATE));
        mailText.append(SPACE);
        String date = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                .withLocale(new Locale(language)).format(currentSchedule.getStartDate());
        String time = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(new Locale(language)).format(currentSchedule.getStartTime());
        mailText.append(date);
        mailText.append(SPACE);
        mailText.append(time);
        mailText.append(SPACE);
        mailText.append(resourceBundle.getString(NEXT_DATE));
        mailText.append(SPACE);
        date = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                .withLocale(new Locale(language)).format(schedule.getStartDate());
        time = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(new Locale(language)).format(schedule.getStartTime());
        mailText.append(date);
        mailText.append(SPACE);
        mailText.append(time);
        mailText.append(resourceBundle.getString(ADDITIONAL_INFO));
        mailText.append(PARAGRAPH);
        mailText.append(PARAGRAPH);
        mailText.append(PARAGRAPH);
        mailText.append(resourceBundle.getString(END_TEXT_UPDATE_WORKOUT));
        return MailSender.getINSTANCE().send(userEmail, mailSubject, mailText.toString());
    }

    /**
     * Send mail to admin boolean.
     * Is used to send message from user to administrator
     *
     * @param userEmail         the user email
     * @param contactParameters the contact parameters
     * @param locale            the locale
     * @return the boolean
     */
    public static boolean sendMailToAdmin(String userEmail, Map<String, String> contactParameters, String locale) {
        String[] localeArray = locale.split(UNDERLINE);
        String language = localeArray[0];
        String country = localeArray[1];
        ResourceBundle resourceBundle = ResourceBundle.getBundle(FILENAME_WITH_MESSAGES, new Locale(language, country));
        String mailSubject = resourceBundle.getString(MAIL_TO_ADMIN_SUBJECT);
        String name = contactParameters.get(ParameterName.CONTACT_US_NAME);
        String phone = contactParameters.get(ParameterName.CONTACT_US_PHONE);
        String email = contactParameters.get(ParameterName.CONTACT_US_EMAIL);
        String message = contactParameters.get(ParameterName.CONTACT_US_MESSAGE);
        StringBuilder mailText = new StringBuilder();
        mailText.append(resourceBundle.getString(CONTACT_US_NAME));
        mailText.append(SPACE);
        mailText.append(name);
        mailText.append(PARAGRAPH);
        mailText.append(resourceBundle.getString(CONTACT_US_PHONE));
        mailText.append(SPACE);
        mailText.append(phone);
        mailText.append(PARAGRAPH);
        mailText.append(resourceBundle.getString(CONTACT_US_EMAIL));
        mailText.append(SPACE);
        mailText.append(email);
        mailText.append(PARAGRAPH);
        mailText.append(resourceBundle.getString(CONTACT_US_MESSAGE));
        mailText.append(PARAGRAPH);
        mailText.append(message);
        return MailSender.getINSTANCE().send(userEmail, mailSubject, mailText.toString());
    }
}