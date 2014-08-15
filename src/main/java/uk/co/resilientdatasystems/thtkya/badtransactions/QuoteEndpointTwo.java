package uk.co.resilientdatasystems.thtkya.badtransactions;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.co.resilientdatasystems.thtkya.quote.Quote;
import uk.co.resilientdatasystems.thtkya.quote.QuoteStats;
import uk.co.resilientdatasystems.thtkya.web.QuoteFacade;

@Component
@RequestMapping(value = "/bad/quote")
public class QuoteEndpointTwo {
    private static final Logger log = LoggerFactory.getLogger(QuoteEndpointTwo.class);
    private final QuoteFacade facade;

    @Autowired
    public QuoteEndpointTwo(@Qualifier("BadTransactions") QuoteFacade facade) {
        this.facade = facade;
        log.debug("Init QuoteEndpointTwo");
    }

    @RequestMapping("{id}")
    @ResponseBody
    public Quote get(@PathVariable Long id) {
        return facade.getQuote(id);
    }

    @RequestMapping("buy/{id}")
    @ResponseBody
    public Quote buy(@PathVariable Long id) {
        return facade.buy(id);
    }

    @RequestMapping("stats/{id}")
    @ResponseBody
    public List<QuoteStats> stats(@PathVariable Long id) {
        return facade.getQuoteStats(id);
    }
}
