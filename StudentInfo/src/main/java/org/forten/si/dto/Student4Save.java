package org.forten.si.dto;

import org.forten.utils.system.Assert;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/3.
 */
public class Student4Save {
    private String name;
    private String gender;
    private String idCardNum;
    private String email;
    private String tel;
    private String address;
    private Date birthday;
    private String eduBg;

    public Student4Save() {

    }

    public Student4Save(String name, String gender, String idCardNum, String email, String tel, String address, Date birthday, String eduBg) {
        this.name = name;
        this.gender = gender;
        this.idCardNum = idCardNum;
        this.email = email;
        this.tel = tel;
        this.address = address;
        this.birthday = birthday;
        this.eduBg = eduBg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEduBg() {
        return eduBg;
    }

    public void setEduBg(String eduBg) {
        this.eduBg = eduBg;
    }

    public String getPassword(){
        Assert.hasText(this.idCardNum,"学生的证件号不能为空");
        return this.idCardNum.substring(idCardNum.length()-6);
    }

    @Override
    public String toString() {
        return "Student4Save{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", idCardNum='" + idCardNum + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", eduBg='" + eduBg + '\'' +
                '}';
    }
}
