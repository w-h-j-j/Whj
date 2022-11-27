package com.huijian.tui.ui.fragments;

import com.huijian.tui.R;
import com.huijian.tui.ui.BaseFragment;

public class MineFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {
        find(R.id.btn).setOnClickListener((view -> { }));
    }
}
