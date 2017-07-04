package org.forten.si.dto;

import com.sun.rowset.internal.Row;
import org.forten.utils.collection.CollectionUtil;
import org.forten.utils.system.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */
public class RoWithPage<T> {
    private List<T> dataList;
    private PageInfo page;

    public RoWithPage(List<T> dataList, PageInfo page) {
        this.dataList = dataList;
        this.page = page;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }

    public boolean isEmptyData(){
        return CollectionUtil.isEmpty(dataList);
    }

    @Override
    public String toString() {
        return "RoWithPage{" +
                "dataList=" + dataList +
                ", page=" + page +
                '}';
    }
}
