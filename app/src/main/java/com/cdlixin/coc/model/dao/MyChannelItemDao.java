package com.cdlixin.coc.model.dao;

import com.cdlixin.coc.entity.ChannelItem;
import com.cdlixin.coc.model.daohelper.GreenDaoManger;
import com.cdlixin.coc.model.daohelper.IBaseDao;
import com.cdlixin.coc.model.greendaogen.ChannelItemDao;

import java.util.List;

/**
 * Created by 蒲弘宇 on 2017/9/28.
 */

public class MyChannelItemDao implements IBaseDao<ChannelItem> {

    private static MyChannelItemDao instance = null;
    private ChannelItemDao dao = null;

    private MyChannelItemDao(){
        dao = GreenDaoManger.getInstance().getDaosession().getChannelItemDao();
    }

    /**
     * 带同步的单例
     * @return
     */
    public static MyChannelItemDao getInstence(){
        if(instance == null){
            synchronized (MyChannelItemDao.class){
                if(instance == null){
                    instance = new MyChannelItemDao();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean insert(ChannelItem entity) {
        return dao.insert(entity) != -1;
    }


    @Override
    public boolean insert(final List<ChannelItem> channelItems) {
        dao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                dao.insertInTx(channelItems);
            }
        });
        return true;
    }

    @Override
    public boolean delete(ChannelItem entity) {
        dao.delete(entity);
        return true;
    }

    @Override
    public boolean delete(List<ChannelItem> channelItems) {
        dao.deleteInTx(channelItems);
        return true;
    }

    /**
     * 插入或修改单个
     * @param entity
     * @return
     */
    @Override
    public boolean insertOrReplace(ChannelItem entity) {
        return dao.insertOrReplace(entity) != -1;
    }

    /**
     * 插入或修改列表
     * @param channelItems
     * @return
     */
    @Override
    public boolean insertOrReplace(final List<ChannelItem> channelItems) {
        dao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                dao.insertOrReplaceInTx(channelItems);
            }
        });
        return true;
    }

    /**
     * 更新单个
     * @param entity
     */
    @Override
    public void update(ChannelItem entity) {
        dao.update(entity);
    }

    /**
     * 按id超找
     * @param id
     * @return
     */
    @Override
    public ChannelItem selectById(String id) {
        return dao.queryBuilder().where(ChannelItemDao.Properties.Id.eq(id)).unique();
    }

    /**
     * 加载所有
     * @return
     */
    @Override
    public List<ChannelItem> loadAll() {
        return dao.loadAll();
    }

    /**
     * 清空表
     * @return
     */
    @Override
    public boolean clear() {
        dao.deleteAll();
        return true;
    }

    /**
     * SQL语句条件查找
     * @param where
     * @param selectionArg
     * @return
     */
    @Override
    public List<ChannelItem> queryRaw(String where, String... selectionArg) {
        return null;
    }

    /**
     * 获取用户选定的频道列表
     * @return
     */
    public List<ChannelItem> getUserChannel(){
        return dao.queryBuilder().where(ChannelItemDao.Properties.Chose.eq(true)).orderAsc(ChannelItemDao.Properties.OrderId).list();
    }

    /**
     * 获取未选中频道列表
     * @return
     */
    public List<ChannelItem> getOtherChannel(){
        return dao.queryBuilder().where(ChannelItemDao.Properties.Chose.eq(false)).orderAsc(ChannelItemDao.Properties.OrderId).list();
    }
}
