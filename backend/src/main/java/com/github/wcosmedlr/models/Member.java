package com.github.wcosmedlr.models;

import java.util.Objects;

public class Member {
    private Long id;
    private String name;
    private String surname;

    public Member() {
        /*
         * This empty constructor allows HTTP decoding stream for type [interface java.util.List]
         * */
    }

    public Member(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) &&
                Objects.equals(name, member.name) &&
                Objects.equals(surname, member.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    public static class Builder {
        private Long id;
        private String name;
        private String surname;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Member build() {
            return new Member(id, name, surname);
        }
    }
}
