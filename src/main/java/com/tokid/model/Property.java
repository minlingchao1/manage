package com.tokid.model;
/*
* @Description:
* @author king
* @date 2017/11/15 15:11
*/

import com.tokid.base.model.BaseModel;

import javax.persistence.Table;

@Table(name="property")
public class Property extends BaseModel{

    private static final long serialVersionUID = 1L;

    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
