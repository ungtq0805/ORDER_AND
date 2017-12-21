package ctwhs.order.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

import ctwhs.common.utils.ConvertUtils;
import ctwhs.order.R;
import ctwhs.order.constant.ConstActivity;
import ctwhs.order.dao.ProductEntDao;
import ctwhs.order.dao.ProductTypeEntDao;
import ctwhs.order.databinding.ActivityMasterFoodsBinding;
import ctwhs.order.entity.ProductEnt;
import ctwhs.order.entity.ProductTypeEnt;

public class MasterFoodsDetailActivity extends AppCompatActivity {
    //get argument from another actiity
    Intent intent;
    Bundle bundle;

    //declare a entity with mode modify
    ProductEnt productEntModify;

    //declare dao
    ProductEntDao productEntDao;

    //declare a combobox
    Spinner productType;

    //declare a list product Type to show on combobox
    ArrayList<ProductTypeEnt> listProductType;

    //get position form combobox product Type
    int positionProducType;

    //mode
    String mode;

    ActivityMasterFoodsBinding masterFoodsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        masterFoodsBinding = DataBindingUtil.setContentView(this, R.layout.activity_master_foods);

        try {
            addItemsToProductType();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //get intent
        intent = getIntent();

        //get bundle
        bundle = intent.getBundleExtra(ConstActivity.CONST_DATA_TRANSFER_FOODS_DETAIL_BUNDLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        productEntDao = new ProductEntDao(this);

        //set items on the screen
        setItemsByMode();
    }

    /**
     * set items by mode new or mode modify
     */
    private void setItemsByMode(){
        this.mode = bundle.getString(ConstActivity.CONST_MODE_ARG);

        if(!mode.equals(ConstActivity.CONST_MODE_MODIFY)){
            masterFoodsBinding.txtProductName.setText(ConstActivity.BLANK);
            masterFoodsBinding.txtProductNamJp.setText(ConstActivity.BLANK);
            masterFoodsBinding.txtPrice.setText(ConstActivity.BLANK);
            masterFoodsBinding.productType.setSelection(0);
            masterFoodsBinding.btnDelProduct.setVisibility(View.INVISIBLE);
            return;
        }

        String productId = bundle.getString("productId");
        ProductEntDao productEntDao = new ProductEntDao(this);
        try {
            productEntModify = productEntDao.getProductEntById(Integer.parseInt(productId));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String groupPosition = bundle.getString("groupPosition");
        masterFoodsBinding.productType.setSelection(Integer.parseInt(groupPosition));

        masterFoodsBinding.txtProductName.setText(productEntModify.getName());
        masterFoodsBinding.txtProductNamJp.setText(productEntModify.getNameJp());
        masterFoodsBinding.txtPrice.setText(ConvertUtils.toEmpty(productEntModify.getPrice()));

        masterFoodsBinding.btnDelProduct.setVisibility(View.VISIBLE);
    }

    /**
     * init values and load item to combo
     * @throws SQLException
     */
    private void addItemsToProductType() throws SQLException {
        productType = masterFoodsBinding.productType;
        ProductTypeEntDao dao = new ProductTypeEntDao(this);
        listProductType = (ArrayList<ProductTypeEnt>) dao.queryAll();

        ArrayAdapter<ProductTypeEnt> arrayAdapter = new ArrayAdapter<ProductTypeEnt>
                (this, R.layout.spinner_item , listProductType);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productType.setAdapter(arrayAdapter);

        productType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionProducType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * event with delete button
     * @param view
     */
    public void delProduct(View view){
        confirmDelProduct();
    }

    /**
     * confirm clear all order
     */
    private void confirmDelProduct(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.table_order_dialog_confirm_cancel_all_title);
        builder.setMessage(R.string.master_menu_product_confirm_del_product);

        builder.setPositiveButton(R.string.table_order_dialog_confirm_cancel_all_ok, new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Yes, let's do it", Toast.LENGTH_SHORT).show();
                try {
                    productEntDao.delProductEnt(productEntModify);
                    finish();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dialog.cancel();
            }

        });

        builder.setNegativeButton(R.string.table_order_dialog_confirm_cancel_all_cancel, new Dialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "No, not do it", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        builder.show();
    }

    /**
     * event reg button
     * @param view
     */
    public void regProduct(View view){
        regOrUpdateProductEnt();
    }

    /**
     * reg product entity or update entity
     */
    private void regOrUpdateProductEnt(){
        if(ConstActivity.CONST_MODE_NEW.equals(this.mode)){
            regProductEnt();
        } else {
            updProductEnt();
        }
        finish();
    }

    /**
     * reg product
     */
    private void regProductEnt(){
        ProductEnt productEnt = new ProductEnt();
        productEnt.setName(ConvertUtils.toEmpty(masterFoodsBinding.txtProductName.getText()));
        productEnt.setNameJp(ConvertUtils.toEmpty(masterFoodsBinding.txtProductNamJp.getText()));

        if(!ConvertUtils.nullOrBlank(masterFoodsBinding.txtPrice.getText().toString())){
            productEnt.setPrice(Float.parseFloat(ConvertUtils.toEmpty(masterFoodsBinding.txtPrice.getText())));
        }

        ProductTypeEnt productTypeEnt = listProductType.get(positionProducType);
        productEnt.setType(productTypeEnt);
        try {
            productEntDao.addProduct(productEnt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * update product
     */
    private void updProductEnt(){
        productEntModify.setName(ConvertUtils.toEmpty(masterFoodsBinding.txtProductName.getText()));
        productEntModify.setNameJp(ConvertUtils.toEmpty(masterFoodsBinding.txtProductNamJp.getText()));
        productEntModify.setPrice(Float.parseFloat(ConvertUtils.toEmpty(masterFoodsBinding.txtPrice.getText())));

        ProductTypeEnt productTypeEnt = listProductType.get(positionProducType);
        productEntModify.setType(productTypeEnt);
        try {
            productEntDao.updProduct(productEntModify);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
