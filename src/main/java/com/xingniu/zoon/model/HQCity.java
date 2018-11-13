package com.xingniu.zoon.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 城市
 */
@Table(name = "hq_city")
public class HQCity implements Serializable {
    private static final long serialVersionUID = -6906974619024397089L;
    /***国家标识ID***/
    @Column(name = "countryID")
    private String countryID;
    /***州、省份ID***/
    @Column(name = "stateID")
    private String stateID;
    /***城市标识***/
    @Column(name = "cityID")
    private String cityID;
    /***城市名***/
    @Column(name = "nameChn")
    private String nameChn;
    /***城市名英文***/
    @Column(name = "nameEng")
    private String nameEng;
    /***城市名拼音***/
    @Column(name = "namePinyin")
    private String namePinyin;
    /***州、省份中文名***/
    @Column(name = "stateNameChn")
    private String stateNameChn;
    /***州、省份英文名***/
    @Column(name = "stateNameEng")
    private String stateNameEng;
    /***州、省份英文名 86中国，99国外***/
    @Column(name = "national")
    private String national;

    private Date createTime;

    private Date updateTime;

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getNameChn() {
        return nameChn;
    }

    public void setNameChn(String nameChn) {
        this.nameChn = nameChn;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getStateNameChn() {
        return stateNameChn;
    }

    public void setStateNameChn(String stateNameChn) {
        this.stateNameChn = stateNameChn;
    }

    public String getStateNameEng() {
        return stateNameEng;
    }

    public void setStateNameEng(String stateNameEng) {
        this.stateNameEng = stateNameEng;
    }

    public String getNamePinyin() {
        return namePinyin;
    }

    public void setNamePinyin(String namePinyin) {
        this.namePinyin = namePinyin;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
