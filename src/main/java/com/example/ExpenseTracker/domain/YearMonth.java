package com.example.ExpenseTracker.domain;

import jakarta.persistence.*;

public class YearMonth {}

/**
@Entity
@Table(name = "YEAR_MONTH")
public class YearMonth {


    @Id
    //@ManyToOne
    @Column(name = "id",nullable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "year",nullable = false,updatable = false)
    private int year;

    @Column(name = "month",nullable = false,updatable = false)
    private int month;
    public YearMonth(final int year, final int month) {

        this.year = year;
        this.month = month;
    }

    public Long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

}
 */
