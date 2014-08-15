package uk.co.resilientdatasystems.thtkya.quote.services;

import java.util.List;

import uk.co.resilientdatasystems.thtkya.quote.Quote;
import uk.co.resilientdatasystems.thtkya.quote.QuoteStats;

public interface StatsService {

    void incrementQuoteViews(Quote quote);

    List<QuoteStats> getStats(Quote quote);

    void incrementQuoteBuys(Quote quote);

}