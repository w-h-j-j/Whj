package com.huijian.tui.ui.fragments;

import android.content.Intent;
import android.os.Build;

import com.huijian.tui.R;
import com.huijian.tui.ui.BaseFragment;
import com.huijian.tui.util.LanguageUtil;
import com.huijian.tui.util.MMKVUtil;
import com.mingrisoft.basedialog.LottieAnimDialog;

import java.util.Locale;

public class HomeFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        find(R.id.btn_click).setOnClickListener((view -> {
            LottieAnimDialog dialog=new LottieAnimDialog(getContext());
            dialog.setMessage(getString(R.string.string_load_message));
            dialog.show();
        }));

    }

    private void changeMyLanguage(){
        Locale locale = Locale.ENGLISH;
        MMKVUtil.getInstance().setEncode("Language",locale.getLanguage());
        updateLanguage(locale);
    }

    private void updateLanguage(Locale locale) {
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.N){
            LanguageUtil.changeAppLanguage(getContext(),locale);
        }
        Intent intent = getActivity().getIntent();
        getActivity().overridePendingTransition(0,0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().finish();
        getContext().startActivity(intent);
    }

}
