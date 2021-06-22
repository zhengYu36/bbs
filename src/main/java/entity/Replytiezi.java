package entity;

import java.util.Date;

//回帖信息表
public class Replytiezi {
    private int tid;

    //内容
    private String tcontent;

    //创建时间
    private Date tdate;

    //帖子id
    private Integer pid;

    //用户id
    private Integer uid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTcontent() {
        return tcontent;
    }

    public void setTcontent(String tcontent) {
        this.tcontent = tcontent;
    }

    public Date getTdate() {
        return tdate;
    }

    public void setTdate(Date tdate) {
        this.tdate = tdate;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
