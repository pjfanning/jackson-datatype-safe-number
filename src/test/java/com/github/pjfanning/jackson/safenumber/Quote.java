package com.github.pjfanning.jackson.safenumber;

import com.github.pjfanning.safenumberparser.SafeBigDecimal;

import java.util.Objects;

final class Quote {
    private String description ;
    private SafeBigDecimal value;

    Quote() {}

    Quote(String description, SafeBigDecimal value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SafeBigDecimal getValue() {
        return value;
    }

    public void setValue(SafeBigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return Objects.equals(description, quote.description) && Objects.equals(value, quote.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, value);
    }
}
