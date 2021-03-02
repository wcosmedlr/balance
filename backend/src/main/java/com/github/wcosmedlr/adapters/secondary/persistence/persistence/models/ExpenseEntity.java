package com.github.wcosmedlr.adapters.secondary.persistence.persistence.models;

import com.github.wcosmedlr.adapters.secondary.persistence.persistence.mappers.ExpenseMapper;
import com.github.wcosmedlr.domain.models.Expense;
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

    public ExpenseEntity(Expense expense){
        ExpenseMapper.INSTANCE.toDAO(expense, this);
    }

    public Expense toServiceModel(){
        this.setTimeStamp(new Date(this.getTimeStamp().getTime()));
        return ExpenseMapper.INSTANCE.toDBO(this);
    }
}
