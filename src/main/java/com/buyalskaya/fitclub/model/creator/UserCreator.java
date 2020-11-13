package com.buyalskaya.fitclub.model.creator;

import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.model.entity.Staff;
import com.buyalskaya.fitclub.model.entity.User;
import com.buyalskaya.fitclub.model.entity.UserRole;
import com.buyalskaya.fitclub.model.entity.UserStatus;
import com.buyalskaya.fitclub.util.CommonUtil;
import com.buyalskaya.fitclub.util.DateTimeTransformer;

import java.time.LocalDate;
import java.util.Map;

public class UserCreator {

    private UserCreator() {
    }

    public static User createUserWithAllParameters(Map<String, String> userParameters, String locale) {
        String login = userParameters.get(ParameterName.USER_LOGIN);
        String name = userParameters.get(ParameterName.USER_NAME);
        String surname = userParameters.get(ParameterName.USER_SURNAME);
        String phone = userParameters.get(ParameterName.USER_PHONE);
        String email = userParameters.get(ParameterName.USER_EMAIL);
        String birthday = userParameters.get(ParameterName.USER_BIRTHDAY);
        LocalDate birthdayDate = DateTimeTransformer.fromStringToLocalDate(birthday, locale);
        User user = new User(login, UserRole.CLIENT, UserStatus.UNCONFIRMED,
                name, surname, phone, email, birthdayDate);
        return user;
    }

    public static Staff createStaffOrClient(Map<String, String> newParameters, String locale) {
        int idUser = Integer.parseInt(newParameters.get(ParameterName.USER_ID));
        int idRole = Integer.parseInt(newParameters.get(ParameterName.USER_ROLE));
        UserRole role = CommonUtil.findRole(idRole);
        int idStatus = Integer.parseInt(newParameters.get(ParameterName.USER_STATUS));
        UserStatus status = CommonUtil.findStatus(idStatus);
        Staff user;
        if (role == UserRole.CLIENT) {
            user = new Staff(idUser, role, status);
        } else {
            LocalDate startWorkingDate = DateTimeTransformer
                    .fromStringToLocalDate(newParameters.get(ParameterName.STAFF_START_WORKING_DATE), locale);
            LocalDate endWorkingDate = DateTimeTransformer
                    .fromStringToLocalDate(newParameters.get(ParameterName.STAFF_END_WORKING_DATE), locale);
            user = new Staff(idUser, role, status, startWorkingDate, endWorkingDate);
        }
        return user;
    }
}
