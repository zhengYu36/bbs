package com.zy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Tiezi implements Serializable {
    private Integer tid;

    //标题
    private String title;

    //内容
    private String tcontent;

    //创建时间
    private Date tdate;

    //浏览人数
    private Integer tnum1;

    //回帖人数
    private Integer tnum2;

    private Integer pid;

    private Integer uid;
    private String uname;

    //0 不是精帖  1 是精帖
    private Integer status;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    //回帖信息
    private List<Replytiezi> replytiezis;

    public List<Replytiezi> getReplytiezis() {
        return replytiezis;
    }

    public void setReplytiezis(List<Replytiezi> replytiezis) {
        this.replytiezis = replytiezis;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTcontent() {
        return tcontent;
    }

    public void setTcontent(String tcontent) {
        this.tcontent = tcontent == null ? null : tcontent.trim();
    }

    public Date getTdate() {
        return tdate;
    }

    public void setTdate(Date tdate) {
        this.tdate = tdate;
    }

    public Integer getTnum1() {
        return tnum1;
    }

    public void setTnum1(Integer tnum1) {
        this.tnum1 = tnum1;
    }

    public Integer getTnum2() {
        return tnum2;
    }

    public void setTnum2(Integer tnum2) {
        this.tnum2 = tnum2;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}