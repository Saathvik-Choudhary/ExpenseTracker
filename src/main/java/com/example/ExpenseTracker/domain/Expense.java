package com.example.ExpenseTracker.domain;

import com.example.ExpenseTracker.common.StringUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

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


    @Column(name = "cost", nullable = false, updatable = false)
    @NotNull(message = "The cost of the expense can not be null.")
    @Digits(integer = 10,fraction = 4,message = "The cost of the expense should contain {0} integers "+
                                                    "and {1} fractional digits.")
    @Positive(message = "The cost of the expense should be positive.")
    private BigDecimal cost;

    @Column(name = "currency", nullable = false, updatable = false)
    private Currency currency;

    @Column(name = "dateId", nullable = false, updatable = false)
    private int dateId;

    @Column(name = "purchaseDate", nullable = false, updatable = false)
    @PastOrPresent(message = "The date of the expense can only be in the past or the present.")
    private Date dateOfExpense;

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false, updatable = false)
    @NotNull(message = "The title of the asset can not be null.")
    @NotBlank(message = "The title of the asset can not be blank.")
    @NotEmpty(message = "The title of the expense can not be empty.")
    private String title;

    Expense(){
        super();
    }

    public Expense( final String title,
                    final BigDecimal cost,
                    final Date dateOfExpense,
                    final Currency currency) {
        this();
        setTitle(title);
        setCost(cost);
        setDateOfExpense(dateOfExpense);
        setDateId(dateOfExpense);
        setCurrency(currency);
    }

    /**
     * Get the cost of the expense.
     *
     * @return the cost of the expense.
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Get the currency of the expense.
     *
     * @return the currency of the expense.
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Get the date of the expense.
     *
     * @return the date of the expense.
     */
    public Date getDateOfExpense() {
        return dateOfExpense;
    }

    /**
     * Get the id of the expense.
     *
     * @return the id of the expense.
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the title of the expense.
     *
     * @return the title of the expense.
     */
    public String getTitle() {
        return title;
    }


    private void setCost(final BigDecimal cost) {
        if (cost==null) {
            throw new NullPointerException("The expense amount is mandatory.");
        }
        else if(cost.compareTo(BigDecimal.ZERO)<0) {
            throw new IllegalArgumentException("The cost of the expense cannot be less than.");
        }

        this.cost = cost;
    }

    private void setCurrency(Currency currency) {

        this.currency = currency;
    }

    private void setDateOfExpense(final Date dateOfExpense) {

        if(dateOfExpense==null) {
            throw new NullPointerException("The date of expense is mandatory");
        } else if (dateOfExpense.after(new Date())) {
            throw new IllegalArgumentException("The date of the expense cannot be in the future");
        }
        this.dateOfExpense = dateOfExpense;
    }

    private void setTitle(final String title) {

        if(StringUtil.isBlank(title)){
            throw new NullPointerException("The tile of the expense can not be blank");
        }

        this.title = title;
    }

    public int getDateId() {
        return dateId;
    }

    private void setDateId(final Date dateOfExpense) {
        Instant instant = dateOfExpense.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zonedDateTime.toLocalDate();

        this.dateId =localDate.getYear()*100 + localDate.getMonthValue();
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
