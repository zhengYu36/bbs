package com.zy.dao.impl;


import com.zy.dao.TieziDao;
import com.zy.entity.Replytiezi;
import com.zy.entity.Tiezi;
import com.zy.untils.DaoUtlis;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TieziDaoImpl extends DaoUtlis implements TieziDao {
    /**
     * 查询全部帖子
     * @return
     */
    @Override
    public List<Tiezi> TieziShow() {
        String sql = "select * from tiezi order by tdate desc";
        //查询数据
        List<Tiezi> list = super.query(sql,null,Tiezi.class);
        return (list!=null&&list.size()>0?list:null);
    }

    @Override
    public List<Tiezi> TieziShowPage(String sql) {

        //查询数据
        List<Tiezi> list = super.query(sql,null,Tiezi.class);
        return (list!=null&&list.size()>0?list:null);
    }

    @Override
    public long tieziTotal(String sql) {
        List<Tiezi> list = super.query(sql, null, Tiezi.class);
        return (list!=null&&list.size()>0?list.size():0);
    }

    //查询热帖
    @Override
    public List<Tiezi> hottie() {
        String sql = "select * from tiezi where status = 1 order by tdate desc limit 9";
        List<Tiezi> list = super.query(sql,null,Tiezi.class);
        return (list!=null&&list.size()>0?list:null);
    }


    /**
     * 查询单个帖子
     * @param tiezi
     * @return
     */
    @Override
    public List<Tiezi> TieziSingleShow(Tiezi tiezi) {
        String sql = "select * from tiezi where tid = ?";
        String sql_num1 = "update tiezi set tnum1 = tnum1+1 where tid = ?";
        Object[] num = {tiezi.getTid()};
        super.update(sql_num1,num);
        List<Tiezi> tieziList = super.query(sql,num,Tiezi.class);
        return (tieziList!=null&&tieziList.size()>0?tieziList:null);
    }

    @Override
    public List<Replytiezi> replyTieziSingleShow(Tiezi tiezi) {
        String sql = "select t1.*,t2.uname from replytiezi t1 left join users t2 on t1.uid = t2.uid where t1.pid = ?";
        Object[] num = {tiezi.getTid()};
        List<Replytiezi> replytiezis = super.query(sql,num,Replytiezi.class);
        return (replytiezis!=null&&replytiezis.size()>0?replytiezis:null);
    }


    /**
     * 发帖
     * @param tiezi
     * @return
     */
    @Override
    public void fatie(Tiezi tiezi) {
        String sql = "INSERT INTO tiezi (title,tcontent,tdate,tnum1,tnum2,pid,uid,status) VALUES(?,?,?,?,?,?,?,?);";
        Object[] num = {tiezi.getTitle(),tiezi.getTcontent(),new Date(),0,0,0,tiezi.getUid(),0};
        super.update(sql,num);
    }


    @Override
    public void huitie(Replytiezi tiezi) {
        String sql = "INSERT INTO replytiezi (tcontent,tdate,pid,uid) VALUES(?,?,?,?);";
        Object[] num = {tiezi.getTcontent(),tiezi.getTdate(),tiezi.getPid(),tiezi.getUid()};
        super.update(sql,num);
    }

    @Override
    public void deleteTiezi(int id) {
        String sql = "delete from tiezi where tid = ?";
        Object[] num = {id};
        super.update(sql,num);
    }


    @Override
    public void jiajing(int id) {
        String sql = "update tiezi set status = 1 where tid = ?";
        Object[] num = {id};
        super.update(sql,num);
    }

    @Override
    public void replytie(Replytiezi tiezi) {
        String sql = "insert into replytiezi (tcontent,pid,uid) values(?,?,?)";
        Object[] num = {tiezi.getTcontent(),tiezi.getPid(),tiezi.getUid()};
        super.update(sql,num);
    }
}
