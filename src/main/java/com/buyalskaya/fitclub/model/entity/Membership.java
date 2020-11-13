package com.buyalskaya.fitclub.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Membership extends CommonEntity{
    private int idMembership;
    private int duration;
    private int classesAmount;
    private BigDecimal price;
    private LocalDate startSale;
    private LocalDate endSale;
    private String typeByNumberPeople;
    private String periodTime;

    public int getIdMembership() {
        return idMembership;
    }

    public void setIdMembership(int idMembership) {
        this.idMembership = idMembership;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getClassesAmount() {
        return classesAmount;
    }

    public void setClassesAmount(int classesAmount) {
        this.classesAmount = classesAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getStartSale() {
        return startSale;
    }

    public void setStartSale(LocalDate startSale) {
        this.startSale = startSale;
    }

    public LocalDate getEndSale() {
        return endSale;
    }

    public void setEndSale(LocalDate endSale) {
        this.endSale = endSale;
    }

    public String getTypeByNumberPeople() {
        return typeByNumberPeople;
    }

    public void setTypeByNumberPeople(String typeByNumberPeople) {
        this.typeByNumberPeople = typeByNumberPeople;
    }

    public String getPeriodTime() {
        return periodTime;
    }

    public void setPeriodTime(String periodTime) {
        this.periodTime = periodTime;
    }
}
