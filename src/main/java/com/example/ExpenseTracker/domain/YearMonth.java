package com.example.ExpenseTracker.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "yearMonth")
public class YearMonth {

    @Column(name = "year", nullable = false, updatable = false)
    private short year;

    @Column(name = "month", nullable = false, updatable = false)
    private short month;

    @Id
    @Column(name = "yearmonth", nullable = false, updatable = false)
    private Integer yearMonth;

    public YearMonth(short year, short month, Integer yearMonth) {
        this.year = year;
        this.month = month;
        this.yearMonth = yearMonth;
    }

    public short getYear() {
        return year;
    }

    public short getMonth() {
        return month;
    }

    public int getYearMonth() {
        return yearMonth;
    }
}
