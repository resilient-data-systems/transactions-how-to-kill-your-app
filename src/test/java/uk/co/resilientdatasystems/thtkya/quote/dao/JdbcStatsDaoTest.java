package uk.co.resilientdatasystems.thtkya.quote.dao;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import uk.co.resilientdatasystems.thtkya.quote.Quote;
import uk.co.resilientdatasystems.thtkya.quote.QuoteStats;
import uk.co.resilientdatasystems.thtkya.quote.QuoteStats.Channel;
import uk.co.resilientdatasystems.thtkya.quote.dao.JdbcStatsDao;
import uk.co.resilientdatasystems.thtkya.quote.dao.StatsDao;
import uk.co.resilientdatasystems.thtkya.testutils.PersistanceTestBase;

@ContextConfiguration(classes = { JdbcStatsDao.class })
@Transactional
public class JdbcStatsDaoTest extends PersistanceTestBase {

    @Autowired
    private StatsDao statsDao;

    @Test
    public void readQuoteStatsForQuote() {
        Quote quote = new Quote(1L, null);
        QuoteStats stats = statsDao.readStatsForQuote(quote, Channel.MOBILE);

        QuoteStats expectedStats = createExpectedForFirstQuote();

        assertThat(stats, is(expectedStats));
    }

    @Test
    public void readQuoteStats() {
        QuoteStats stats = statsDao.readStats(1L);

        QuoteStats expectedStats = createExpectedForFirstQuote();

        assertThat(stats, is(expectedStats));
    }

    @Test
    public void addQuoteViews() {
        Quote quote = new Quote(1L, null);

        long additionalViews = 7L;
        Channel channel = Channel.MOBILE;

        QuoteStats beforeUpdate = statsDao.readStatsForQuote(quote, channel);
        QuoteStats updatedQuoteStats = statsDao.addQuoteViews(quote, additionalViews, channel);

        assertThat(beforeUpdate.getViewed()+additionalViews, is(updatedQuoteStats.getViewed()));
    }

    @Test
    public void addQuoteBought() {
        Quote quote = new Quote(1L, null);

        long additionalPurchases = 5L;
        Channel channel = Channel.WEB;

        QuoteStats beforeUpdate = statsDao.readStatsForQuote(quote, channel);
        QuoteStats updatedQuoteStats = statsDao.addQuoteBought(quote, additionalPurchases, channel);

        assertThat(beforeUpdate.getBought()+additionalPurchases, is(updatedQuoteStats.getBought()));
    }

    @Test
    public void readStats() {
        Quote quote = new Quote(1L, null);

        List<QuoteStats> stats = statsDao.readStats(quote);

        assertThat(stats.size(), is(2));
    }


    private QuoteStats createExpectedForFirstQuote() {
        QuoteStats quoteStats = new QuoteStats();

        quoteStats.setId(1L);
        quoteStats.setQuoteId(1L);
        quoteStats.setBought(0);
        quoteStats.setViewed(2);
        quoteStats.setChannel(Channel.MOBILE);
        return quoteStats;
    }

}
