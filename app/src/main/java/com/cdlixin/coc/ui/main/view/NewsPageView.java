package com.cdlixin.coc.ui.main.view;

import com.cdlixin.coc.entity.ChannelItem;

import java.util.List;

/**
 * Created by 蒲弘宇 on 2017/9/28.
 */

public interface NewsPageView {

    void showChannel(List<ChannelItem> channelItems);
    void showToast(String string);
}
