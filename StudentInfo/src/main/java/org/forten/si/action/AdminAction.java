package org.forten.si.action;

import org.forten.si.bo.StudentBo;
import org.forten.si.dao.Message;
import org.forten.si.dto.RoWithPage;
import org.forten.si.dto.Student4List;
import org.forten.si.dto.Student4Save;
import org.forten.si.dto.StudentQo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/7/3.
 */
@Controller
@RequestMapping("/admin")
public class AdminAction {
    @Resource
    private StudentBo bo;

    @RequestMapping("save")
    public @ResponseBody
    Message save(@RequestBody Student4Save dto){
        try{
            bo.doSave(dto);
            return new Message("学生信息保存成功");
        }catch(Exception e){
            e.printStackTrace();
            return new Message("学生信息保存失败");
        }
    }

    @RequestMapping("list")
    public @ResponseBody
    RoWithPage<Student4List> list(@RequestBody StudentQo qo){
        return bo.queryBy(qo);
    }
}
