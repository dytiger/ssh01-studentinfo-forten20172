package org.forten.si.dto;

import org.forten.utils.common.StringUtil;
import org.forten.utils.system.PropertiesFileReader;

/**
 * Created by Administrator on 2017/7/4.
 */
public class StudentQo {
    private String name;
    private int pageNo;
    private int pageSize;

    public StudentQo() {
        this.name = "";
        this.pageNo = 1;
        this.pageSize = Integer.parseInt(PropertiesFileReader.getValue("system/system","DEFAULT_PAGE_SIZE"));
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "StudentQo{" +
                "name='" + name + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
