package com.cdlixin.coc.model.dao;

import com.cdlixin.coc.entity.OrgEntity;
import com.cdlixin.coc.model.daohelper.GreenDaoManger;
import com.cdlixin.coc.model.daohelper.IBaseDao;
import com.cdlixin.coc.model.greendaogen.OrgEntityDao;

import java.util.List;

/**
 * Created by 蒲弘宇 on 2017/10/11.
 */

public class MyOrgdao implements IBaseDao<OrgEntity>{
    private static MyOrgdao instance = null;

    public static MyOrgdao getInstance(){
        if(instance == null){
            synchronized (MyNewsDao.class){
                if(instance == null){
                    instance = new MyOrgdao();
                }
            }
        }
        return instance;
    }

    private OrgEntityDao dao = null;

    public MyOrgdao() {
        dao = GreenDaoManger.getInstance().getDaosession().getOrgEntityDao();
    }

    @Override
    public boolean insert(OrgEntity entity) {
        return dao.insert(entity) != -1;
    }

    @Override
    public boolean insert(final List<OrgEntity> orgEntities) {
        dao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                dao.insertInTx(orgEntities);
            }
        });
        return true;
    }

    @Override
    public boolean delete(OrgEntity entity) {
        dao.delete(entity);
        return true;
    }

    @Override
    public boolean delete(List<OrgEntity> orgEntities) {
        dao.deleteInTx(orgEntities);
        return true;
    }

    @Override
    public boolean insertOrReplace(OrgEntity entity) {
        return dao.insertOrReplace(entity) != -1;
    }

    @Override
    public boolean insertOrReplace(final List<OrgEntity> orgEntities) {
        dao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                dao.insertOrReplaceInTx(orgEntities);
            }
        });
        return true;
    }

    @Override
    public void update(OrgEntity entity) {
        dao.update(entity);
    }

    @Override
    public OrgEntity selectById(String id) {
        return dao.queryBuilder().where(OrgEntityDao.Properties.C_id.eq(id)).unique();
    }

    @Override
    public List<OrgEntity> loadAll() {
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
    public List<OrgEntity> queryRaw(String where, String... selectionArg) {
        return null;
    }

    public long getSize() {
        return dao.count();
    }
}
