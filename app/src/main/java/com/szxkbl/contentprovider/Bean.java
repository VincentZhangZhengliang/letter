package com.szxkbl.contentprovider;

/**
 * @author : Vincent
 * @time : 2017/2/13 10:19.
 * @Discription :
 */

public class Bean {
    private String name;
    private String phone;

    public Bean(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
