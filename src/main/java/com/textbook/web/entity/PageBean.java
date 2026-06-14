package com.textbook.web.entity;

import java.util.List;

public class PageBean<T> {
    private Integer pageNo;      // 当前页码
    private Integer pageSize;    // 每页显示的条数
    private Integer totalCount;  // 总记录数
    private Integer totalPage;   // 总页数
    private List<T> list;        // 当前页的数据集合

    public PageBean() {}

    public Integer getPageNo() { return pageNo; }
    public void setPageNo(Integer pageNo) { this.pageNo = pageNo; }

    public Integer getPageSize() { return pageSize; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }

    public Integer getTotalCount() { return totalCount; }
    public void setTotalCount(Integer totalCount) { this.totalCount = totalCount; }

    // 总页数由总记录数和每页条数自动计算
    public Integer getTotalPage() {
        return (int) Math.ceil((double) totalCount / pageSize);
    }
    public void setTotalPage(Integer totalPage) { this.totalPage = totalPage; }

    public List<T> getList() { return list; }
    public void setList(List<T> list) { this.list = list; }
}