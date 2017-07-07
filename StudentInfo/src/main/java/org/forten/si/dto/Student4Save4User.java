package org.forten.si.dto;

import org.forten.utils.security.SHA1Util;
import org.forten.utils.system.Assert;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/3.
 */
public class Student4Save4User {
    @NotBlank(message = "请输入姓名")
    @Length(min=2,max=10,message = "姓名长度非法，请输入{min}到{max}之间个字符")
    private String name;
    @NotBlank(message = "请输入密码")
    @Length(min=40,max=40,message = "密码长度非法，请输入{min}到{max}之间个字符")
    private String password;
    @NotBlank(message = "请选择性别")
    @Pattern(regexp = "(F|M)",message = "性别只能取M或F两值中的一个")
    private String gender;
    @NotBlank(message = "请输入身份证号")
    @Pattern(regexp = "^[1-9]\\d{5}[1-2]\\d{3}((0[1-9])|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$",message = "输入18位身份证号的正确格式")
    private String idCardNum;
    @NotBlank(message = "请输入Email")
    @Email(message="请输入正确的Email格式")
    private String email;
    private String tel;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "生日不可以输入将来的日期")
    private Date birthday;
    private String eduBg;

    public Student4Save4User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "Student4Save4User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
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
