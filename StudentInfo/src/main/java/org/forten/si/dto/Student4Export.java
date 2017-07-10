package org.forten.si.dto;

import org.forten.utils.common.DateUtil;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/10.
 */
public class Student4Export {
    private String name;
    private String tel;
    private String email;
    private String address;
    private String idCardNum;
    private Date birthday;

    public Student4Export() {
    }

    public Student4Export(String name, String tel, String email, String address, String idCardNum, Date birthday) {
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.address = address;
        this.idCardNum = idCardNum;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBirthdayStr(){
        return birthday==null?"": DateUtil.convertDateToString(birthday,"yyyy年MM月dd日");
    }

    @Override
    public String toString() {
        return "Student4Export{" +
                "name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", idCardNum='" + idCardNum + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
