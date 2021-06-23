package dao;

import entity.Replytiezi;
import entity.Tiezi;

import java.util.List;

public interface TieziDao {
    /**
     * 查询全部帖子
     * @return
     */
    public List<Tiezi> TieziShow();

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
