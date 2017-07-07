package org.forten.si.action;

import org.forten.si.bo.StudentBo;
import org.forten.si.dto.*;
import org.forten.utils.system.PropertiesFileReader;
import org.forten.utils.system.ValidateException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2017/7/5.
 */
@Controller
@RequestMapping("/")
@SessionAttributes({"logined","isAdmin"})
public class StudentAction {
    @Resource
    private StudentBo bo;

    @RequestMapping("student/show")
    public @ResponseBody
    Student4List show(HttpSession session) {
        LoginedUser stu = (LoginedUser) session.getAttribute("logined");
        int id = stu.getId();
        Student4List dto = bo.queryBy(id);

        return dto;
    }

    @RequestMapping("student/modifyPassword")
    public @ResponseBody
    Message modifyPassword(String oldPwd, String newPwd, HttpSession session) {
        LoginedUser stu = (LoginedUser) session.getAttribute("logined");
        int id = stu.getId();
        return bo.doModifyPassword(id, oldPwd, newPwd);
    }

    @RequestMapping("student/update")
    public @ResponseBody
    Message update(@RequestBody Student4Update dto, HttpSession session) {
        LoginedUser stu = (LoginedUser) session.getAttribute("logined");
        int id = stu.getId();
        return bo.doUpdate(id, dto);
    }

    @RequestMapping("login")
    public String login(String name, String password, Model model) {
        if (name.equals("admin") && password.equals(PropertiesFileReader.getValue("system/system", "ADMIN_PWD"))) {
            LoginedUser stu = new LoginedUser(0,"admin",PropertiesFileReader.getValue("system/system", "ADMIN_EMAIL"));
            model.addAttribute("logined", stu);
            model.addAttribute("isAdmin",true);
            return "redirect:admin/index.html";
        } else {
            LoginedUser stu = bo.login(name, password);
            if (stu == null) {
                return "redirect:login.html";
            } else {
                model.addAttribute("logined", stu);
                model.addAttribute("isAdmin",false);
                return "redirect:student/index.html";
            }
        }
    }

    @RequestMapping("forgetPwd")
    public @ResponseBody
    Message forgetPwd(String name) {
        return bo.forgetPwd(name);
    }

    @RequestMapping("regist")
    public @ResponseBody
    Message save(@RequestBody Student4Save4User dto) {
        try {
            bo.doRegist(dto);
            return new Message("注册成功");
        }catch(ValidateException e){
            List<String> errors = e.getMessages();
            StringBuilder sb = new StringBuilder();
            for (String msg:errors) {
                sb.append(msg);
                sb.append("<br/>");
            }
            return new Message(sb.toString());
        }catch (Exception e) {
            e.printStackTrace();
            return new Message("注册失败");
        }
    }

    @RequestMapping("existsEmail")
    public @ResponseBody
    Boolean existsEmail(String email) {
        return !bo.existsEmail(email);
    }
}
