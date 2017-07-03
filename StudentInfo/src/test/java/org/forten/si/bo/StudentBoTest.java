package org.forten.si.bo;

import org.forten.si.dto.Student4Save;
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

        bo.doSave(dto);
    }
}
