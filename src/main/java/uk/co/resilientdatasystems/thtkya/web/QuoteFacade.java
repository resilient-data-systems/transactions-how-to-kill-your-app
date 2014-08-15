package uk.co.resilientdatasystems.thtkya.web;

import java.util.List;

import uk.co.resilientdatasystems.thtkya.quote.Quote;
import uk.co.resilientdatasystems.thtkya.quote.QuoteStats;

public interface QuoteFacade {

    Quote getQuote(Long id);

    Quote buy(Long id);

    List<QuoteStats> getQuoteStats(Long id);

}