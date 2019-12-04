package com.sand.common.collection;

import com.sand.common.enums.DurationUnit;

import java.util.Date;

public class GeoViewCollection {
    private Long id;
    private String province;
    private String city;
    private Long viewCount;
    private Date collectDate;
    private DurationUnit durationUnit;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public GeoViewCollection setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public GeoViewCollection setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public GeoViewCollection setCity(String city) {
        this.city = city;
        return this;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public GeoViewCollection setViewCount(Long viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public Date getCollectDate() {
        return collectDate;
    }

    public GeoViewCollection setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
        return this;
    }

    public DurationUnit getDurationUnit() {
        return durationUnit;
    }

    public GeoViewCollection setDurationUnit(DurationUnit durationUnit) {
        this.durationUnit = durationUnit;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public GeoViewCollection setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public GeoViewCollection setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
