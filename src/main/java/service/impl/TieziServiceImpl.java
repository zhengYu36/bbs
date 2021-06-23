package service.impl;

import dao.TieziDao;
import dao.impl.TieziDaoImpl;
import entity.Replytiezi;
import entity.Tiezi;
import service.TieziService;

import javax.rmi.CORBA.Tie;
import java.util.List;

public class TieziServiceImpl implements TieziService {
    TieziDao tieziDao = new TieziDaoImpl();

    /**
     * 查询所有帖子
     * @return
     */
    @Override
    public List<Tiezi> tieziShow() {
        return tieziDao.TieziShow();
    }

    /**
     * 查询单个帖子
     * @param tiezi
     * @return
     */
    @Override
    public List<Tiezi> TieziSingleShow(Tiezi tiezi) {
        List<Tiezi> result = tieziDao.TieziSingleShow(tiezi);
        if(result != null && result.size() >0){
            //获取帖子的回帖信息
            result.get(0).setReplytiezis(tieziDao.replyTieziSingleShow(tiezi));
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
