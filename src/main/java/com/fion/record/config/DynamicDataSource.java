package com.fion.record.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 根据Key动态路由数据源
 *
 * @date 2020-09-03 20:32
 * @author fion yang
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getCurrentDataSource();
    }
}
