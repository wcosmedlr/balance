package com.github.wcosmedlr.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(setterPrefix = "set")
@NoArgsConstructor
public class Member implements Comparable<Member>{

    private Long id;
    private String name;
    private String surname;

    @Override
    public int compareTo(Member o) {
        return this.getName().compareTo(o.getName());
    }

}
