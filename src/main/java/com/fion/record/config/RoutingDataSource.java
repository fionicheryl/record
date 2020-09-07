package com.fion.record.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 路由数据源自定义注解
 *
 * @date 2020-09-02 14:17
 * @author fion yang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RoutingDataSource {

    /**
     * 路由的DataSource，默认为LEGEND
     */
    DataSourceEnum value() default DataSourceEnum.LEGEND;

}
