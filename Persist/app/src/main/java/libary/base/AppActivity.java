package libary.base;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import libary.base.ActivityManager;

/**
 * Created by daxiong on 2016/7/22.
 */
public abstract class AppActivity  extends BaseActivity{
    //获取第一个fragment
    protected abstract BaseFragment getFirstFragment();

    //获取Intent
    protected void handleIntent(Intent intent) {

    }
    @Override
    protected int getContentViewId() {
        return 0;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        //避免重复添加Fragment
        if (null == getSupportFragmentManager().getFragments()) {
            BaseFragment firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment);
            }
        }

        ActivityManager.getInstance().addActivity(this);
    }

    //    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //ctivityManager.getInstance().finishActivity(this);
//        ActivityManager.getIn
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        libary.base.ActivityManager.getInstance().finishActivity(this);
    }
}
