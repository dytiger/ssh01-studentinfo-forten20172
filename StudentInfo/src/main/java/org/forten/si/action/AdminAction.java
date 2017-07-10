package org.forten.si.action;

import org.apache.poi.ss.usermodel.Workbook;
import org.forten.si.bo.StudentBo;
import org.forten.si.dto.Message;
import org.forten.si.dto.RoWithPage;
import org.forten.si.dto.Student4List;
import org.forten.si.dto.Student4Save;
import org.forten.si.dto.StudentQo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

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
    RoWithPage<Student4List> list(@RequestBody StudentQo qo) {
        return bo.queryBy(qo);
    }

    @RequestMapping("delete")
    public @ResponseBody Message delete(@RequestBody Integer... ids){
        return bo.doDelete(ids);
    }

    @RequestMapping("changeStatus")
    public @ResponseBody Message changeStatus(int id,String status){
        return bo.doChangeStatus(id,status);
    }

    @RequestMapping("export")
    public void export(HttpServletResponse response){
        try(OutputStream out = response.getOutputStream(); Workbook wb = bo.exportData()){
            response.setContentType("application/x-msexcel");
            response.setHeader("Content-Disposition","attachment;filename=students.xls");
            wb.write(out);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
