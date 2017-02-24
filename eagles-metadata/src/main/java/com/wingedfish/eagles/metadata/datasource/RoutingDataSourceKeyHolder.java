package com.wingedfish.eagles.metadata.datasource;

import org.springframework.core.NamedThreadLocal;

/**
 * Created by wingedfish on 2017/2/23.
 */
public class RoutingDataSourceKeyHolder {
    private static ThreadLocal<Long> dataSourceKeyHolder = new NamedThreadLocal<Long>("RoutingDataSourceKey");


    public static Long getKey() {
        return dataSourceKeyHolder.get();
    }

    public static void setKey(Long key) {
        dataSourceKeyHolder.set(key);
    }

    public static void remove() {
        dataSourceKeyHolder.remove();
    }


}
