package com.mingrisoft.whj.db.dbflow;


import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = MyDBFlowDataBase.class)
public class Apple extends BaseModel {

    @Column
    @PrimaryKey
    int id;

    @Column
    private String price;

    @Column
    private String color;

    @Column
    private String model;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "id=" + id +
                ", price='" + price + '\'' +
                ", color='" + color + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
