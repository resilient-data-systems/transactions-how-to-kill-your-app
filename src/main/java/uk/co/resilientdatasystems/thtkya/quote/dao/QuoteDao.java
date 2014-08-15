package uk.co.resilientdatasystems.thtkya.quote.dao;

import uk.co.resilientdatasystems.thtkya.quote.Quote;

public interface QuoteDao {
    Quote readQuote(long id);

    Long countAll();
}
