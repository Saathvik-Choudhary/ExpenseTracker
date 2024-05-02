package com.example.ExpenseTracker.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.Date;


/**
 * Entity to store the expenses and the JPA table name is "expense"
 */
@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @Column(name = "id",nullable = false,updatable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "cost",nullable = false,updatable = false)
    @NotNull(message = "The cost of the expense can not be null")
    @Digits(integer = 10,fraction = 4,message = "The cost of the expense should contain {0} integers "+
                                                    "and {1} fractional digits")
    @Positive(message = "The cost of the expense should be positive")
    private BigDecimal cost;

    @Column(name = "currency",nullable = false,updatable = false)
    private Currency currency;

    @Column(name = "dateid",nullable = false,updatable = false)
    private int dateid;


    @Column(name = "purchaseDate",nullable = false,updatable = false)
    @PastOrPresent(message = "The date of the expense can only be in the past or the present")
    private Date dateOfExpense;

    @Column(name = "title",nullable = false,updatable = false)
    @NotNull(message = "The title of the asset can not be null")
    @NotBlank(message = "The title of the asset can not be blank")
    @NotEmpty(message = "The title of the expense can not be empty")
    private String title;

    private Expense(){
        super();
    }

    public Expense( final String title,
                    final BigDecimal cost,
                    final Date dateOfExpense,
                    final Currency currency) {
        setTitle(title);
        setCost(cost);
        setDateOfExpense(dateOfExpense);
        setDateid(dateOfExpense);
        setCurrency(currency);
    }

    /**
     * Get the cost of the expense
     *
     * @return the cost of the expense
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Get teh currency of the expense
     *
     * @return the currency of the expense
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Get the date  of the expense
     *
     * @return the date of the expense
     */
    public Date getDateOfExpense() {
        return dateOfExpense;
    }

    /**
     * Get the id of the expense
     *
     * @return the id of the expense
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the title of the expense
     *
     * @return the title of the expense
     */
    public String getTitle() {
        return title;
    }

    public void setCost(final BigDecimal cost) {
        if (cost==null) {
            throw new NullPointerException("The cost of the expense cannot be null");
        }
        else if(cost.compareTo(BigDecimal.ZERO)<0) {
            throw new IllegalArgumentException("The cost of the expense cannot be less than");
        }

        this.cost = cost;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setDateOfExpense(final Date dateOfExpense) {
        if(dateOfExpense==null) {
            throw new NullPointerException("The date of the expense cannot be null");
        } else if (dateOfExpense.after(new Date())) {
            throw new IllegalArgumentException("The date of the expense cannot be in the future");
        }
        this.dateOfExpense = dateOfExpense;
    }

    public void setTitle(final String title) {

        if(title==null){
            throw new NullPointerException("The title of the expense can not be null");
        }
        else if(title.trim().length()==0){
            throw new IllegalArgumentException("The title of the expense can not be blank");
        }

        this.title = title;
    }

    public int getDateid() {
        return dateid;
    }

    public void setDateid(final Date dateOfExpense) {
        Instant instant = dateOfExpense.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zonedDateTime.toLocalDate();

        this.dateid=localDate.getYear()*100 + localDate.getMonthValue();
    }



    /**
    @ManyToOne
    @JoinColumn(name = "year_month_id", nullable = false, updatable = false)
    private YearMonth date;

    public void setDate(final Date dateOfExpense) {

        Instant instant = dateOfExpense.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zonedDateTime.toLocalDate();

        this.date=new YearMonth(localDate.getYear(),localDate.getMonthValue());
    }

    public YearMonth getDate() {
        return date;
    }
    */
}
