package org.forten.si.bo;

import org.forten.si.dao.HibernateDao;
import org.forten.si.dto.RoWithPage;
import org.forten.si.dto.Student4List;
import org.forten.si.dto.Student4Save;
import org.forten.si.dto.StudentQo;
import org.forten.si.entity.Student;
import org.forten.utils.common.StringUtil;
import org.forten.utils.system.BeanPropertyUtil;
import org.forten.utils.system.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.swing.StringUIClientPropertyKey;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Transactional(readOnly = true)
    public RoWithPage<Student4List> queryBy(StudentQo qo){
        String countHql = "SELECT count(id) FROM Student WHERE 1=1 ";
        String hql = "SELECT new org.forten.si.dto.Student4List(id, name, gender, idCardNum, email, tel, address, birthday, eduBg, status, registTime) FROM Student WHERE 1=1 ";
        Map<String,Object> params = new HashMap<>();

        if(StringUtil.hasText(qo.getName())){
            countHql = countHql + "AND name LIKE :name ";
            hql = hql + "AND name LIKE :name ";
            params.put("name","%"+qo.getName()+"%");
        }

        hql = hql + "ORDER BY registTime DESC";

        long count = dao.findOneBy(countHql,params);

        if(count==0){
            // 如果没有符合查询条件的数据，则返回安全的空Ro对象
            return new RoWithPage<>(new ArrayList<>(), PageInfo.getInstance(1,1,0));
        }

        PageInfo page = PageInfo.getInstance(qo.getPageNo(),qo.getPageSize(),count);

        List<Student4List> list = dao.findBy(hql,params,(int)page.getFirstResultNum(),page.getPageSize());

        return new RoWithPage<>(list,page);
    }
}
