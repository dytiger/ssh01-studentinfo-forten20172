package org.forten.si.action;

import org.forten.si.bo.StudentBo;
import org.forten.si.dto.LoginedUser;
import org.forten.si.dto.Message;
import org.forten.si.dto.Student4List;
import org.forten.si.dto.Student4Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/7/5.
 */
@Controller
@RequestMapping("/")
@SessionAttributes({"logined"})
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
        LoginedUser stu = bo.login(name, password);
        if (stu == null) {
            return "redirect:login.html";
        } else {
            model.addAttribute("logined", stu);
            return "redirect:student/index.html";
        }
    }

    @RequestMapping("forgetPwd")
    public @ResponseBody Message forgetPwd(String name){
        return bo.forgetPwd(name);
    }
}
