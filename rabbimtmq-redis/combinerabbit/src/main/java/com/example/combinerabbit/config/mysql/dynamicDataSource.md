#多数据源配置总结：
    1.配置数据源需要分开注入sqlsessionTemplate等
    2.开始事务需要配置PlatformTransactionManager事务管理
    3.数据源的扫描最好分开  
        最好不要重复扫描mapper；
        要么只扫描一次
       
---------------------------


#一定要有日志记录功能！！！

