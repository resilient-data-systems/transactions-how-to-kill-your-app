package uk.co.resilientdatasystems.thtkya.badtransactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import uk.co.resilientdatasystems.thtkya.quote.Quote;
import uk.co.resilientdatasystems.thtkya.quote.QuoteStats;
import uk.co.resilientdatasystems.thtkya.quote.services.QuoteService;
import uk.co.resilientdatasystems.thtkya.quote.services.StatsService;
import uk.co.resilientdatasystems.thtkya.web.QuoteFacade;

@Component
@Qualifier("BadTransactions")
@Transactional
public class BadTransactionsServicesQuoteFacade implements QuoteFacade {

    private final QuoteService quoteService;
    private final StatsService statsService;

    @Autowired
    public BadTransactionsServicesQuoteFacade(@Qualifier("BadTransactions") QuoteService quoteService,
            @Qualifier("BadTransactions") StatsService statsService) {
        this.quoteService = quoteService;
        this.statsService = statsService;

    }

    @Override
    public Quote getQuote(Long quoteId) {
        Quote quote = quoteService.getQuote(quoteId);
        statsService.incrementQuoteViews(quote);
        return quote;
    }

    @Override
    public Quote buy(Long quoteId) {
        Quote quote = quoteService.getQuote(quoteId);
        statsService.incrementQuoteBuys(quote);
        return quote;
    }

    @Override
    public List<QuoteStats> getQuoteStats(Long quoteId) {
        Quote quote = quoteService.getQuote(quoteId);
        return statsService.getStats(quote);
    }

}
