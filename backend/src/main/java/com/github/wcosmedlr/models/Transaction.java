package com.github.wcosmedlr.models;

import java.util.Objects;

public class Transaction extends Balance {
    private Member benefactor;

    public Transaction() {
        /*
         * This empty constructor allows HTTP decoding stream for type [interface java.util.List]
         * */
    }

    public Member getBenefactor() {
        return benefactor;
    }

    public void setBenefactor(Member benefactor) {
        this.benefactor = benefactor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        if (!super.equals(o)) return false;
        Transaction transaction = (Transaction) o;
        return Objects.equals(benefactor, transaction.benefactor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), benefactor);
    }

    public static class Builder extends Balance.Builder<Builder> {
        private Member benefactor;

        public Builder setBenefactor(Member benefactor) {
            this.benefactor = benefactor;
            return this;
        }

        @Override
        public Transaction build() {
            return new Transaction(this);
        }
    }

    protected Transaction(Builder builder) {
        super(builder);
        benefactor = builder.benefactor;
    }
}
