package com.github.wcosmedlr.models;

import java.util.Objects;

public class Balance implements Comparable<Balance>{
    private Member owner;
    private Double value;

    public Balance() {
        /*
         * This empty constructor allows HTTP decoding stream for type [interface java.util.List]
         * */
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Balance)) return false;
        Balance balance = (Balance) o;
        return Objects.equals(owner, balance.owner) &&
                Objects.equals(value, balance.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, value);
    }

    @Override
    public int compareTo(Balance o) {
        return this.getValue().compareTo(o.getValue());
    }

    public static class Builder<T extends Builder> {
        private Member owner;
        private Double value;

        public T setOwner(Member owner) {
            this.owner = owner;
            return (T) this;
        }

        public T setValue(Double value) {
            this.value = value;
            return (T) this;
        }

        public Balance build() {
            return new Balance(this);
        }

    }

    protected Balance(Builder<?> builder){
        this.owner = builder.owner;
        this.value = builder.value;
    }
}
