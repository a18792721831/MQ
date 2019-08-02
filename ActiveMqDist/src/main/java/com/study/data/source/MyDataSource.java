package com.study.data.source;

import com.study.data.manage.DataSourceManage;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author jiayq
 */
public class MyDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceManage.get();
    }

}
