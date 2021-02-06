package com.github.wcosmedlr.mothers

import com.github.wcosmedlr.dto.Expense

import javax.inject.Inject
import javax.inject.Singleton
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Singleton
class ExpenseMother<T extends Expense>{

    final private DateTimeFormatter formatter
    final private MemberMother memberMother;

    @Inject
    ExpenseMother(DateTimeFormatter formatter, MemberMother memberMother) {
        this.formatter = formatter
        this.memberMother = memberMother
    }

    T createValidExpense1() {
        return Expense.builder()
                .setDescription("Cena")
                .setValue(100D)
                .setTimeStamp(Timestamp.valueOf(LocalDateTime.parse("03-01-2021 11:30:40", formatter)))
                .setOwner(memberMother.createValidUser1())
                .build()
    }

    T createValidExpense2() {
        return Expense.builder()
                .setDescription("Taxi")
                .setValue(75)
                .setTimeStamp(Timestamp.valueOf(LocalDateTime.parse("29-02-2021 11:30:40", formatter)))
                .setOwner(memberMother.createValidUser2())
                .build()
    }

    T createValidExpense3() {
        return Expense.builder()
                .setDescription("Compra")
                .setValue(50)
                .setTimeStamp(Timestamp.valueOf(LocalDateTime.parse("02-02-2021 11:30:40", formatter)))
                .setOwner(memberMother.createValidUser3())
                .build()
    }

    T createValidExpense4() {
        return Expense.builder()
                .setDescription("Regalo")
                .setValue(15)
                .setTimeStamp(Timestamp.valueOf(LocalDateTime.parse("29-01-2021 11:30:40", formatter)))
                .setOwner(memberMother.createValidUser4())
                .build()
    }

    T createValidExpense5() {
        return Expense.builder()
                .setDescription("Hotel")
                .setValue(10)
                .setTimeStamp(Timestamp.valueOf(LocalDateTime.parse("04-02-2021 11:30:40", formatter)))
                .setOwner(memberMother.createValidUser4())
                .build()
    }

    List<T> createAListOfExpensesToCheckBalances() {
        T[] expenses = new Expense[] {
                createValidExpense1(),
                createValidExpense2(),
                createValidExpense3(),
                createValidExpense4(),
                createValidExpense5()
        };
        return Arrays.asList(expenses);
    }
}
