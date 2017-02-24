package com.wingedfish.eagles.metadata.domain.constants;

/**
 * Created by wingedfish on 2017/2/23.
 */
public interface DataSourcePoolConstant {
    //     最大连接池数量
    int maxActive = 15;
    //        连接池初始化数量 add
    int initialSize = 2;
    //        获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁,需手动置为非公平锁
    long maxWait = 60000L;
    //         	最小连接池数量
    int minIdle = 2;
    //         配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 
    long timeBetweenEvictionRunsMillis = 60000L;
    //         配置一个连接在池中最小生存的时间，单位是毫秒 
    long minEvictableIdleTimeMillis = 180000L;
    //
    String validationQuery = "SELECT 'x'";
    //        建议配置为true，不影响性能，并且保证安全性。
//    申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，
//    执行validationQuery检测连接是否有效。
    boolean testWhileIdle = true;
    //        申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
    boolean testOnBorrow = false;
    //         	归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
    boolean testOnReturn = false;
    //        是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
    boolean poolPreparedStatements = false;
    //        要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true>
    int maxPoolPreparedStatementPerConnectionSize = -1;
    //         配置监控统计拦截的filters
    int slowSqlMillis = 500;
    boolean logSlowSql = true;
    //        配置了maxWait druid默认使用公平锁,手动设置使用非公平锁
    boolean useUnfairLock = true;
    //        可以看到未关闭连接的具体堆栈信息,方便查看链接泄露
    boolean removeAbandoned = true;
}
