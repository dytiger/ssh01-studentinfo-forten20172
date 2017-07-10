package org.forten.si.action;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.forten.si.bo.StudentBo;
import org.forten.si.dto.Message;
import org.forten.si.dto.RoWithPage;
import org.forten.si.dto.Student4List;
import org.forten.si.dto.Student4Save;
import org.forten.si.dto.StudentQo;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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

    // @RequestMapping("importData")
    public String importDataSaveFile(MultipartFile excelFile,HttpServletRequest req) throws IOException, InvalidFormatException {
        // 得到上传文件在客户端的原始名称
        String filename = excelFile.getOriginalFilename();
        // 得到项目所在磁盘的绝对根路径下的upload位置
        String uploadPathStr = req.getServletContext().getRealPath("/upload");
        // 创建项目所在磁盘的绝对根路径下的upload位置的文件对象
        File uploadPath = new File(uploadPathStr);
        // 如果项目所在磁盘的绝对根路径下的upload目录不存在，则创建它
        if(!uploadPath.exists()){
            uploadPath.mkdir();
        }

        // 把上传的文件内容写入到一个新的文件对象上，这个文件对象被指定存储在项目所在磁盘的绝对根路径下的upload目录中
        FileCopyUtils.copy(excelFile.getBytes(),new File(uploadPath+"/"+filename));

        return "index.html";
    }

    @RequestMapping("importData")
    public String importData(MultipartFile excelFile) throws IOException, InvalidFormatException {

        Workbook wb = WorkbookFactory.create(excelFile.getInputStream());
        System.out.println(wb.getSheetAt(0).getRow(0).getCell(0).getStringCellValue());

        return "index.html";
    }
}
