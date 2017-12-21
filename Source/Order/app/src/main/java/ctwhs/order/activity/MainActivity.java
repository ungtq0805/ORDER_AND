package ctwhs.order.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ctwhs.order.R;
import ctwhs.order.common.activity.ActivityHelper;
import ctwhs.order.constant.ConstActivity;
import ctwhs.order.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {//implements IEvent {

    ActivityMainBinding activityMainBinding;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        fragmentManager = getSupportFragmentManager();
//
//        //load form login
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.linearLayoutMain, new FragmentLogin());
//        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    public void openFormLogin(View view){
        //call form login
        Intent intent = new Intent(this, LoginActivity.class);

        //pass parameter
        intent.putExtra(ConstActivity.CONST_ACTIVITY_LANG, ActivityHelper.getBundleLang());

        //init form login
        startActivity(intent);

    }

    public void onClickVn(View view){
        ActivityHelper.setViewLanguages(ConstActivity.CONST_ACTIVITY_LANG_VN, this, R.layout.activity_main);
    }

    public void onClickJp(View view){
        ActivityHelper.setViewLanguages(ConstActivity.CONST_ACTIVITY_LANG_JP, this, R.layout.activity_main);
    }

//    @Override
//    public void onClick(int layoutId) {
//        Toast.makeText(getApplication(), "layoutId = " + layoutId, Toast.LENGTH_SHORT).show();
//        if(layoutId == R.id.btnLogin){
//            //load form login
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.linearLayoutMain, new FragmentMainMenu());
//            fragmentTransaction.commit();
//        }
//    }
}
