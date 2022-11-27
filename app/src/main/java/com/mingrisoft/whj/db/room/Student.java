package com.mingrisoft.whj.db.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "student")            //通过该标签将该类与Room中表关联起来。tableName属性可以为该表设置名字，如果不设置，则表名与类名相同。
public class Student {

    @PrimaryKey(autoGenerate = true)     //用于指定该字段作为表的主键。
    @ColumnInfo(name = "id",typeAffinity = ColumnInfo.INTEGER)     //用于设置该字段存储在数据库表中的名字并指定字段的类型。
    private int id;

    @ColumnInfo(name = "name",typeAffinity = ColumnInfo.TEXT)
    private String name;

    @ColumnInfo(name = "age",typeAffinity = ColumnInfo.TEXT)
    private String age;

    @ColumnInfo(name = "sex",typeAffinity = ColumnInfo.TEXT)
    private String sex;

    public Student(int id, String name, String age,String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Ignore
    public Student(String name,String age,String sex){
        this.sex = sex;
        this.name = name;
        this.age = age;
    }

    @Ignore                //用来告诉系统忽略该字段或者方法。
    public Student(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Ignore
    public Student(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

}
