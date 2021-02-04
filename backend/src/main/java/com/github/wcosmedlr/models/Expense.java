package com.github.wcosmedlr.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Comparator;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Expense extends Balance {
    private Long id;
    private String description;

    private Date timeStamp;

    public static final Comparator<Expense> compareByTimeStamp = Comparator.comparing(Expense::getTimeStamp);

    public Expense() {
        /*
        * This empty constructor allows HTTP decoding stream for type [interface java.util.List]
        * */
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expense)) return false;
        if (!super.equals(o)) return false;
        Expense expense = (Expense) o;
        return Objects.equals(id, expense.id) &&
                Objects.equals(description, expense.description) &&
                Objects.equals(timeStamp, expense.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, description, timeStamp);
    }

    public static class Builder extends Balance.Builder<Builder>{
        private Long id;
        private String description;
        private Date timeStamp;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setTimeStamp(Date timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        @Override
        public Expense build() {
            return new Expense(this);
        }
    }

    protected Expense(Builder builder) {
        super(builder);
        this.id = builder.id;
        this.description = builder.description;
        this.timeStamp = builder.timeStamp;
    }
}
