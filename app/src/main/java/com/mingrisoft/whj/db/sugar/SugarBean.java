package com.mingrisoft.whj.db.sugar;


import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

/**Sugar数据存储的实体类*/
@Table(name = "sugarBean")
public class SugarBean extends SugarRecord{

    @Unique
    @Column(name = "name")
    private String name;

    @Column(name = "age")
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
