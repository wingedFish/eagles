package com.wingedfish.eagles.metadata.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by wingedfish on 2017/2/23.
 */
public class RoutingMultiDataSource extends AbstractRoutingDataSource {
    protected Object determineCurrentLookupKey() {
        return RoutingDataSourceKeyHolder.getKey();
    }
}
