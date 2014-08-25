package com.spotify.heroic.http.model.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.Data;

import com.spotify.heroic.aggregation.Aggregation;
import com.spotify.heroic.model.filter.Filter;

@Data
public class MetricsQuery {
    private static final DateRangeQuery DEFAULT_DATE_RANGE = new DateRangeQuery.Relative(
            TimeUnit.DAYS, 7);
    private static final List<AggregationQuery> EMPTY_AGGREGATIONS = new ArrayList<>();

    private final String key = null;
    private final Map<String, String> tags = new HashMap<String, String>();
    private final Filter filter = null;
    private final List<String> groupBy = new ArrayList<String>();
    private final DateRangeQuery range = DEFAULT_DATE_RANGE;
    private final boolean noCache = false;
    private final List<AggregationQuery> aggregators = EMPTY_AGGREGATIONS;

    public List<Aggregation> makeAggregators() {
        if (this.aggregators == null)
            return null;

        final List<Aggregation> aggregators = new ArrayList<>(
                this.aggregators.size());

        for (final AggregationQuery aggregation : this.aggregators) {
            aggregators.add(aggregation.makeAggregation());
        }

        return aggregators;
    }
}