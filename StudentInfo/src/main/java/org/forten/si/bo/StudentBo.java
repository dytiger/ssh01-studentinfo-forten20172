package org.forten.si.bo;

import org.forten.si.dao.HibernateDao;
import org.forten.si.dto.Message;
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

import javax.annotation.Resource;
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
            return EMPTY_RO;
        }

        PageInfo page = PageInfo.getInstance(qo.getPageNo(),qo.getPageSize(),count);

        List<Student4List> list = dao.findBy(hql,params,(int)page.getFirstResultNum(),page.getPageSize());

        return new RoWithPage<>(list,page);
    }

    @Transactional(readOnly = true)
    public RoWithPage<Student4List> queryBy2(StudentQo qo){
        // 参数Map
        Map<String,Object> params = new HashMap<>();
        // 用于统计符合条件的数据量
        String countHql = "SELECT count(id) FROM Student WHERE 1=1 ";
        if(StringUtil.hasText(qo.getName())){
            countHql = countHql + "AND name LIKE :name ";
            params.put("name","%"+qo.getName()+"%");
        }

        // 得到符合查询条件的数据量
        long count = dao.findOneBy(countHql,params);

        if(count==0){
            // 如果没有符合查询条件的数据，则返回安全的空Ro对象
            return EMPTY_RO;
        }

        // 重置参数Map
        params.clear();
        // 用于查询符合查询条件的数据（DTO）
        String hql = "SELECT new org.forten.si.dto.Student4List(id, name, gender, idCardNum, email, tel, address, birthday, eduBg, status, registTime) FROM Student WHERE 1=1 ";

        if(StringUtil.hasText(qo.getName())){
            hql = hql + "AND name LIKE :name ";
            params.put("name","%"+qo.getName()+"%");
        }

        hql = hql + "ORDER BY registTime DESC";

        // 通过当前页码、页容量、符合条件的数据数量计算出与分页相关的其它数据
        // （共几页、当前页第一条数据是总数据集合中的第几条、当前页的最后一条数据是总数据集合中的第几条、当前页码、页容量、是否是第一页、是否是最后一页）
        // 这些数据都被封装在PageInfo对象中
        PageInfo page = PageInfo.getInstance(qo.getPageNo(),qo.getPageSize(),count);

        List<Student4List> list = dao.findBy(hql,params,(int)page.getFirstResultNum(),page.getPageSize());

        return new RoWithPage<>(list,page);
    }

    @Transactional
    public Message doDelete(Integer... ids){
        String hql = "DELETE FROM Student WHERE id IN (:ids)";
        Map<String,Object> params = new HashMap<>(1);
        // 因为HQL中的IN只能绑定Collection类型的数据，所以要把数组转换为List或Set
        params.put("ids", Arrays.asList(ids));
        try {
            int n = dao.executeUpdate(hql, params);
            return new Message("成功删除了"+n+"条数据");
        }catch(Exception e){
            e.printStackTrace();
            return new Message("删除时出现错误");
        }
    }

    @Transactional
    public Message doChangeStatus(int id,String status){
        String hql = "UPDATE Student SET status=:s WHERE id=:id";
        Map<String ,Object> params = new HashMap<>(2);
        params.put("s",status);
        params.put("id",id);

        try {
            int n = dao.executeUpdate(hql, params);
            return new Message("成功修改ID为["+id+"]学员的状态");
        }catch(Exception e){
            e.printStackTrace();
            return new Message("修改ID为["+id+"]的学员状态时出错");
        }
    }
}
