package com.huijian.tui.ui.fragments;

import com.huijian.tui.R;
import com.huijian.tui.ui.BaseFragment;
import com.xuexiang.xui.widget.toast.XToast;

public class MineFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {
        find(R.id.img_mine).setOnClickListener((view -> {
            XToast.normal(getContext(),"你好").show();
        }));
    }
}
