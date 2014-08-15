package uk.co.resilientdatasystems.thtkya.quote.dao;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import uk.co.resilientdatasystems.thtkya.quote.Quote;
import uk.co.resilientdatasystems.thtkya.quote.dao.JdbcQuoteDao;
import uk.co.resilientdatasystems.thtkya.quote.dao.QuoteDao;
import uk.co.resilientdatasystems.thtkya.testutils.PersistanceTestBase;

@ContextConfiguration(classes = {JdbcQuoteDao.class})
public class JdbcQuoteDaoTest extends PersistanceTestBase {

    @Autowired
    private QuoteDao quoteDao;

    @Test
    public void countAll() {
        assertThat(quoteDao.countAll(), is(5L));
    }

    @Test
    public void readQuote() {
        Quote quote = quoteDao.readQuote(1L);
        assertThat(quote, is(new Quote(1L, 100.0)));
    }
}
