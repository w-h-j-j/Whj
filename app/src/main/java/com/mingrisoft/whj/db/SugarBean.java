package com.mingrisoft.whj.db;


import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**Sugar数据存储的实体类*/
public class SugarBean extends SugarRecord{
    @Unique
    private String name;
    private String age;

    public SugarBean(){}

    public SugarBean(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(String age) {
        this.age = age;
    }
}
