package mvc.slice.pojo.paging;

public class PageInfoBean {
    /*总页数*/
    private int total;

    /*每一页的总大小*/
    private final int pageSize = 20;

    /*上一页  下一页 通过索引来快速定位*/
    private int pageForeId = 0;

    private int pageLastId = 0;

    /*标记是上一页还是下一页*/
    private int pageFlag = 1;



    /*本页的页码*/
    private int pageNumber = 1;
    /*中间展示的个数*/
    private int showSize;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageForeId() {
        return pageForeId;
    }

    public void setPageForeId(int pageForeId) {
        this.pageForeId = pageForeId;
    }

    public int getPageLastId() {
        return pageLastId;
    }

    public void setPageLastId(int pageLastId) {
        this.pageLastId = pageLastId;
    }

    public int getPageFlag() {
        return pageFlag;
    }

    public void setPageFlag(int pageFlag) {
        this.pageFlag = pageFlag;
    }

    public int getShowSize() {
        return showSize;
    }

    public void setShowSize(int showSize) {
        this.showSize = showSize;
    }
}
