package com.fion.record.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * tk mybatis 要配置单独的 {@link MapperScan}
 * basePackage不能包含通用mapper（我的是BaseDao）的路径，只包含其他的mapper的路径，不然会报错
 * sun.reflect.generics.reflectiveObjects.TypeVariableImpl cannot be cast to java.lang.Class
 *
 * @author yanqi69
 * @date 2020/3/25
 */
@Configuration
@MapperScan(basePackages = "com.fion.record.dao.*")
public class RepositoryConfig {

    /**
     * Primary 表示这个数据源是默认数据源, 这个注解必须要加，因为不加的话spring将分不清楚那个为主数据源（默认数据源）
     *
     * @return
     */

    @Bean("db1Datasource")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource db1Datasource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("db2Datasource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource db2Datasource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("db1Datasource") DataSource db1Datasource,
                                 @Qualifier("db2Datasource") DataSource db2Datasource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(db1Datasource);
        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put(DataSourceEnum.DB1, db1Datasource);
        dataSources.put(DataSourceEnum.DB2, db2Datasource);
        dynamicDataSource.setTargetDataSources(dataSources);
        return dynamicDataSource;
    }

}
