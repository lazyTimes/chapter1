package com.smart4j.chapter.model;

/**
 * 自定义客户表
 */

public class Customer {

    /**
     * 主键
     */
    private int id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 联系人
     */
    private String contack;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String remark;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContack() {
        return contack;
    }

    public void setContack(String contack) {
        this.contack = contack;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
