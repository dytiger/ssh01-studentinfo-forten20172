package org.forten.si.dto;

import org.forten.utils.common.DateUtil;
import org.forten.utils.common.StringUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/3.
 */
public class Student4Update {
    private String name;
    private String gender;
    private String idCardNum;
    private String email;
    private String tel;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String eduBg;

    public Student4Update() {
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

    public String getBirthdayStr() {
        return birthday == null ? "" : DateUtil.convertDateToString(birthday, "yyyy-MM-dd");
    }


    public String getGenderDes(){
        if(!StringUtil.hasText(gender)){
            return "未知";
        }else{
            if(gender.equals("M")){
                return "男";
            }else{
                return "女";
            }
        }
    }

    @Override
    public String toString() {
        return "Student4List{" +
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
