package com.zy.entity;

import java.io.Serializable;

public class Users  implements Serializable{
    private Integer uid;

    private String uname;

    private String upwd;

    private String uquestion;

    private String uanswer;

    private String uemail;

    private String utype;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd == null ? null : upwd.trim();
    }

    public String getUquestion() {
        return uquestion;
    }

    public void setUquestion(String uquestion) {
        this.uquestion = uquestion == null ? null : uquestion.trim();
    }

    public String getUanswer() {
        return uanswer;
    }

    public void setUanswer(String uanswer) {
        this.uanswer = uanswer == null ? null : uanswer.trim();
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail == null ? null : uemail.trim();
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype == null ? null : utype.trim();
    }
}