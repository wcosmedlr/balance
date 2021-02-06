package com.github.wcosmedlr.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Expense")
@Data
@NoArgsConstructor
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Date timeStamp;
    private Double value;

    @ManyToOne
    private MemberEntity owner;
}
