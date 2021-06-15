package cn.beecp.starter.demo;


import cn.beecp.boot.datasource.sqltrace.SqlTraceAlert;
import cn.beecp.boot.datasource.sqltrace.SqlTraceEntry;

import java.util.List;

public class TestAlert extends SqlTraceAlert {
    public void alert(List<SqlTraceEntry> alertList) {
        System.out.println("....test alert siz:" + alertList.size());
    }
}
