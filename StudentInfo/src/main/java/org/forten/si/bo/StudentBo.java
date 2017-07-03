package org.forten.si.bo;

import org.forten.si.dao.HibernateDao;
import org.forten.si.dto.Student4Save;
import org.forten.si.entity.Student;
import org.forten.utils.system.BeanPropertyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/7/3.
 */
@Service("studentBo")
public class StudentBo {
    @Resource
    private HibernateDao dao;

    @Transactional
    public void doSave(Student4Save dto){
        Student stu = new Student();
        BeanPropertyUtil.copy(stu,dto);
        dao.save(stu);
    }
}
