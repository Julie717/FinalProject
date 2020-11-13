package com.buyalskaya.fitclub.util;

import com.buyalskaya.fitclub.model.entity.Schedule;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;

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

    private static final String MAIL_CANCEL_WORKOUT_SUBJECT = "mail.cancelWorkout.subject";
    private static final String WORKOUT = "mail.cancelWorkout.workout";
    private static final String INSTRUCTOR = "mail.cancelWorkout.instructor";
    private static final String MAIN_TEXT_CANCEL_WORKOUT = "mail.cancelWorkout.mainText";
    private static final String END_TEXT_CANCEL_WORKOUT = "mail.cancelWorkout.endText";

    private MailCreator() {
    }

    public static boolean sendMailToConfirmRegistration(String locale, String userEmail, String userName, String login) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(FILENAME_WITH_MESSAGES, new Locale(locale));
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
        mailText.append(PARAGRAPH);
        mailText.append(PARAGRAPH);
        mailText.append(resourceBundle.getString(END_TEXT));
        return MailSender.getINSTANCE().send(userEmail, mailSubject, mailText.toString());
    }

    public static boolean sendMailToCancelWorkout(String locale, String userEmail, String userName, Schedule schedule) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(FILENAME_WITH_MESSAGES, new Locale(locale));
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
        String language=locale.split(UNDERLINE)[0];
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
}