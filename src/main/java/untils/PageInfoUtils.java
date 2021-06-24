package untils;

import java.util.ArrayList;
import java.util.List;

public class PageInfoUtils<T> {

    //总页数
    private long pageCount;

    //当前页数
    private long currentPage;

    //每页大小默认为6
    private int pageSize = 3;

    //分页数据
    private List<T> data = new ArrayList<>();

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
