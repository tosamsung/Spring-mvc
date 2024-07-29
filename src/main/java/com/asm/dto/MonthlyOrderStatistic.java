package com.asm.dto;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
public class MonthlyOrderStatistic {
    private int month;
    private int totalOrders;

    // Constructors
    public MonthlyOrderStatistic() {}

    public MonthlyOrderStatistic(int month, int totalOrders) {
        this.month = month;
        this.totalOrders = totalOrders;
    }

    // Getters and Setters
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

   
}

