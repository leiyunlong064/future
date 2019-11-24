package com.sand.common.enums;

public enum UserStatus {
    ACTIVE("激活"),
    INACTIVE("未激活"),
    ;
    private final String label;

    UserStatus(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }
}
