package com.zy.service.impl;

import com.zy.dao.TieziDao;
import com.zy.dao.UsersDao;
import com.zy.dao.impl.TieziDaoImpl;
import com.zy.dao.impl.UsersDaoImpl;
import com.zy.entity.Replytiezi;
import com.zy.entity.Tiezi;
import com.zy.entity.Users;
import com.zy.entity.vo.TieziVo;
import com.zy.service.TieziService;
import com.zy.untils.PageInfoUtils;
import com.zy.untils.SqlPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.rmi.CORBA.Tie;
import java.util.List;

@Service
public class TieziServiceImpl implements TieziService {

    @Autowired
    TieziDao tieziDao;

    @Autowired
    UsersDao usersDao;

    @Override
    public TieziVo allTie(String currentPage) {

        TieziVo tv = new TieziVo();

        //普通帖子需要进行分页
        String sql = "select t1.*,t2.uname from tiezi t1 left join users t2 on t1.uid = t2.uid where status = 0 order" +
                " by t1.tdate desc";
        //总条数
        //String totalNumSql = SqlPageUtil.countSql(sql);
        long totalNum = tieziDao.tieziTotal(sql);

        //分页数据
        PageInfoUtils pageInfoUtils = new PageInfoUtils();

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
        tv.setHot(tieziDao.hottie());
        return tv;
    }

    /**
     * 查询所有帖子
     * @return
     */
    @Override
    public List<Tiezi> tieziShow() {
        List<Tiezi> tiezis = tieziDao.TieziShow();
        return tiezis;
    }

    @Override
    public List<Tiezi> hottie() {
        return tieziDao.hottie();
    }

    /**
     * 查询单个帖子
     * @param tiezi
     * @return
     */
    @Override
    public List<Tiezi> TieziSingleShow(Tiezi tiezi) {
        //根据id获取的帖子肯定只有一个
        List<Tiezi> result = tieziDao.TieziSingleShow(tiezi);
        if(result != null && result.size() >0){

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
     * @param tiezi
     * @return
     */
    @Override
    public void fatie(Tiezi tiezi) {
        tieziDao.fatie(tiezi);
    }

    @Override
    public void huitie(Replytiezi tiezi) {
        tieziDao.huitie(tiezi);
    }

    /**
     * 删帖
     * @param id
     */
    @Override
    public void deleteTiezi(int id) {
        tieziDao.deleteTiezi(id);
    }

    @Override
    public void jiajing(int id) {
        tieziDao.jiajing(id);
    }

    @Override
    public void replytie(Replytiezi tiezi) {
        tieziDao.replytie(tiezi);
    }
}
