package com.oa.bean;

//分页实体类--封装分页信息
public class PageInfo {
    private int pageIndex;//当前页码
    private int pageSize = 3;//每页显示记录条数
    private int startIndex;//查询的起始位置
    private int prePage;//上一页
    private int nextPage;//下一页
    private int pages;//总页数
    private int total;//总记录条数

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    //设定查询的起始位置的算法
    public int getStartIndex() {
        startIndex = (pageIndex - 1) * pageSize;//起始位置：（当前页码-1）*每页显示记录条数
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    //获取上一页
    public int getPrePage() {
        prePage = pageIndex - 1;
        //判断
        if (prePage <= 0) {
            return 1;
        } else {
            return prePage;
        }
    }

    //上一页做合理化操作
    public void setPrePage(int prePage) {
        //判断
        if (prePage <= 0) {
            this.prePage = 1;
        } else
            this.prePage = prePage;
    }

    //下一页
    public int getNextPage() {
        nextPage = pageIndex + 1;
        //判断
        if (nextPage> getPages()) {
           return getPages();
        } else{
            return nextPage;
        }
    }

    //下一页做合理化操作
    public void setNextPage(int nextPage) {
        //判断
        if (nextPage > getPages()) {
            this.nextPage = getPages();
        } else
            this.nextPage = nextPage;
    }

    //总页数
    public int getPages() {
        //(总记录条数+每页显示记录条数-1)/每页显示记录条数
        pages = (total + pageSize - 1) / pageSize;
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public PageInfo() {
    }

    //提供当前页码以及总记录数
    public PageInfo(int pageIndex, int total) {
        this.pageIndex = pageIndex;
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", startIndex=" + startIndex +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", pages=" + pages +
                ", total=" + total +
                '}';
    }
}
