package ctwhs.order.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;

import java.sql.SQLException;
import java.util.List;

import ctwhs.order.R;
import ctwhs.order.adapter.MasterListFoodsAdapter;
import ctwhs.order.constant.ConstActivity;
import ctwhs.order.dao.ProductTypeEntDao;
import ctwhs.order.databinding.ActivityMasterListFoodsBinding;
import ctwhs.order.entity.ProductTypeEnt;
import ctwhs.order.unit.test.SqliteUnitTest;

public class MasterFoodsListActivity extends AppCompatActivity {

    ActivityMasterListFoodsBinding listFoodsBinding;

    //list product type
    List<ProductTypeEnt> listProductTypeHeader;

    //list adapter
    ExpandableListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SqliteUnitTest unitTest = new SqliteUnitTest(this);
        unitTest.demoTranslateApiGoogle();
//        unitTest.test1();

        listFoodsBinding = DataBindingUtil.setContentView(this, R.layout.activity_master_list_foods);

        ProductTypeEntDao dao = new ProductTypeEntDao(this);
        try {
            listProductTypeHeader = dao.queryAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        listAdapter = new MasterListFoodsAdapter(this, listProductTypeHeader);

        // setting list adapter
        listFoodsBinding.expListView.setAdapter(listAdapter);
    }

    public void addNewProduct(View view){
        Intent intent = new Intent(this, MasterFoodsDetailActivity.class);
        intent.putExtra(ConstActivity.CONST_DATA_TRANSFER_FOODS_DETAIL_BUNDLE, getBundleWithModeNew());
        startActivity(intent);
    }

    /**
     * set bundle with mode new and transfer to Product Detail
     * @return
     */
    private Bundle getBundleWithModeNew(){
        Bundle bundle = new Bundle();
        bundle.putString(ConstActivity.CONST_MODE_ARG, ConstActivity.CONST_MODE_NEW);
        return bundle;
    }
}
