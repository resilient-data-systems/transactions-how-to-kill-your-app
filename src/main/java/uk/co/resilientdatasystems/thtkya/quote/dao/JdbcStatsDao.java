package uk.co.resilientdatasystems.thtkya.quote.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import uk.co.resilientdatasystems.thtkya.quote.Quote;
import uk.co.resilientdatasystems.thtkya.quote.QuoteStats;
import uk.co.resilientdatasystems.thtkya.quote.QuoteStats.Channel;

@Repository
public class JdbcStatsDao implements StatsDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcStatsDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public QuoteStats addQuoteBought(Quote quote, long additionalPurchases, Channel channel) {
        SqlParameterSource paramSource =
                new MapSqlParameterSource()
        .addValue("quoteId", quote.getId())
        .addValue("additionalPurchases", additionalPurchases)
        .addValue("channel", channel.toString());

        jdbcTemplate.update(
                "UPDATE quote_stats SET bought = bought+ :additionalPurchases WHERE quote_id=:quoteId AND channel=:channel",
                paramSource);

        return readStatsForQuote(quote, channel);
    }

    @Override
    public QuoteStats addQuoteViews(Quote quote, long additionalViews, Channel channel) {
        SqlParameterSource paramSource =
                new MapSqlParameterSource()
        .addValue("quoteId", quote.getId())
        .addValue("additionalViews", additionalViews)
        .addValue("channel", channel.toString());

        jdbcTemplate.update(
                "UPDATE quote_stats SET viewed = viewed+ :additionalViews WHERE quote_id=:quoteId AND channel=:channel",
                paramSource);

        return readStatsForQuote(quote, channel);
    }

    @Override
    public QuoteStats readStats(long id) {
        SqlParameterSource paramMap = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(
                "SELECT id, quote_id, viewed, bought, channel FROM quote_stats where id = :id",
                paramMap,
                new QuoteStatsMapper());
    }

    @Override
    public List<QuoteStats> readStats(Quote quote) {
        SqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("quoteId", quote.getId());

        return jdbcTemplate.query(
                "SELECT id, quote_id, viewed, bought, channel FROM quote_stats where quote_id = :quoteId",
                paramMap,
                new QuoteStatsMapper());
    }

    @Override
    public QuoteStats readStatsForQuote(Quote quote, Channel channel) {
        SqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("quoteId", quote.getId())
                .addValue("channel", channel.toString());

        return jdbcTemplate.queryForObject(
                "SELECT id, quote_id, viewed, bought, channel FROM quote_stats where quote_id = :quoteId AND channel=:channel",
                paramMap,
                new QuoteStatsMapper());
    }

    private static final class QuoteStatsMapper implements RowMapper<QuoteStats> {

        @Override
        public QuoteStats mapRow(ResultSet rs, int rowNum) throws SQLException {
            QuoteStats quoteStats = new QuoteStats();
            quoteStats.setId(rs.getLong("id"));
            quoteStats.setQuoteId(rs.getLong("quote_id"));
            quoteStats.setViewed(rs.getLong("viewed"));
            quoteStats.setBought(rs.getLong("bought"));
            quoteStats.setChannel(Channel.valueOf(rs.getString("channel")));
            return quoteStats;
        }

    }
}
