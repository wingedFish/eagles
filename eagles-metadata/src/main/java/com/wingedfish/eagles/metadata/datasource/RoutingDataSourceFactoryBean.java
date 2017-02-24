package com.wingedfish.eagles.metadata.datasource;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.wingedfish.eagles.metadata.domain.constants.DataSourcePoolConstant;
import com.wingedfish.eagles.metadata.domain.enumtype.DatabaseTypeEnum;
import com.wingedfish.eagles.metadata.domain.monitor.DatabaseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wingedfish on 2017/2/22.
 */
public class RoutingDataSourceFactoryBean implements FactoryBean<Map>, InitializingBean {
    protected final Logger logger = LoggerFactory.getLogger(getClass().getName());

    private Map<Long, DataSource> routingDataSourceMap = new HashMap();
    private boolean isInitialized = false;

    public Map getObject() throws Exception {
        if (isInitialized) {
            return routingDataSourceMap;
        }
        afterPropertiesSet();

        return routingDataSourceMap;
    }

    public Class<?> getObjectType() {
        return Map.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {
        isInitialized = true;
        List<DatabaseInfo> databaseInfos = getValidDatabaseList();
        if (CollectionUtils.isEmpty(databaseInfos)) {
            logger.warn("could not found valid database info !");
            return;
        }
        databaseInfos.forEach((databaseInfo) -> {
            routingDataSourceMap.put(databaseInfo.getMonitorId(), createDruidDataSource(databaseInfo));
        });
    }

    /**
     * 构建数据源
     * @param databaseInfo
     * @return
     */
    private DruidDataSource createDruidDataSource(DatabaseInfo databaseInfo) {
        DruidDataSource druidDataSource = getAbstractDataSource();
        druidDataSource.setUrl(databaseInfo.getUrl());
        druidDataSource.setUsername(databaseInfo.getUserName());
        druidDataSource.setPassword(databaseInfo.getPasswd());
        druidDataSource.setName(databaseInfo.getMonitorId() + "");
        return druidDataSource;
    }

    /**
     * 构建公共属性的DruidDataSource
     *
     * @return
     * @see DataSourcePoolConstant
     */
    private DruidDataSource getAbstractDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setMaxActive(DataSourcePoolConstant.maxActive);
        druidDataSource.setInitialSize(DataSourcePoolConstant.initialSize);
        druidDataSource.setMaxWait(DataSourcePoolConstant.maxWait);
        druidDataSource.setMinIdle(DataSourcePoolConstant.minIdle);
        druidDataSource.setTimeBetweenEvictionRunsMillis(DataSourcePoolConstant.timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(DataSourcePoolConstant.minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(DataSourcePoolConstant.validationQuery);
        druidDataSource.setTestWhileIdle(DataSourcePoolConstant.testWhileIdle);
        druidDataSource.setTestOnBorrow(DataSourcePoolConstant.testOnBorrow);
        druidDataSource.setTestOnReturn(DataSourcePoolConstant.testOnReturn);
        druidDataSource.setPoolPreparedStatements(DataSourcePoolConstant.poolPreparedStatements);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(DataSourcePoolConstant.maxPoolPreparedStatementPerConnectionSize);
        druidDataSource.setUseUnfairLock(DataSourcePoolConstant.useUnfairLock);
        druidDataSource.setRemoveAbandoned(DataSourcePoolConstant.removeAbandoned);

        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(DataSourcePoolConstant.slowSqlMillis);
        statFilter.setLogSlowSql(DataSourcePoolConstant.logSlowSql);
        List<Filter> proxyFilters = new ArrayList<>();
        proxyFilters.add(statFilter);

        druidDataSource.setProxyFilters(proxyFilters);

        return druidDataSource;
    }


    //获取有效的数据库信息列表
    private List<DatabaseInfo> getValidDatabaseList() {
        return testValidDatabaseList();
    }

    //模拟数据
    private List<DatabaseInfo> testValidDatabaseList() {
        List<DatabaseInfo> databaseInfos = new ArrayList<DatabaseInfo>();
        //数据库B
        DatabaseInfo kmInfo = new DatabaseInfo();
        kmInfo.setMonitorId(1L);
        kmInfo.setDatabaseId(1L);
        kmInfo.setUrl("jdbc:mysql://127.0.0.1:3306/eagles?characterEncoding=UTF-8");
        kmInfo.setPasswd("eagles");
        kmInfo.setUserName("eagles");
        kmInfo.setType(DatabaseTypeEnum.MYSQL.getType());

        //数据库A
        DatabaseInfo czInfo = new DatabaseInfo();
        czInfo.setMonitorId(1L);
        czInfo.setDatabaseId(1L);
        czInfo.setUrl("jdbc:mysql://127.0.0.1:3306/eagles?characterEncoding=UTF-8");
        czInfo.setPasswd("eagles");
        czInfo.setUserName("eagles");
        czInfo.setType(DatabaseTypeEnum.MYSQL.getType());

        databaseInfos.add(kmInfo);
        databaseInfos.add(czInfo);

        return databaseInfos;

    }
}
