package com.cdlixin.coc.model.dao;

import com.cdlixin.coc.entity.UserEntity;
import com.cdlixin.coc.model.daohelper.GreenDaoManger;
import com.cdlixin.coc.model.daohelper.IBaseDao;
import com.cdlixin.coc.model.greendaogen.UserEntityDao;

import java.util.List;

/**
 * Created by 蒲弘宇 on 2017/10/11.
 */

public class MyUserDao implements IBaseDao<UserEntity>{

    private static MyUserDao instance = null;

    public static MyUserDao getInstance(){
        if(instance == null){
            synchronized (MyNewsDao.class){
                if(instance == null){
                    instance = new MyUserDao();
                }
            }
        }
        return instance;
    }

    private UserEntityDao dao = null;

    public MyUserDao() {
        dao = GreenDaoManger.getInstance().getDaosession().getUserEntityDao();
    }

    @Override
    public boolean insert(UserEntity entity) {
        return dao.insert(entity) != -1;
    }

    @Override
    public boolean insert(final List<UserEntity> userEntities) {
        dao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                dao.insertInTx(userEntities);
            }
        });
        return true;
    }

    @Override
    public boolean delete(UserEntity entity) {
        dao.delete(entity);
        return true;
    }

    @Override
    public boolean delete(List<UserEntity> userEntities) {
        dao.deleteInTx(userEntities);
        return true;
    }

    @Override
    public boolean insertOrReplace(UserEntity entity) {
        return dao.insertOrReplace(entity) != -1;
    }

    @Override
    public boolean insertOrReplace(final List<UserEntity> userEntities) {
        dao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                dao.insertOrReplaceInTx(userEntities);
            }
        });
        return true;
    }

    @Override
    public void update(UserEntity entity) {
        dao.update(entity);
    }

    @Override
    public UserEntity selectById(String id) {
        return dao.queryBuilder().where(UserEntityDao.Properties.U_id.eq(id)).unique();
    }

    @Override
    public List<UserEntity> loadAll() {
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
    public List<UserEntity> queryRaw(String where, String... selectionArg) {
        return null;
    }

    public long getSize() {
        return dao.count();
    }
}
