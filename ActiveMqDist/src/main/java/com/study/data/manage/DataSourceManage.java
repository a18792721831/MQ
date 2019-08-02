package com.study.data.manage;

import com.study.nems.DataSourceType;

/**
 * @author jiayq
 */
public class DataSourceManage {

    private static final ThreadLocal<DataSourceType> dataSourceType =
            ThreadLocal.withInitial(() -> DataSourceType.DATA_SOURCE_SUB);

    public static DataSourceType get() {
        return dataSourceType.get();
    }

    public static void set(DataSourceType dataSourceType) {
        DataSourceManage.dataSourceType.set(dataSourceType);
    }

}