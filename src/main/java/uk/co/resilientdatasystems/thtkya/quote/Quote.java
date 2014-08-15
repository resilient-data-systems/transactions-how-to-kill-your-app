package uk.co.resilientdatasystems.thtkya.quote;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Quote {
    private Long id;
    private Double quote;

    public Quote() {
    }

    public Quote(Long id, Double quote) {
        this.id = id;
        this.quote = quote;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuote() {
        return quote;
    }

    public void setQuote(Double quote) {
        this.quote = quote;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, true);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
