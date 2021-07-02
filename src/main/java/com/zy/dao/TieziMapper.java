package com.zy.dao;

import com.zy.entity.Replytiezi;
import com.zy.entity.Tiezi;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TieziMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Tiezi record);

    int insertSelective(Tiezi record);

    Tiezi selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Tiezi record);

    int updateByPrimaryKey(Tiezi record);


    /**
     * 查询全部帖子
     * @return
     */
    public List<Tiezi> TieziShow();

    public List<Tiezi> TieziShowPage(@Param(value ="sql")String sql);

    //执行sql
    public List<Tiezi> tieziTotal(@Param(value ="sql") String sql);

    public List<Tiezi> hottie();

    /**
     * 查询单个帖子
     * @return
     */
    //注意要更新一下浏览次数
    public List<Tiezi> TieziSingleShow(Tiezi tiezi);

    //浏览次数添加1
    public void TieziViewUpdate(Tiezi tiezi);

    public List<Replytiezi> replyTieziSingleShow(Tiezi tiezi);

    /**
     * 发帖
     * @param tiezi
     * @return
     */
    public void fatie(Tiezi tiezi);

    //首先来测试ok后，然后再操作哈
    public void huitie(Replytiezi tiezi);

    public void deleteTiezi(int id);

    public void jiajing(int id);


    public void replytie(Replytiezi tiezi);
}