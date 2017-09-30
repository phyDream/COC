package com.cdlixin.coc.model.dao;

import com.cdlixin.coc.entity.NewsEntity;
import com.cdlixin.coc.model.daohelper.DataBaseManager;
import com.cdlixin.coc.model.daohelper.GreenDaoManger;
import com.cdlixin.coc.model.daohelper.IBaseDao;
import com.cdlixin.coc.model.greendaogen.NewsEntityDao;

import java.util.List;

/**
 * Created by 蒲弘宇 on 2017/9/30.
 */

public class MyNewsDao implements IBaseDao<NewsEntity>{

    private static MyNewsDao instance = null;

    public static MyNewsDao getInstance(){
        if(instance == null){
            synchronized (MyNewsDao.class){
                if(instance == null){
                    instance = new MyNewsDao();
                }
            }
        }
        return instance;
    }

    private NewsEntityDao dao = null;

    public MyNewsDao() {
        dao = GreenDaoManger.getInstance().getDaosession().getNewsEntityDao();
    }

    @Override
    public boolean insert(NewsEntity entity) {
        return dao.insert(entity) != -1;
    }

    @Override
    public boolean insert(final List<NewsEntity> newsEntities) {
        dao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                dao.insertInTx(newsEntities);
            }
        });
        return true;
    }

    @Override
    public boolean delete(NewsEntity entity) {
        dao.delete(entity);
        return true;
    }

    @Override
    public boolean delete(List<NewsEntity> newsEntities) {
        dao.deleteInTx(newsEntities);
        return true;
    }

    @Override
    public boolean insertOrReplace(NewsEntity entity) {
        return dao.insertOrReplace(entity) != -1;
    }

    @Override
    public boolean insertOrReplace(final List<NewsEntity> newsEntities) {
        dao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                dao.insertOrReplaceInTx(newsEntities);
            }
        });
        return true;
    }

    @Override
    public void update(NewsEntity entity) {
        dao.update(entity);
    }

    @Override
    public NewsEntity selectById(String id) {
        return dao.queryBuilder().where(NewsEntityDao.Properties.Info_id.eq(id)).unique();
    }

    @Override
    public List<NewsEntity> loadAll() {
        return dao.loadAll();
    }

    @Override
    public boolean clear() {
        dao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                dao.deleteAll();
            }
        });
        return true;
    }

    @Override
    public List<NewsEntity> queryRaw(String where, String... selectionArg) {
        return null;
    }
}
