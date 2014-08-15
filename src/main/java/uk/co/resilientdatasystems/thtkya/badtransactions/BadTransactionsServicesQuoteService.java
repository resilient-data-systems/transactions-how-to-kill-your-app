package uk.co.resilientdatasystems.thtkya.badtransactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import uk.co.resilientdatasystems.thtkya.quote.Quote;
import uk.co.resilientdatasystems.thtkya.quote.dao.QuoteDao;
import uk.co.resilientdatasystems.thtkya.quote.services.QuoteService;

@Service
@Qualifier("BadTransactions")
public class BadTransactionsServicesQuoteService implements QuoteService {
    private final QuoteDao quoteDao;

    @Autowired
    public BadTransactionsServicesQuoteService(QuoteDao quoteDao) {
        this.quoteDao = quoteDao;
    }

    @Override
    public Quote getQuote(Long id) {
        return quoteDao.readQuote(id);
    }

}
