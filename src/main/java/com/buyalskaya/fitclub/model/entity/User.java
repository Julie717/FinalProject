package com.buyalskaya.fitclub.model.entity;

import java.time.LocalDate;
import java.util.StringJoiner;

/**
 * The type User.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class User extends CommonEntity {
    /**
     * The value is used for id user storage
     */
    private int idUser;
    /**
     * The value is used for login storage
     */
    private String login;
    /**
     * The value is used for role storage
     */
    private UserRole role;
    /**
     * The value is used for status storage
     */
    private UserStatus status;
    /**
     * The value is used for name storage
     */
    private String name;
    /**
     * The value is used for surname storage
     */
    private String surname;
    /**
     * The value is used for phone number storage
     */
    private String phoneNumber;
    /**
     * The value is used for email storage
     */
    private String email;
    /**
     * The value is used for birthday storage
     */
    private LocalDate birthday;
    /**
     * The value is used for photo storage in base64 format
     */
    private String photo;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param idUser the id user
     * @param role   the role
     * @param status the status
     */
    public User(int idUser, UserRole role, UserStatus status) {
        this.idUser = idUser;
        this.role = role;
        this.status = status;
    }

    /**
     * Instantiates a new User.
     *
     * @param login       the login
     * @param role        the role
     * @param status      the status
     * @param name        the name
     * @param surname     the surname
     * @param phoneNumber the phone number
     * @param email       the email
     * @param birthday    the birthday
     */
    public User(String login, UserRole role,
                UserStatus status, String name, String surname,
                String phoneNumber, String email, LocalDate birthday) {
        this.login = login;
        this.role = role;
        this.status = status;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
    }

    /**
     * Gets id user.
     *
     * @return the id user
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * Sets id user.
     *
     * @param idUser the id user
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets birthday.
     *
     * @return the birthday
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Sets birthday.
     *
     * @param birthday the birthday
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Gets photo.
     *
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Sets photo.
     *
     * @param photo the photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (idUser != user.idUser) {
            return false;
        }
        if (login != null ? !login.equals(user.login) : user.login != null) {
            return false;
        }
        if (role != user.role) {
            return false;
        }
        if (status != user.status) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) {
            return false;
        }
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) {
            return false;
        }
        return photo != null ? photo.equals(user.photo) : user.photo == null;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("idUser=" + idUser)
                .add("login='" + login + "'")
                .add("role=" + role)
                .add("status=" + status)
                .add("name='" + name + "'")
                .add("surname='" + surname + "'")
                .add("phoneNumber='" + phoneNumber + "'")
                .add("email='" + email + "'")
                .add("birthday=" + birthday)
                .add("photo='" + photo + "'")
                .toString();
    }
}