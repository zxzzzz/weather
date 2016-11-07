package com.example.weather;

/**
 * Created by zx on 16-10-16.
 */
//存放省市的信息 :id,城市名称，省份
public class CityInfo {
    String prov;
    String city;
    String id;

    public CityInfo(String id,String city, String prov) {
        this.id=id;
        this.city = city;
        this.prov = prov;
    }

    public String getProv() {
        return prov;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getCity() {
        return city;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return city+"-"+prov;
    }
}
