package com.spotify.heroic.aggregation.simple;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotify.heroic.aggregation.Aggregation;
import com.spotify.heroic.aggregation.BucketAggregation;
import com.spotify.heroic.aggregation.model.QueryAggregation;
import com.spotify.heroic.aggregation.model.QuerySampling;
import com.spotify.heroic.model.DataPoint;
import com.spotify.heroic.model.Sampling;

public class AverageAggregation extends BucketAggregation<SumBucket> {
    @Data
    public static class Query implements QueryAggregation {
        private final QuerySampling sampling;

        @Override
        public Aggregation build() {
            return new AverageAggregation(sampling.build());
        }

        @JsonCreator
        public static Query create(@JsonProperty(value = "sampling", required = true) QuerySampling sampling) {
            return new Query(sampling);
        }
    }

    public AverageAggregation(Sampling sampling) {
        super(sampling);
    }

    @JsonCreator
    public static AverageAggregation create(@JsonProperty("sampling") Sampling sampling) {
        return new AverageAggregation(sampling);
    }

    @Override
    protected SumBucket buildBucket(long timestamp) {
        return new SumBucket(timestamp);
    }

    @Override
    protected DataPoint build(SumBucket bucket) {
        final long count = bucket.count();

        if (count == 0) {
            return new DataPoint(bucket.timestamp(), Double.NaN);
        }

        return new DataPoint(bucket.timestamp(), bucket.value() / count);
    }
}