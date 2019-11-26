package com.sand.common.enums;

public enum PermissionType {
    USER_CREATE("用户创建"),
    USER_UPDATE("用户更新"),
    USER_QUERY("用户查询"),

    ;
    private final String label;

    PermissionType(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }
}
