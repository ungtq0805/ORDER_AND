package ctwhs.order.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ctwhs.common.utils.ConvertUtils;
import ctwhs.order.R;
import ctwhs.order.constant.ConstActivity;
import ctwhs.order.databinding.ActivityMasterMenuBinding;
import ctwhs.order.fragment.FragmentMasterFoods;
import ctwhs.order.fragment.FragmentMasterListFoods;
import ctwhs.order.fragment.IFragmentMasterEvent;
import ctwhs.order.unit.test.SqliteUnitTest;

public class MasterMenuActivity extends AppCompatActivity implements IFragmentMasterEvent {

    ActivityMasterMenuBinding masterMenuBinding;
    FragmentManager fragmentManager;
    FragmentMasterListFoods fragmentMasterListFoods;
    FragmentMasterFoods fragmentMasterFoods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        masterMenuBinding = DataBindingUtil.setContentView(this, R.layout.activity_master_menu);

        SqliteUnitTest unitTest = new SqliteUnitTest(this);
        unitTest.demoTranslateApiGoogle();
        unitTest.test1();


        fragmentManager = getSupportFragmentManager();

        //load table init
        loadMasterListFoodFrag();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * load product fragment
     */
    private void loadMasterFoodFragWithModeNew(){
        loadMasterFoodFragWithMode(-1, -1, ConstActivity.CONST_MODE_NEW);
    }

    /**
     * load product fragment
     */
    private void loadMasterListFoodFrag(){
        if(ConvertUtils.nullOrBlank(fragmentMasterListFoods)){
            fragmentMasterListFoods = new FragmentMasterListFoods();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        if(!fragmentMasterListFoods.isAdded()){
            fragmentTransaction.replace(R.id.masterMenuContentAreas, fragmentMasterListFoods);
//            fragmentTransaction.replace(R.id.masterMenuContentAreas, new FragmentMasterListFoods());
            fragmentTransaction.commit();
//        }
    }

    @Override
    public void onClickMasterMenuActivity(int layoutId, String mode, int productType, int productId) {
        if(layoutId == R.id.btnAddNewProduct){
            loadMasterFoodFragWithModeNew();
        } else  if(layoutId == R.id.btnRegOrUpdProduct || layoutId == R.id.btnDelProduct){
            loadMasterListFoodFrag();
        } else {
            loadMasterFoodFragWithMode(productType, productId, ConstActivity.CONST_MODE_MODIFY);
        }
    }

    /**
     * open master foods with mode
     * @param productId
     * @param mode
     */
    private void loadMasterFoodFragWithMode(int groupPosition, int productId, String mode){
        Bundle bundle;
        boolean isExist = false;

        if(ConvertUtils.nullOrBlank(fragmentMasterFoods)){
            fragmentMasterFoods = new FragmentMasterFoods();
            bundle = new Bundle();
        } else {
            bundle = fragmentMasterFoods.getArguments();
            isExist = true;
        }

        bundle.putString("productId", ConvertUtils.toEmpty(productId));
        bundle.putString("groupPosition", ConvertUtils.toEmpty(groupPosition));
        bundle.putString(ConstActivity.CONST_MODE_ARG, mode);

        if(!isExist){
            fragmentMasterFoods.setArguments(bundle);
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.masterMenuContentAreas, fragmentMasterFoods);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        fragmentManager.getFragments().remove(fragmentMasterListFoods);
        super.onBackPressed();
    }
}
