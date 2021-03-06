package org.forten.si.dto;

import org.forten.utils.common.DateUtil;
import org.forten.utils.common.StringUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/3.
 */
public class Student4List {
    private int id;
    private String name;
    private String gender;
    private String idCardNum;
    private String email;
    private String tel;
    private String address;
    private Date birthday;
    private String eduBg;
    private String status;
    private Date registTime;

    public Student4List() {
    }

    public Student4List(int id, String name, String gender, String idCardNum, String email, String tel, String address, Date birthday, String eduBg, String status, Date registTime) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.idCardNum = idCardNum;
        this.email = email;
        this.tel = tel;
        this.address = address;
        this.birthday = birthday;
        this.eduBg = eduBg;
        this.status = status;
        this.registTime = registTime;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public String getBirthdayStr() {
        return birthday == null ? "" : DateUtil.convertDateToString(birthday, "yyyy-MM-dd");
    }

    public String getRegistTimeStr() {
        return registTime == null ? "" : DateUtil.convertDateToString(registTime, "yyyy-MM-dd HH:mm");
    }

    public String getStatusDes() {
        if (StringUtil.hasText(status)) {
            switch (status) {
                case "BM":
                    return "报名";
                case "SK":
                    return "上课";
                case "BY":
                    return "毕业";
                case "XX":
                    return "休学";
                case "TX":
                    return "退学";
                case "CX":
                    return "重修";
                default:
                    return "未知";

            }
        } else {
            return "未知";
        }
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", idCardNum='" + idCardNum + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", eduBg='" + eduBg + '\'' +
                ", status='" + status + '\'' +
                ", registTime=" + registTime +
                '}';
    }
}
