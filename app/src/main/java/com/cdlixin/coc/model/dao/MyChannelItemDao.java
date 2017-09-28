package com.cdlixin.coc.model.dao;

import com.cdlixin.coc.entity.ChannelItem;

import java.util.List;

/**
 * Created by 蒲弘宇 on 2017/9/28.
 */

public class MyChannelItemDao implements IBaseDao<ChannelItem>{
    @Override
    public boolean insert(ChannelItem entity) {
        return false;
    }

    @Override
    public boolean insert(List<ChannelItem> channelItems) {
        return false;
    }

    @Override
    public boolean delete(ChannelItem entity) {
        return false;
    }

    @Override
    public boolean delete(List<ChannelItem> channelItems) {
        return false;
    }

    @Override
    public boolean insertOrReplace(ChannelItem entity) {
        return false;
    }

    @Override
    public boolean insertOrReplace(List<ChannelItem> channelItems) {
        return false;
    }

    @Override
    public void update(ChannelItem entity) {

    }

    @Override
    public ChannelItem selectById(String id) {
        return null;
    }

    @Override
    public List<ChannelItem> loadAll() {
        return null;
    }

    @Override
    public boolean clear() {
        return false;
    }

    @Override
    public List<ChannelItem> queryRaw(String where, String... selectionArg) {
        return null;
    }
}
