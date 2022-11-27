package com.mingrisoft.whj.db.dbflow;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = MyDBFlowDataBase.NAME, version = MyDBFlowDataBase.VERSION)
public class MyDBFlowDataBase {
    //数据库名称
    public static final String NAME = "MyDatabase";
    //数据库版本号
    public static final int VERSION = 1;
}
