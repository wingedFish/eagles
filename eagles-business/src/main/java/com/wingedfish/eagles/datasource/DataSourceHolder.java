package com.wingedfish.eagles.datasource;

import org.springframework.core.NamedThreadLocal;

/**
 * Created by lixiuhai on 2017/5/5.
 */
public class DataSourceHolder {
    private static ThreadLocal<String> dsThreadLocal = new NamedThreadLocal<>("dataSource");

    public static void setDataSource(String dataSourceKey) {
        dsThreadLocal.set(dataSourceKey);
    }

    public static String getDataSource() {
        return dsThreadLocal.get();
    }

    public static void removeDataSource() {
        dsThreadLocal.remove();
    }
}
