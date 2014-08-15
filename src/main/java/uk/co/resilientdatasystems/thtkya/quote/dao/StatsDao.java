package uk.co.resilientdatasystems.thtkya.quote.dao;

import java.util.List;

import uk.co.resilientdatasystems.thtkya.quote.Quote;
import uk.co.resilientdatasystems.thtkya.quote.QuoteStats;
import uk.co.resilientdatasystems.thtkya.quote.QuoteStats.Channel;

public interface StatsDao {
    QuoteStats readStatsForQuote(Quote quote, Channel channel);
    QuoteStats addQuoteViews(Quote quote, long additionalViews, Channel channel);
    QuoteStats addQuoteBought(Quote quote, long additionalViews, Channel channel);
    QuoteStats readStats(long id);
    List<QuoteStats> readStats(Quote quote);
}
