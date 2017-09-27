package com.cdlixin.coc.model.impl;

import com.cdlixin.coc.global.BaseFrament;
import com.cdlixin.coc.model.IFragment;
import com.cdlixin.coc.ui.main.fragment.FiveFragment;
import com.cdlixin.coc.ui.main.fragment.FourFragment;
import com.cdlixin.coc.ui.main.fragment.OneFragment;
import com.cdlixin.coc.ui.main.fragment.ThreeFragment;
import com.cdlixin.coc.ui.main.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蒲弘宇 on 2017/9/27.
 */

public class FragmentMaodel implements IFragment {
    private List<BaseFrament> framents = new ArrayList<>();

    @Override
    public List<BaseFrament> getFraments(){
        framents.add(new OneFragment());
        framents.add(new TwoFragment());
        framents.add(new ThreeFragment());
        framents.add(new FourFragment());
        framents.add(new FiveFragment());
        return framents;
    }
}
