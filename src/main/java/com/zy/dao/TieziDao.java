package com.zy.dao;


import com.zy.entity.Replytiezi;
import com.zy.entity.Tiezi;

import java.util.List;

public interface TieziDao {
    /**
     * 查询全部帖子
     * @return
     */
    public List<Tiezi> TieziShow();

    public List<Tiezi> TieziShowPage(String sql);

    //执行sql
    public long tieziTotal(String sql);

    public List<Tiezi> hottie();

    /**
     * 查询单个帖子
     * @return
     */
    public List<Tiezi> TieziSingleShow(Tiezi tiezi);
    public List<Replytiezi> replyTieziSingleShow(Tiezi tiezi);

    /**
     * 发帖
     * @param tiezi
     * @return
     */
    public void fatie(Tiezi tiezi);

    public void huitie(Replytiezi tiezi);

    public void deleteTiezi(int id);

    public void jiajing(int id);


    public void replytie(Replytiezi tiezi);
}
