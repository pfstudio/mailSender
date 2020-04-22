package com.pfstudio.mailsender.pojo;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author 杨丰畅
 * @description TODO
 * @date 2020/4/22 15:59
 **/
public class UserInfo {
    @ExcelProperty("账号")
    String account;

    @ExcelProperty("密码")
    String password;

    @ExcelProperty("学号")
    String stuNumber;

    @ExcelProperty("姓名")
    String name;

    @ExcelProperty("性别")
    String sex;

    @ExcelProperty("学校")
    String school;

    @ExcelProperty("学院")
    String subSchool;

    @ExcelProperty("专业")
    String major;

    @ExcelProperty("邮箱")
    String email;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSubSchool() {
        return subSchool;
    }

    public void setSubSchool(String subSchool) {
        this.subSchool = subSchool;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", stuNumber='" + stuNumber + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", school='" + school + '\'' +
                ", subSchool='" + subSchool + '\'' +
                ", major='" + major + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
