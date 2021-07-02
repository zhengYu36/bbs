package com.zy.service;

import com.zy.entity.Replytiezi;
import com.zy.entity.Tiezi;
import com.zy.entity.vo.TieziVo;
import com.zy.untils.PageInfoUtils;

import java.util.List;

public interface TieziService {

    public TieziVo allTie(PageInfoUtils pageInfoUtils,String currentPage, Integer status);

    /**
     * 帖子查询展示
     * @return
     */
    public List<Tiezi> tieziShow();


    //热帖
    public List<Tiezi> hottie();
    /**
     * 帖子单独展示页
     * @return
     */
    public List<Tiezi> TieziSingleShow(Tiezi tiezi);

    /**
     * 发帖
     * @param tiezi
     * @return
     */
    public void fatie(Tiezi tiezi);

    public void deleteTiezi(int id);

    /**
     * 回帖
     * @param tiezi
     */
    public void replytie(Replytiezi tiezi);

    public void jiajing(int id);

    public void huitie(Replytiezi tiezi);
}
