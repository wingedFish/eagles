# eagles
雄鹰系统：所有系统的基类
系统框架：
    SpringMvc+MyBatis+Disruptor(RingBuffer)

相关技术点：
    1、利用Spring的AbstractRoutingDataSource动态路由数据源
    2、通过实现FactoryBean动态创建数据源链接池，保存在JVM内存中
    3、创建多个MyBatis的SqlSessionFactoryBean区分不同的链接会话
    4、环境问题配置化：development.properties,production.properties
