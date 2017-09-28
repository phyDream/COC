package com.cdlixin.coc.model.greendaogen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.cdlixin.coc.entity.ChannelItem;

import com.cdlixin.coc.model.greendaogen.ChannelItemDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig channelItemDaoConfig;

    private final ChannelItemDao channelItemDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        channelItemDaoConfig = daoConfigMap.get(ChannelItemDao.class).clone();
        channelItemDaoConfig.initIdentityScope(type);

        channelItemDao = new ChannelItemDao(channelItemDaoConfig, this);

        registerDao(ChannelItem.class, channelItemDao);
    }
    
    public void clear() {
        channelItemDaoConfig.clearIdentityScope();
    }

    public ChannelItemDao getChannelItemDao() {
        return channelItemDao;
    }

}
