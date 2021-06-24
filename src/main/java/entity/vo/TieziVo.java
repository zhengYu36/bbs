package entity.vo;

import entity.Tiezi;
import untils.PageInfoUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//里面包含了热帖和普通帖子
public class TieziVo<T> {

    //普通帖子
    List<Tiezi> pt = new ArrayList<>();

    //热帖
    List<Tiezi> hot = new ArrayList<>();

    private PageInfoUtils<T> pageInfo;

    public PageInfoUtils<T> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoUtils<T> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Tiezi> getPt() {
        return pt;
    }

    public void setPt(List<Tiezi> pt) {
        this.pt = pt;
    }

    public List<Tiezi> getHot() {
        return hot;
    }

    public void setHot(List<Tiezi> hot) {
        this.hot = hot;
    }
}
