package com.zy.service.impl;

import com.zy.dao.TieziMapper;
import com.zy.dao.UsersMapper;
import com.zy.entity.Replytiezi;
import com.zy.entity.Tiezi;
import com.zy.entity.Users;
import com.zy.entity.vo.TieziVo;
import com.zy.untils.PageInfoUtils;
import com.zy.untils.SqlPageUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TieziServiceImpl {

    @Autowired
    TieziMapper tieziDao;

    @Autowired
    UsersMapper usersDao;

    private static Logger logger=Logger.getLogger(TieziServiceImpl.class);//获取Logger实例

    public TieziVo allTie(PageInfoUtils pageInfoUtils, String currentPage, Integer status) {

        TieziVo tv = new TieziVo();

        String sql = "";
        if (status != null) {
            sql = "select t1.*,t2.uname from tiezi t1 left join users t2 on t1.uid = t2.uid where status = " + status + " " +
                    "order" +
                    " by t1.tdate desc";
        } else {
            sql = "select t1.*,t2.uname from tiezi t1 left join users t2 on t1.uid = t2.uid order" +
                    " by t1.tdate desc";
        }

        //总条数
        long totalNum = tieziDao.tieziTotal(sql).size();

        //总页数
        long totalPage = SqlPageUtil.pageCount(pageInfoUtils.getPageSize(), totalNum);
        pageInfoUtils.setPageCount(totalPage);
        pageInfoUtils.setCurrentPage(Integer.parseInt(currentPage));

        //分页sql
        String pageSql = SqlPageUtil.pageSql(SqlPageUtil.Dialect.MySql, sql, pageInfoUtils.getPageSize(), Integer.parseInt(currentPage));
        List<Tiezi> tiezis = tieziDao.TieziShowPage(pageSql);
        pageInfoUtils.setData(tiezis);

        tv.setPageInfo(pageInfoUtils);

        //普通帖子
        tv.setPt(tiezis);
        //热帖不需要分页
        tv.setHot(tieziDao.hottie());
        return tv;
    }

    public TieziVo allTie(PageInfoUtils pageInfoUtils, String currentPage, Integer status,String title) {

        TieziVo tv = new TieziVo();

        //查询状态
        String sql = "";
        if (status != null) {
            //根据状态查询
            sql =
                    "select t1.*,t2.uname from tiezi t1 left join users t2 on t1.uid = t2.uid where status = " + status + " and title like '%"+title+"%' " +
                    "order" +
                    " by t1.tdate desc";
        } else {
            //查询全部
            sql = "select t1.*,t2.uname from tiezi t1 left join users t2 on t1.uid = t2.uid order" +
                    " by t1.tdate desc";
        }

        //总条数
        long totalNum = tieziDao.tieziTotal(sql).size();

        //总页数
        long totalPage = SqlPageUtil.pageCount(pageInfoUtils.getPageSize(), totalNum);
        pageInfoUtils.setPageCount(totalPage);
        pageInfoUtils.setCurrentPage(Integer.parseInt(currentPage));

        //分页sql
        String pageSql = SqlPageUtil.pageSql(SqlPageUtil.Dialect.MySql, sql, pageInfoUtils.getPageSize(), Integer.parseInt(currentPage));
        List<Tiezi> tiezis = tieziDao.TieziShowPage(pageSql);
        pageInfoUtils.setData(tiezis);

        tv.setPageInfo(pageInfoUtils);

        //普通帖子
        tv.setPt(tiezis);
        //热帖不需要分页
        tv.setHot(tieziDao.hottie());
        return tv;
    }

    /**
     * 查询所有帖子
     *
     * @return
     */
    public List<Tiezi> tieziShow() {
        List<Tiezi> tiezis = tieziDao.TieziShow();
        return tiezis;
    }

    public List<Tiezi> hottie() {
        return tieziDao.hottie();
    }

    /**
     * 查询单个帖子
     * 这个测试了缓存，并且也测试了事务
     * 注意而且有的时候磁盘中看不到临时文件，是因为保存到了内存中的
     * @param tiezi
     * @return
     */
    @Transactional
    @Cacheable(value="myCache", key="'get'+#tiezi.tid")
    public List<Tiezi> TieziSingleShow(Tiezi tiezi) {
        //测试日志打印
        /*logger.debug("debugxxxxx");
        logger.info("infoxxxxxx");
        logger.warn("warnxxxx");*/
        //System.out.println("检查cache是否生效");
        //根据id获取的帖子肯定只有一个
        List<Tiezi> result = tieziDao.TieziSingleShow(tiezi);
        //更新浏览次数
        tieziDao.TieziViewUpdate(tiezi);

        if (result != null && result.size() > 0) {

            Tiezi tiezi1 = result.get(0);
            List<Users> users = usersDao.editUser(tiezi1.getUid());
            //设置用户名称
            tiezi1.setUname(users.get(0).getUname());

            //获取帖子的回帖信息
            tiezi1.setReplytiezis(tieziDao.replyTieziSingleShow(tiezi));
        }
        return result;
    }

    /**
     * 发帖
     *
     * @param tiezi
     * @return
     */
    public void fatie(Tiezi tiezi) {
        tieziDao.fatie(tiezi);
    }

    public void huitie(Replytiezi tiezi) {
        tieziDao.huitie(tiezi);
    }

    /**
     * 删帖
     *
     * @param id
     */
    public void deleteTiezi(int id) {
        tieziDao.deleteTiezi(id);
    }

    public void jiajing(int id) {
        tieziDao.jiajing(id);
    }

    public void replytie(Replytiezi tiezi) {
        tieziDao.replytie(tiezi);
    }
}
