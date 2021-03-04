package com.github.wcosmedlr.adapters.secondary.persistence.models;

import com.github.wcosmedlr.adapters.secondary.persistence.mappers.MemberMapper;
import com.github.wcosmedlr.domain.models.Member;
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

    public MemberEntity(Member member){
        MemberMapper.INSTANCE.toDAO(member, this);
    }

    public Member toServiceModel(){
        return MemberMapper.INSTANCE.toDBO(this);
    }
}
