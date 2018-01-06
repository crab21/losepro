package mvc.slice.biz.paging;

public class PageInfoBean {
    /*总页数*/
    private int total;

    /*每一页的总大小*/
    private int pageSize;

    /*上一页*/
    private String foreignPage;

    /*下一页*/
    private String nextPage;

    /*中间展示的个数*/
    private int showSize;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getForeignPage() {
        return foreignPage;
    }

    public void setForeignPage(String foreignPage) {
        this.foreignPage = foreignPage;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public int getShowSize() {
        return showSize;
    }

    public void setShowSize(int showSize) {
        this.showSize = showSize;
    }
}
