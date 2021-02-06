package com.github.wcosmedlr.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity(name = "Member")
@Data
@NoArgsConstructor
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @OneToMany(mappedBy="owner")
    private List<ExpenseEntity> expenses = new LinkedList<>();

}
