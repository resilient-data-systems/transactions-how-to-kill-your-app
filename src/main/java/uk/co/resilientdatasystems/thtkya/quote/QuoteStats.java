package uk.co.resilientdatasystems.thtkya.quote;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QuoteStats {

    public enum Channel {WEB, MOBILE, AFFILIATE}

    private long id;

    private long quoteId;
    private long viewed;
    private long bought;

    private Channel channel;

    public long getId() {
        return id;
    }

    public long getQuoteId() {
        return quoteId;
    }

    public long getViewed() {
        return viewed;
    }

    public long getBought() {
        return bought;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuoteId(long quoteId) {
        this.quoteId = quoteId;
    }

    public void setViewed(long viewed) {
        this.viewed = viewed;
    }

    public void setBought(long bought) {
        this.bought = bought;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
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
