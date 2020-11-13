package com.buyalskaya.fitclub.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class UserValidatorTest {

    @DataProvider(name = "dataForIsLoginValid")
    public Object[][] dataForIsLoginValid() {
        return new Object[][]{
                {"malyshko_s", true},
                {"fit_club13", true},
                {"t14.3", true},
                {"liliya-98", true},
                {"1rosa", false},
                {"ann", false},
                {"_miss", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsLoginValid")
    public void isLoginValidTest(String login, boolean expected) {
        boolean actual = UserValidator.isLoginValid(login);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsPasswordValid")
    public Object[][] dataForIsPasswordValid() {
        return new Object[][]{
                {"tyuIop74r1", true},
                {"ugt99yc_t$rT", true},
                {"001100_uytreWq", true},
                {"tre(Wq0987)", true},
                {"qwe123456", false},
                {"qW123", false},
                {"poiuyTre", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsPasswordValid")
    public void isPasswordValidTest(String password, boolean expected) {
        boolean actual = UserValidator.isPasswordValid(password);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsNameValid")
    public Object[][] dataForIsNameValid() {
        return new Object[][]{
                {"Anna", true},
                {"julie", true},
                {"ян", true},
                {"Анастасия", true},
                {"Катя1", false},
                {"Анна-Мария", false},
                {"Jane_7", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsNameValid")
    public void isNameValidTest(String name, boolean expected) {
        boolean actual = UserValidator.isNameValid(name);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsSurnameValid")
    public Object[][] dataForIsSurnameValid() {
        return new Object[][]{
                {"Ivanova", true},
                {"Petrov", true},
                {"сидоров", true},
                {"СИдорова-Иванова", true},
                {"Иванова_сидорова", false},
                {"Сидорова Иванова", false},
                {"Petrov_12", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsSurnameValid")
    public void isSurnameValidTest(String surname, boolean expected) {
        boolean actual = UserValidator.isSurnameValid(surname);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsPhoneValid")
    public Object[][] dataForIsPhoneValid() {
        return new Object[][]{
                {"+375295584533", true},
                {"+375-29-5584533", false},
                {"+375-29-558-45-33", false},
                {"+375(29)5584533", false},
                {"8(029)5584533", false},
                {"375295563589", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsPhoneValid")
    public void isPhoneValidTest(String phone, boolean expected) {
        boolean actual = UserValidator.isPhoneValid(phone);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsEmailValid")
    public Object[][] dataForIsEmailValid() {
        return new Object[][]{
                {"ivanov_iv@gmail.com", true},
                {"sidorov.ki2020@ya.ru", true},
                {"misha123.yv@mail.ru", true},
                {"misha123.yvmail.ru", false},
                {"misha123.yv@mail", false},
                {"misha123", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsEmailValid")
    public void isEmailValidTest(String email, boolean expected) {
        boolean actual = UserValidator.isEmailValid(email);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsDescriptionValid")
    public Object[][] dataForIsDescriptionValid() {
        return new Object[][]{
                {"Надежда закончила Полоцкий государственный университет по специальности «Практическая психология. Английский язык». За ее плечами также курсы по пилатесу и более десяти лет занятий восточными танцами: девушка не только участвовала и побеждала в городских и областных конкурсах, но и преподавала их в одном из новополоцких фитнес-клубов. Также Надежда получила комплексную программу дополнительного образования \"Фитнес-инструктор групповых программ\" (частное предприятие \"Фитнес-профи\"), прошла обучение \"Инструктор по фитнесу\" в ЧУП \"Эффект-лайн\" (пилатес, калланетика, фитнес-йога), участвовала в конвенции \"Сезоны Грантелло\", конвенции по аквааэробике и обучающем семинаре \"Стретчинг в работе инструктора групповых программ\" (Grantello fitness school).", true},
                {"", true},
                {"<script src=\"data:&comma;alert(1)// \"><script src=data:&comma;alert(1)// ",false},
                {null, false},
                {"Надежда закончила Полоцкий государственный университет по специальности «Практическая психология. Английский язык». За ее плечами также курсы по пилатесу и более десяти лет занятий восточными танцами: девушка не только участвовала и побеждала в городских и областных конкурсах, но и преподавала их в одном из новополоцких фитнес-клубов. Также Надежда получила комплексную программу дополнительного образования \"Фитнес-инструктор групповых программ\" (частное предприятие \"Фитнес-профи\"), прошла обучение \"Инструктор по фитнесу\" в ЧУП \"Эффект-лайн\" (пилатес, калланетика, фитнес-йога), участвовала в конвенции \"Сезоны Грантелло\", конвенции по аквааэробике и обучающем семинаре \"Стретчинг в работе инструктора групповых программ\" (Grantello fitness school). Надежда закончила Полоцкий государственный университет по специальности «Практическая психология. Английский язык». За ее плечами также курсы по пилатесу и более десяти лет занятий восточными танцами: девушка не только участвовала и побеждала в городских и областных конкурсах, но и преподавала их в одном из новополоцких фитнес-клубов. Также Надежда получила комплексную программу дополнительного образования \"Фитнес-инструктор групповых программ\" (частное предприятие \"Фитнес-профи\"), прошла обучение \"Инструктор по фитнесу\" в ЧУП \"Эффект-лайн\" (пилатес, калланетика, фитнес-йога), участвовала в конвенции \"Сезоны Грантелло\", конвенции по аквааэробике и обучающем семинаре \"Стретчинг в работе инструктора групповых программ\" (Grantello fitness school). Надежда закончила Полоцкий государственный университет по специальности «Практическая психология. Английский язык». За ее плечами также курсы по пилатесу и более десяти лет занятий восточными танцами: девушка не только участвовала и побеждала в городских и областных конкурсах, но и преподавала их в одном из новополоцких фитнес-клубов. Также Надежда получила комплексную программу дополнительного образования \"Фитнес-инструктор групповых программ\" (частное предприятие \"Фитнес-профи\"), прошла обучение \"Инструктор по фитнесу\" в ЧУП \"Эффект-лайн\" (пилатес, калланетика, фитнес-йога), участвовала в конвенции \"Сезоны Грантелло\", конвенции по аквааэробике и обучающем семинаре \"Стретчинг в работе инструктора групповых программ\" (Grantello fitness school). Надежда закончила Полоцкий государственный университет по специальности «Практическая психология. Английский язык». За ее плечами также курсы по пилатесу и более десяти лет занятий восточными танцами: девушка не только участвовала и побеждала в городских и областных конкурсах, но и преподавала их в одном из новополоцких фитнес-клубов. Также Надежда получила комплексную программу дополнительного образования \"Фитнес-инструктор групповых программ\" (частное предприятие \"Фитнес-профи\"), прошла обучение \"Инструктор по фитнесу\" в ЧУП \"Эффект-лайн\" (пилатес, калланетика, фитнес-йога), участвовала в конвенции \"Сезоны Грантелло\", конвенции по аквааэробике и обучающем семинаре \"Стретчинг в работе инструктора групповых программ\" (Grantello fitness school). Надежда закончила Полоцкий государственный университет по специальности «Практическая психология. Английский язык». За ее плечами также курсы по пилатесу и более десяти лет занятий восточными танцами: девушка не только участвовала и побеждала в городских и областных конкурсах, но и преподавала их в одном из новополоцких фитнес-клубов. Также Надежда получила комплексную программу дополнительного образования \"Фитнес-инструктор групповых программ\" (частное предприятие \"Фитнес-профи\"), прошла обучение \"Инструктор по фитнесу\" в ЧУП \"Эффект-лайн\" (пилатес, калланетика, фитнес-йога), участвовала в конвенции \"Сезоны Грантелло\", конвенции по аквааэробике и обучающем семинаре \"Стретчинг в работе инструктора групповых программ\" (Grantello fitness school).", false}
        };
    }

    @Test(dataProvider = "dataForIsDescriptionValid")
    public void isDescriptionValidTest(String description, boolean expected) {
        boolean actual = UserValidator.isDescriptionValid(description);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsWorkExperienceValid")
    public Object[][] dataForIsWorkExperienceValid() {
        return new Object[][]{
                {"19", true},
                {"4", true},
                {"0", true},
                {"65", false},
                {"-1", false},
                {"-1.1", false},
                {"1.1", false},
                {"1.", false},
                {"1o", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsWorkExperienceValid")
    public void isWorkExperienceValidTest(String workExperience, boolean expected) {
        boolean actual = UserValidator.isWorkExperienceValid(workExperience);
        assertEquals(actual, expected);
    }


    @DataProvider(name = "dataForIsIdStatusValid")
    public Object[][] dataForIsIdStatusValid() {
        return new Object[][]{
                {"1", true},
                {"3", true},
                {"0", false},
                {"4", false},
                {"-1", false},
                {"-1.1", false},
                {"1.1", false},
                {"1.", false},
                {"1o", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsIdStatusValid")
    public void isIdStatusValidTest(String idStatus, boolean expected) {
        boolean actual = UserValidator.isIdStatusValid(idStatus);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsIdRoleValid")
    public Object[][] dataForIsIdRoleValid() {
        return new Object[][]{
                {"2", true},
                {"3", true},
                {"0", false},
                {"4", false},
                {"-10", false},
                {"-1.1", false},
                {"1.1", false},
                {"1o", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsIdRoleValid")
    public void isIdRoleValidTest(String idRole, boolean expected) {
        boolean actual = UserValidator.isIdStatusValid(idRole);
        assertEquals(actual, expected);
    }

    @Test
    public void isUserParametersForChangeRoleOrStatusValidPositiveTest1() {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("userRole", "3");
        userParameters.put("userStatus", "1");
        userParameters.put("startWorkingDate", null);
        userParameters.put("endWorkingDate", "");
        boolean actual = UserValidator.isUserParametersForChangeRoleOrStatusValid(userParameters);
        assertEquals(actual, true);
    }

    @Test
    public void isUserParametersForChangeRoleOrStatusValidPositiveTest2() {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("userRole", "2");
        userParameters.put("userStatus", "1");
        userParameters.put("startWorkingDate", "01.12.2020");
        userParameters.put("endWorkingDate", null);
        boolean actual = UserValidator.isUserParametersForChangeRoleOrStatusValid(userParameters);
        assertEquals(actual, true);
    }

    @Test
    public void isUserParametersForChangeRoleOrStatusValidPositiveTest3() {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("userRole", "1");
        userParameters.put("userStatus", "2");
        userParameters.put("startWorkingDate", "01/01/2020");
        userParameters.put("endWorkingDate", "");
        boolean actual = UserValidator.isUserParametersForChangeRoleOrStatusValid(userParameters);
        assertEquals(actual, true);
    }

    @Test
    public void isUserParametersForChangeRoleOrStatusValidNegativeTest1() {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("userStatus", "2");
        userParameters.put("startWorkingDate", "01/01/2020");
        boolean actual = UserValidator.isUserParametersForChangeRoleOrStatusValid(userParameters);
        assertEquals(actual, false);
    }

    @Test
    public void isUserParametersForChangeRoleOrStatusValidNegativeTest2() {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("userRole", "1");
        userParameters.put("userStatus", "2");
        boolean actual = UserValidator.isUserParametersForChangeRoleOrStatusValid(userParameters);
        assertEquals(actual, false);
    }

    @Test
    public void isStaffUpdateParametersValidPositiveTest1() {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("workExperience", "2");
        userParameters.put("staffDescription", "Инструктор групповых занятий. Инструктор групповых программ категории Мастер, персональный тренер.");
        boolean actual = UserValidator.isStaffUpdateParametersValid(userParameters);
        assertEquals(actual, true);
    }

    @Test
    public void isStaffUpdateParametersValidPositiveTest3() {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("workExperience", "7");
        userParameters.put("staffDescription","sdsgsdg");
        boolean actual = UserValidator.isStaffUpdateParametersValid(userParameters);
        assertEquals(actual, true);
    }

    @Test
    public void isStaffUpdateParametersValidNegativeTest1() {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("workExperience", "2");
        userParameters.put("staffDescription", "Amy normally hated Monday mornings, but this year was different. Kamal was in her art class and she liked Kamal. She was waiting outside the classroom when her friend Tara arrived.\n" +
                "“Hi Amy! Your mum sent me a text. You forgot your inhaler. Why don’t you turn your phone on?” Amy didn’t like technology. She never sent text messages and she hated Facebook too.\n" +
                "“Did Kamal ask you to the disco?” Tara was Amy’s best friend, and she wanted to know everything that was happening in Amy’s life. “I don’t think he likes me,” said Amy. “And I never see him alone. He’s always with Grant.” Amy and Tara didn’t like Grant.\n" +
                "“Do you know about their art project?” asked Amy. “It’s about graffiti, I think,” said Tara. “They’re working on it at the old house behind the factory.” “But that building is dangerous,” said Amy. “Aah, are you worried he’s going to get hurt?\" Tara teased. “Shut up, Tara! Hey look, here they come!”\n" +
                "Kamal and Grant arrived. “Hi Kamal!” said Tara. “Are you going to the Halloween disco tomorrow?” “Yes. Hi Amy,” Kamal said, smiling. “Do you want to come and see our paintings after school?” “I’m coming too!” Tara insisted.\n" +
                "After school, Kamal took the girls to the old house. It was very old and very dirty too. There was rubbish everywhere. The windows were broken and the walls were damp. It was scary. Amy didn’t like it. There were paintings of zombies and skeletons on the walls. “We’re going to take photos for the school art competition,” said Kamal. Amy didn’t like it but she didn’t say anything. “Where’s Grant?” asked Tara. “Er, he’s buying more paint.” Kamal looked away quickly. Tara thought he looked suspicious. “It’s getting dark, can we go now?” said Amy. She didn’t like zombies.\n" +
                "Then, they heard a loud noise coming from a cupboard in the corner of the room. “What’s that?” Amy was frightened. “I didn’t hear anything,” said Kamal. Something was making strange noises. There was something inside the cupboard. “Oh no! What is it?” Amy was very frightened now. “What do you mean? There’s nothing there!” Kamal was trying not to smile. Suddenly the door opened with a bang and a zombie appeared, shouting and moving its arms. Amy screamed and covered her eyes. “Oh very funny, Grant!” said Tara looking bored. Kamal and Grant started laughing. “Ha ha, you were frightened!” said Grant. \"Do you like my zombie costume?” Amy took Tara’s arm. “I can’t breathe,” she said. Kamal looked worried now. “Is she OK? It was only a joke.” “No she’s not OK, you idiot. She’s having an asthma attack and she forgot her inhaler.” Tara took out her phone. “I’m calling her dad.”\n" +
                "Next evening was Halloween. The girls were at the school disco. “Are you better now?” asked Tara. “I’m fine,” said Amy. “I think it was the smell of paint that started the attack.” Tara looked around. “So, where are the zombies?” “I don’t know, I don’t want to see Kamal again,” said Amy. “Come on, let’s dance!”");
        boolean actual = UserValidator.isStaffUpdateParametersValid(userParameters);
        assertEquals(actual, false);
    }

    @Test
    public void isStaffUpdateParametersValidNegativeTest2() {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("workExperience", "65");
        boolean actual = UserValidator.isStaffUpdateParametersValid(userParameters);
        assertEquals(actual, false);
    }

    @Test
    public void isUserUpdateParametersValidPositiveTest1() {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("login", "lolod_2");
        boolean actual = UserValidator.isUserUpdateParametersValid(userParameters);
        assertEquals(actual, true);
    }

    @Test
    public void isUserUpdateParametersPositiveTest2() {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("login", "lolod_2");
        userParameters.put("name", "Ivan");
        userParameters.put("workExperience", "7");
        boolean actual = UserValidator.isStaffUpdateParametersValid(userParameters);
        assertEquals(actual, true);
    }

    @Test
    public void isUserUpdateParametersValidNegativeTest1() {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("login", null);
        boolean actual = UserValidator.isUserUpdateParametersValid(userParameters);
        assertEquals(actual, false);
    }

    @Test
    public void isUserUpdateParametersNegativeTest2() {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("birthday", "1/05/2020");
        boolean actual = UserValidator.isUserUpdateParametersValid(userParameters);
        assertEquals(actual, false);
    }

    @Test
    public void isUserParametersPositiveTest() {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("login", "ivan_2");
        userParameters.put("password", "qwertY123");
        userParameters.put("repeatedPassword", "qwertY123");
        userParameters.put("name", "Ivan");
        userParameters.put("surname", "Ivanov");
        userParameters.put("phone", "+375298745627");
        userParameters.put("email", "ivan_14@tut.by");
        userParameters.put("birthday", "12.12.1989");
        boolean actual = UserValidator.isUserParametersValid(userParameters);
        assertEquals(actual, true);
    }

    @Test
    public void isUserUserParametersNegativeTest() {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put("login", "ivan_2");
        userParameters.put("password", "qwertY123");
        userParameters.put("repeatedPassword", "wertY123tr");
        userParameters.put("name", "Ivan");
        userParameters.put("surname", "Ivanov");
        userParameters.put("phone", "+375298745627");
        userParameters.put("email", "ivan_14@tut.by");
        userParameters.put("birthday", "12.12.1989");
        boolean actual = UserValidator.isUserParametersValid(userParameters);
        assertEquals(actual, false);
    }
}