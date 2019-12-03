package com.sand.common.collection;

import java.util.Date;

public class PageCollection {
    private Long id;
    private String remoteAddr;
    private String referer;
    private String url;
    private String methodName;
    private Date startTime;
    private Date endTime;
    private Long spendTime;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public PageCollection setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public PageCollection setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
        return this;
    }

    public String getReferer() {
        return referer;
    }

    public PageCollection setReferer(String referer) {
        this.referer = referer;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PageCollection setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public PageCollection setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public PageCollection setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public PageCollection setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public Long getSpendTime() {
        return spendTime;
    }

    public PageCollection setSpendTime(Long spendTime) {
        this.spendTime = spendTime;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public PageCollection setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public PageCollection setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
