package ctwhs.order.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ctwhs.order.R;
import ctwhs.order.databinding.ActivityMainMenuBinding;
import ctwhs.order.fragment.FragmentFoods;
import ctwhs.order.fragment.FragmentTables;

public class MainMenuActivity extends AppCompatActivity {

    ActivityMainMenuBinding menuBinding;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menuBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_menu);

        fragmentManager = getSupportFragmentManager();

        //load table init
        loadTablesFrag();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * on click button history
     * @param v
     */
    public void onClickHistory(View v){
        loadHistoryOrder();
    }

    /**
     * on click table button
     * @param v
     */
    public void onClickTables(View v){
        loadTablesFrag();
    }

    /**
     * open master menu
     * @param v
     */
    public void onClickMasterMenu(View v){
//        Intent intent = new Intent(this, MasterMenuActivity.class);
        Intent intent = new Intent(this, MasterFoodsListActivity.class);
        startActivity(intent);
    }

    /**
     * load table fragment
     */
    private void loadTablesFrag(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.resultAreas, new FragmentTables());
        fragmentTransaction.commit();
        menuBinding.buttonTable.setBackgroundColor(getResources().getColor(R.color.colorYellowButton));
        menuBinding.buttonFoods.setBackgroundColor(getResources().getColor(R.color.colorWhiteButton));
        menuBinding.buttonMasterMenu.setBackgroundColor(getResources().getColor(R.color.colorWhiteButton));
    }

    /**
     * load history Order
     */
    private void loadHistoryOrder(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.resultAreas, new FragmentFoods());
        fragmentTransaction.commit();
        menuBinding.buttonFoods.setBackgroundColor(getResources().getColor(R.color.colorYellowButton));
        menuBinding.buttonTable.setBackgroundColor(getResources().getColor(R.color.colorWhiteButton));
        menuBinding.buttonMasterMenu.setBackgroundColor(getResources().getColor(R.color.colorWhiteButton));
    }
}
