package org.forten.si.bo;

import org.forten.si.dto.RoWithPage;
import org.forten.si.dto.Student4List;
import org.forten.si.dto.Student4Save;
import org.forten.si.dto.StudentQo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/**/app-*.xml"})
public class StudentBoTest {
    @Resource
    private StudentBo bo;

    @Test
    public void testDoSave(){
        Student4Save dto = new Student4Save();
        dto.setAddress("address");
        dto.setBirthday(new Date());
        dto.setEduBg("大本");
        dto.setEmail("test@tom.com");
        dto.setGender("M");
        dto.setIdCardNum("110101199909091111");
        dto.setName("Tom");
        dto.setTel("1333333333");

//        bo.doSave(dto);
    }

    @Test
    public void testQueryBy(){
        RoWithPage<Student4List> ro = bo.queryBy(new StudentQo());
        Assert.assertEquals(2,ro.getDataList().size());
        Assert.assertEquals(3,ro.getPage().getTotalPage());

        StudentQo qo = new StudentQo();
        qo.setName("刘");
        ro = bo.queryBy(new StudentQo());
        Assert.assertEquals(1,ro.getDataList().size());
        Assert.assertEquals(1,ro.getPage().getTotalPage());

        qo.setName("岑");
        ro = bo.queryBy(new StudentQo());
        Assert.assertEquals(0,ro.getDataList().size());
        Assert.assertEquals(0,ro.getPage().getTotalPage());
    }
}
