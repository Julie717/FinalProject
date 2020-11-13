package com.buyalskaya.fitclub.tag;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.model.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;

@SuppressWarnings("serial")
public class ScheduleBox extends TagSupport {
    private final static String FILE_NAME = "prop.pagecontent";
    private final static String WORD_FREE = "schedule.wordFree";
    private final static String WORD_FROM = "schedule.wordFrom";
    private final static String BUTTON_LABEL_CHECK = "schedule.buttonCheck";
    private final static String BUTTON_LABEL_UNCHECK = "schedule.buttonUncheck";
    private final static String BUTTON_LABEL_ADMIN_CHECK = "schedule.buttonAdminCheck";
    private final static char SPACE = ' ';
    private final static String UNDERLINE = "_";
    private String nameWorkout;
    private String nameInstructor;
    private String surnameInstructor;
    private LocalDate date;
    private LocalTime time;
    private int capacity;
    private int freeCapacity;
    private String subscribed;

    public ScheduleBox() {
        this.freeCapacity = 0;
        this.capacity = 0;
    }

    public void setNameWorkout(String nameWorkout) {
        this.nameWorkout = nameWorkout;
    }

    public void setNameInstructor(String nameInstructor) {
        this.nameInstructor = nameInstructor;
    }

    public void setSurnameInstructor(String surnameInstructor) {
        this.surnameInstructor = surnameInstructor;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setFreeCapacity(int freeCapacity) {
        this.freeCapacity = freeCapacity;
    }

    public void setSubscribed(String subscribed) {
        this.subscribed = subscribed;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            String locale = (String) pageContext.getSession().getAttribute(AttributeName.SESSION_LOCALE);
            String[] localeArray = locale.split(UNDERLINE);
            String language = localeArray[0];
            String country = localeArray[1];
            ResourceBundle resourceBundle = ResourceBundle.getBundle(FILE_NAME, new Locale(language, country));
            String day = date.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale(language));
            String dateString = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                    .withLocale(new Locale(language)).format(date);
            JspWriter out = pageContext.getOut();
            out.write("<div class=\"box schedule\">");
            out.write("<p class=\"date\">" + dateString + "</p>");
            out.write("<p class=\"time\">" + time + "</p>");
            out.write("<p class=\"day\"><strong>" + day.toLowerCase() + "</strong><br></p>");
            out.write("<div class=\"box\">");
            if (nameWorkout != null) {
                out.write("<a class=\"text-uppercase workout_name\"><strong>" + nameWorkout + "</strong><br></a>");
            }
            if (surnameInstructor != null && nameInstructor != null) {
                out.write("<a class=\"instructor\">" + surnameInstructor + SPACE + nameInstructor + "<br></a>");
            }
            if (capacity > 0) {
                String wordFree = resourceBundle.getString(WORD_FREE);
                String wordFrom = resourceBundle.getString(WORD_FROM);
                out.write("<p class=\"free_workout\">");
                out.write(wordFree + SPACE + freeCapacity + SPACE + wordFrom + SPACE + capacity + "</p>");
            }
            if (nameWorkout != null && surnameInstructor != null && nameInstructor != null && capacity > 0) {
                User user = (User) pageContext.getSession().getAttribute(AttributeName.SESSION_USER);
                if (user != null) {
                    String buttonLabel;
                    switch (user.getRole()) {
                        case ADMINISTRATOR:
                            buttonLabel = resourceBundle.getString(BUTTON_LABEL_ADMIN_CHECK);
                            out.write("<button class=\"btn btn-primary subscribe\" type=\"submit\">" +
                                    buttonLabel + "</button>");
                            break;
                        case CLIENT:
                            String backgroundColor;
                            if (subscribed != null && subscribed.equals("true")) {
                                buttonLabel = resourceBundle.getString(BUTTON_LABEL_UNCHECK);
                                backgroundColor = "purple";
                            } else {
                                buttonLabel = resourceBundle.getString(BUTTON_LABEL_CHECK);
                                backgroundColor = "#0062cc";
                            }
                            out.write("<button class=\"btn btn-primary subscribe\" type=\"submit\"" +
                                    "style=\"background-color:" + backgroundColor + "\">" +
                                    buttonLabel + "</button>");
                    }
                }
            }
            out.write("</div></div>");
        } catch (
                IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }
}