package com.cdlixin.coc.ui.main.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdlixin.coc.R;
import com.cdlixin.coc.global.BaseFrament;
import com.cdlixin.coc.global.BasePresenter;
import com.cdlixin.coc.ui.user.activity.LoginActivity;
import com.cdlixin.coc.ui.user.widget.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FiveFragment extends BaseFrament {

    @Bind(R.id.btn_skipToLogin)
    Button btnSkipToLogin;
    @Bind(R.id.tv_textForLogin)
    TextView tvTextForLogin;
    @Bind(R.id.rel_skipToLogin)
    RelativeLayout relSkipToLogin;
    @Bind(R.id.img_userhead)
    CircleImageView imgUserhead;
    @Bind(R.id.tv_companyName)
    TextView tvCompanyName;
    @Bind(R.id.tv_userName)
    TextView tvUserName;
    @Bind(R.id.rel_center)
    RelativeLayout relCenter;
    @Bind(R.id.img_userInfo)
    ImageView imgUserInfo;
    @Bind(R.id.tv_userInfo)
    TextView tvUserInfo;
    @Bind(R.id.lin_userInfo)
    LinearLayout linUserInfo;
    @Bind(R.id.img_collectManage)
    ImageView imgCollectManage;
    @Bind(R.id.tv_collectManage)
    TextView tvCollectManage;
    @Bind(R.id.lin_collectManage)
    LinearLayout linCollectManage;
    @Bind(R.id.img_setting)
    ImageView imgSetting;
    @Bind(R.id.tv_setting)
    TextView tvSetting;
    @Bind(R.id.lin_setting)
    LinearLayout linSetting;
    @Bind(R.id.img_suggestion)
    ImageView imgSuggestion;
    @Bind(R.id.tv_suggestion)
    TextView tvSuggestion;
    @Bind(R.id.lin_suggestion)
    LinearLayout linSuggestion;
    @Bind(R.id.lin_agreement)
    LinearLayout linAgreement;

    @Override
    public int bindLayout() {
        return R.layout.fragment_five;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void setListener() {
        btnSkipToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, LoginActivity.class));
            }
        });
    }


    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
