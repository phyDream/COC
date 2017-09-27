package com.cdlixin.coc.presenter.main.impl;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.cdlixin.coc.R;
import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.global.BaseFrament;
import com.cdlixin.coc.global.BasePresenter;
import com.cdlixin.coc.model.impl.FragmentMaodel;
import com.cdlixin.coc.presenter.main.SwitchFragment;
import com.cdlixin.coc.ui.main.view.MainView;

import java.util.List;

/**
 * Created by 蒲弘宇 on 2017/9/26.
 */

public class MainActivityPresenter extends BasePresenter implements SwitchFragment {

    private MainView mainView;
    private FragmentMaodel fragmentMaodel;
    private List<BaseFrament> framents;
    private FragmentManager fragmentManager = null;

    public MainActivityPresenter(BaseActivity context) {
        super(context);
        mainView = (MainView) context;
        fragmentMaodel = new FragmentMaodel();
        framents = fragmentMaodel.getFraments();
    }

    //初始化主界面的各fragment
    public void initFragments(BaseActivity activity) {
        fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //添加所有
        for (BaseFrament f : framents) {
            transaction.add(R.id.replace,f);
        }
        transaction.commit();
        showFragment(0);
    }

    //展示不同的fragment
    @Override
    public void showFragment(int i) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //隐藏所有
        for (BaseFrament f : framents) {
            transaction.hide(f);
        }
        //展示当前选中
        transaction.show(framents.get(i));
        transaction.commit();
    }

}
