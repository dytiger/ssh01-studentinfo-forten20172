package org.forten.si.action;

import org.forten.si.bo.StudentBo;
import org.forten.si.dto.Message;
import org.forten.si.dto.Student4List;
import org.forten.si.dto.Student4Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/7/5.
 */
@Controller
@RequestMapping("/")
public class StudentAction {
    @Resource
    private StudentBo bo;

    @RequestMapping("show")
    public @ResponseBody
    Student4List show(){
        // TODO 从Session中得当前登录学生的id
        int id = 1;
        Student4List dto = bo.queryBy(id);

        return dto;
    }

    @RequestMapping("modifyPassword")
    public @ResponseBody
    Message modifyPassword(String oldPwd, String newPwd){
        // TODO 从Session中得当前登录学生的id
        int id = 1;
        return bo.doModifyPassword(id,oldPwd,newPwd);
    }

    @RequestMapping("update")
    public @ResponseBody Message update(@RequestBody Student4Update dto){
        // TODO 从Session中得当前登录学生的id
        int id = 1;
        return bo.doUpdate(id,dto);
    }

}
