package org.forten.si.bo;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.forten.si.dao.HibernateDao;
import org.forten.si.dto.*;
import org.forten.si.entity.Student;
import org.forten.utils.common.NumberUtil;
import org.forten.utils.common.StringUtil;
import org.forten.utils.security.SHA1Util;
import org.forten.utils.system.BeanPropertyUtil;
import org.forten.utils.system.PageInfo;
import org.forten.utils.system.ValidateException;
import org.forten.utils.system.ValidateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.forten.si.dto.RoWithPage.EMPTY_RO;

/**
 * Created by Administrator on 2017/7/3.
 */
@Service("studentBo")
public class StudentBo {
    @Resource
    private HibernateDao dao;

    @Transactional
    public void doSave(Student4Save dto) {
        Student stu = new Student();
        BeanPropertyUtil.copy(stu, dto);
        dao.save(stu);
    }

    @Transactional
    public void doRegist(Student4Save4User dto) {
        ValidateUtil.validateThrow(dto);

        Student stu = new Student();
        BeanPropertyUtil.copy(stu, dto);
        dao.save(stu);
    }

    @Transactional(readOnly = true)
    public RoWithPage<Student4List> queryBy(StudentQo qo) {
        String countHql = "SELECT count(id) FROM Student WHERE 1=1 ";
        String hql = "SELECT new org.forten.si.dto.Student4List(id, name, gender, idCardNum, email, tel, address, birthday, eduBg, status, registTime) FROM Student WHERE 1=1 ";
        Map<String, Object> params = new HashMap<>();

        if (StringUtil.hasText(qo.getName())) {
            countHql = countHql + "AND name LIKE :name ";
            hql = hql + "AND name LIKE :name ";
            params.put("name", "%" + qo.getName() + "%");
        }

        hql = hql + "ORDER BY registTime DESC";

        long count = dao.findOneBy(countHql, params);

        if (count == 0) {
            // 如果没有符合查询条件的数据，则返回安全的空Ro对象
            return EMPTY_RO;
        }

        PageInfo page = PageInfo.getInstance(qo.getPageNo(), qo.getPageSize(), count);

        List<Student4List> list = dao.findBy(hql, params, (int) page.getFirstResultNum(), page.getPageSize());

        return new RoWithPage<>(list, page);
    }

    @Transactional(readOnly = true)
    public RoWithPage<Student4List> queryBy2(StudentQo qo) {
        // 参数Map
        Map<String, Object> params = new HashMap<>();
        // 用于统计符合条件的数据量
        String countHql = "SELECT count(id) FROM Student WHERE 1=1 ";
        if (StringUtil.hasText(qo.getName())) {
            countHql = countHql + "AND name LIKE :name ";
            params.put("name", "%" + qo.getName() + "%");
        }

        // 得到符合查询条件的数据量
        long count = dao.findOneBy(countHql, params);

        if (count == 0) {
            // 如果没有符合查询条件的数据，则返回安全的空Ro对象
            return EMPTY_RO;
        }

        // 重置参数Map
        params.clear();
        // 用于查询符合查询条件的数据（DTO）
        String hql = "SELECT new org.forten.si.dto.Student4List(id, name, gender, idCardNum, email, tel, address, birthday, eduBg, status, registTime) FROM Student WHERE 1=1 ";

        if (StringUtil.hasText(qo.getName())) {
            hql = hql + "AND name LIKE :name ";
            params.put("name", "%" + qo.getName() + "%");
        }

        hql = hql + "ORDER BY registTime DESC";

        // 通过当前页码、页容量、符合条件的数据数量计算出与分页相关的其它数据
        // （共几页、当前页第一条数据是总数据集合中的第几条、当前页的最后一条数据是总数据集合中的第几条、当前页码、页容量、是否是第一页、是否是最后一页）
        // 这些数据都被封装在PageInfo对象中
        PageInfo page = PageInfo.getInstance(qo.getPageNo(), qo.getPageSize(), count);

        List<Student4List> list = dao.findBy(hql, params, (int) page.getFirstResultNum(), page.getPageSize());

        return new RoWithPage<>(list, page);
    }

    @Transactional
    public Message doDelete(Integer... ids) {
        String hql = "DELETE FROM Student WHERE id IN (:ids)";
        Map<String, Object> params = new HashMap<>(1);
        // 因为HQL中的IN只能绑定Collection类型的数据，所以要把数组转换为List或Set
        params.put("ids", Arrays.asList(ids));
        try {
            int n = dao.executeUpdate(hql, params);
            return new Message("成功删除了" + n + "条数据");
        } catch (Exception e) {
            e.printStackTrace();
            return new Message("删除时出现错误");
        }
    }

    @Transactional
    public Message doChangeStatus(int id, String status) {
        String hql = "UPDATE Student SET status=:s WHERE id=:id";
        Map<String, Object> params = new HashMap<>(2);
        params.put("s", status);
        params.put("id", id);

        try {
            int n = dao.executeUpdate(hql, params);
            return new Message("成功修改ID为[" + id + "]学员的状态");
        } catch (Exception e) {
            e.printStackTrace();
            return new Message("修改ID为[" + id + "]的学员状态时出错");
        }
    }

    @Transactional(readOnly = true)
    public Student4List queryBy(int id) {
        Student s = dao.loadById(id, Student.class);
        Student4List dto = new Student4List();

        BeanPropertyUtil.copy(dto, s);
        return dto;
    }

    @Transactional
    public Message doModifyPassword(int id, String oldPwd, String newPwd) {
        String hql = "SELECT count(id) FROM Student WHERE id=:id AND password=:oldPwd";
        Map<String, Object> params = new HashMap<>(2);
        params.put("id", id);
        params.put("oldPwd", oldPwd);

        long count = dao.findOneBy(hql, params);
        if (count == 0) {
            return new Message("你输入的旧密码错误，请重新输入。");
        }

        hql = "UPDATE Student SET password=:pwd WHERE id=:id";
        params.clear();
        params.put("id", id);
        params.put("pwd", newPwd);

        try {
            dao.executeUpdate(hql, params);
            return new Message("密码修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Message("密码修改失败");
        }
    }

    @Transactional
    public Message doUpdate(int id, Student4Update dto) {
        Student s = dao.loadById(id, Student.class);
        BeanPropertyUtil.copy(s, dto);
        try {
            dao.update(s);
            return new Message("更新信息成功");
        } catch (Exception e) {
            return new Message("更新信息失败");
        }
    }

    @Transactional(readOnly = true)
    public LoginedUser login(String name, String password) {
        String hql = "SELECT new org.forten.si.dto.LoginedUser(id,name,email) FROM Student WHERE name=:n AND password=:p";
        Map<String, Object> params = new HashMap<>(2);
        params.put("n", name);
        params.put("p", password);

        return dao.findOneBy(hql, params);
    }

    @Transactional
    public Message forgetPwd(String name) {
        String hql = "SELECT new org.forten.si.dto.Student4List(id, name, gender, idCardNum, email, tel, address, birthday, eduBg, status, registTime) FROM Student WHERE name=:n";
        Map<String, Object> params = new HashMap<>(1);
        params.put("n", name);

        Student4List dto = dao.findOneBy(hql, params);
        if (dto == null) {
            return new Message("用户名不存在");
        }

        String pwdCode = getRandomStr();
        String shaPwdCode = SHA1Util.encryptSHA(pwdCode);
        hql = "UPDATE Student SET password=:fp WHERE id=:id";
        params.clear();
        params.put("id", dto.getId());
        params.put("fp", shaPwdCode);
        dao.executeUpdate(hql, params);

        new Thread(() -> {
            try {
                HtmlEmail email = new HtmlEmail();
                email.setHostName("smtp.126.com");
                email.setCharset("UTF-8");
                email.setSmtpPort(465);
                email.setAuthenticator(new DefaultAuthenticator("thcic_test", "a123456"));
                email.setSSLOnConnect(true);
                email.addTo(dto.getEmail(), dto.getName());
                email.setFrom("thcic_test@126.com", "System");
                email.setSubject("重置密码");
                email.setHtmlMsg("<p>系统已经为你重置密码，新密码为：<strong>" + pwdCode + "</strong></p><p>请尽快<a href='http://localhost:8081/login.html'>登录</a>系统修改密码！</p>");
                email.send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).run();

        return new Message("已经向<strong>" + dto.getEmail() + "</strong>发送了密码重置邮件，请查收");
    }

    @Transactional(readOnly = true)
    public boolean existsEmail(String email) {
        String hql = "SELECT count(id) FROM Student WHERE email=:email";
        Map<String, Object> params = new HashMap<>(1);
        params.put("email", email);

        long count = dao.findOneBy(hql, params);
        return count == 1L;
    }

    @Transactional(readOnly = true)
    public Workbook exportData() {
        String hql = "SELECT new org.forten.si.dto.Student4Export(name,tel,email,address,idCardNum,birthday) " +
                "FROM Student ORDER BY registTime";

        List<Student4Export> dtoList = dao.findBy(hql);

        // 2003格式的Excel工作簿模型
        Workbook wb = new HSSFWorkbook();
        // 2007格式的Excel工作簿模型
        // Workbook wb = new XSSFWorkbook();

        // 在Workbook中创建一个sheet
        Sheet sheet = wb.createSheet("学员联系信息");

        // 在sheet中创建表头行
        Row header = sheet.createRow(0);

        // 创建表头行中的单元格及表头标题
        Cell c1 = header.createCell(0);
        c1.setCellValue("序号");
        header.createCell(1).setCellValue("姓名");
        header.createCell(2).setCellValue("电话");
        header.createCell(3).setCellValue("Email");
        header.createCell(4).setCellValue("地址");
        header.createCell(5).setCellValue("身份证号");
        header.createCell(6).setCellValue("生日");

        // 生成数据行
        for (Student4Export dto : dtoList) {
            Row row = sheet.createRow(sheet.getLastRowNum()+1);
            row.createCell(0).setCellValue(sheet.getLastRowNum());
            row.createCell(1).setCellValue(dto.getName());
            row.createCell(2).setCellValue(dto.getTel());
            row.createCell(3).setCellValue(dto.getEmail());
            row.createCell(4).setCellValue(dto.getAddress());
            row.createCell(5).setCellValue(dto.getIdCardNum());
            row.createCell(6).setCellValue(dto.getBirthdayStr());
        }

        return wb;
    }

    private String getRandomStr() {
        String s = "";
        for (int i = 0; i < 6; i++) {
            s = s + (char) NumberUtil.getRandomInteger(97, (97 + 25));
        }
        return s;
    }
}
