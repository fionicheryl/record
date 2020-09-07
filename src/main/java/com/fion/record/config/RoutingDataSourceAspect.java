package com.fion.record.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 路由数据源切面
 *
 * @date 2020-09-02 18:17
 * @author fion yang
 */
@Aspect
@Component
@Slf4j
@Order(-1)
public class RoutingDataSourceAspect {

    /**
     * 路由数据源切点
     */
    @Pointcut(value = "@annotation(dataSource)", argNames = "dataSource")
    private void routingDataSourcePointCut(RoutingDataSource dataSource) {}

    /**
     * 前置通知，切换数据源
     *
     * @param dataSource
     */
    @Before(value = "routingDataSourcePointCut(dataSource)")
    public void before(RoutingDataSource dataSource) {
        log.info("============================== {}",dataSource.value());
        DataSourceContextHolder.setCurrentDataSource(dataSource.value());
    }

    /**
     * 后置通知，切换数据源
     *
     * @param dataSource
     */
    @After(value = "routingDataSourcePointCut(dataSource)")
    public void after(RoutingDataSource dataSource) {
        DataSourceContextHolder.removeDataSource();
    }

    /**
     * 通知
     * 主要是执行方法前设置数据源标识，方法执行完成删除数据源标识
     *
     * @param joinPoint 连接点
     * @return
     * @throws Throwable
     */
    /*@Around("routingDataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 获取方法
        Method method = methodSignature.getMethod();
        // 获取路由数据源注解
        RoutingDataSource routingDataSource = method.getAnnotation(RoutingDataSource.class);
        // 获取路由数据源标识
        DataSourceEnum dataSourceEnum = routingDataSource.value();
        // 向ThreadLocal设置对应的数据源标识
        DataSourceContextHolder.setCurrentDataSource(dataSourceEnum);
        try {
            return joinPoint.proceed();
        } finally {
            // 删除ThreadLocal中设置的数据源标识
            DataSourceContextHolder.removeDataSource();
        }
    }*/
}
