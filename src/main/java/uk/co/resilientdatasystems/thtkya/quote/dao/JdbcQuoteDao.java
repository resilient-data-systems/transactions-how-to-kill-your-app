package uk.co.resilientdatasystems.thtkya.quote.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import uk.co.resilientdatasystems.thtkya.quote.Quote;

@Repository
public class JdbcQuoteDao implements QuoteDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcQuoteDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Quote readQuote(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        return jdbcTemplate.queryForObject("SELECT id, quote FROM quote WHERE id=:id", params, new QuoteMapper());
    }

    public Long countAll() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM quote", Collections.EMPTY_MAP, Long.class);
    }

    private static final class QuoteMapper implements RowMapper<Quote> {

        public Quote mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            Double quote = rs.getDouble("quote");
            return new Quote(id, quote);
        }
    }
}

