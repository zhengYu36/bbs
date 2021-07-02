package com.zy.dao;

import com.zy.entity.Replytiezi;

public interface ReplytieziMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Replytiezi record);

    int insertSelective(Replytiezi record);

    Replytiezi selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Replytiezi record);

    int updateByPrimaryKey(Replytiezi record);
}