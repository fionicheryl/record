package com.fion.record.config;

/**
 * 数据源上下文助手
 *
 * @date 2020-09-02 18:19
 * @author fion yang
 */
public class DataSourceContextHolder {

    /**
     * ThreadLocal 用于记录当前数据源
     */
    private static final ThreadLocal<DataSourceEnum> CONTEXT_HOLDER = ThreadLocal.withInitial(() -> DataSourceEnum.DB1);

    /**
     * 设置当前数据源
     *
     * @param dataSourceEnum 数据源枚举
     */
    public static void setCurrentDataSource(DataSourceEnum dataSourceEnum) {
        CONTEXT_HOLDER.set(dataSourceEnum);
    }

    /**
     * 获取当前数据源
     * @return
     */
    public static DataSourceEnum getCurrentDataSource() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 删除当前数据源记录
     */
    public static void removeDataSource() {
        CONTEXT_HOLDER.remove();
    }
}
