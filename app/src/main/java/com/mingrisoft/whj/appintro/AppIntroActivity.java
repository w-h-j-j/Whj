package com.mingrisoft.whj.appintro;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;
import com.mingrisoft.whj.MainActivity;
import com.mingrisoft.whj.R;
import com.mingrisoft.whj.db.mmkv.MMKVUtil;

public class AppIntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("Welcome!",
                "This is a demo example in java of AppIntro library, with a custom background on each slide!",
                R.drawable.ic_add_oil));

        addSlide(AppIntroFragment.newInstance(
                "Clean App Intros",
                "This library offers developers the ability to add clean app intros at the start of their apps.",
                R.drawable.ic_launcher_background));
    }

    //当执行跳过动作时触发
    @Override
    protected void onSkipPressed(@Nullable Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(AppIntroActivity.this, MainActivity.class);
        startActivity(intent);
        MMKVUtil.getInstance().setEncode("isFirstInto",true);
        finish();
    }

    //当执行完成动作时触发
    @Override
    protected void onDonePressed(@Nullable Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(AppIntroActivity.this, MainActivity.class);
        startActivity(intent);
        MMKVUtil.getInstance().setEncode("isFirstInto",true);
        finish();
    }

    //当执行下一步动作时触发
    @Override
    protected void onNextPressed(@Nullable Fragment currentFragment) {
        super.onNextPressed(currentFragment);
    }

    //当执行变化动作时触发
    @Override
    protected void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }
}
