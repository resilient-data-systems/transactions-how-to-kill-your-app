package uk.co.resilientdatasystems.thtkya.badtransactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.co.resilientdatasystems.thtkya.quote.Quote;
import uk.co.resilientdatasystems.thtkya.quote.QuoteStats;
import uk.co.resilientdatasystems.thtkya.quote.QuoteStats.Channel;
import uk.co.resilientdatasystems.thtkya.quote.dao.StatsDao;
import uk.co.resilientdatasystems.thtkya.quote.services.StatsService;

@Service
@Qualifier("BadTransactions")
/**
 * REQUIRES_NEW: DON'T DO THAT, this is for educational purpose, right?
 */
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class BadTransactionsStatsService  implements StatsService{

    private final StatsDao statsDao;

    @Autowired
    public BadTransactionsStatsService(StatsDao statsDao) {
        this.statsDao = statsDao;
    }

    @Override
    public void incrementQuoteViews(Quote quote) {
        statsDao.addQuoteViews(quote, 1, Channel.WEB);
    }

    @Override
    public List<QuoteStats> getStats(Quote quote) {
        return statsDao.readStats(quote);
    }

    @Override
    public void incrementQuoteBuys(Quote quote) {
        statsDao.addQuoteBought(quote, 1, Channel.WEB);
    }

}
