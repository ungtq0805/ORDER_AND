package ctwhs.order.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by HOCVIEN on 3/18/2017.
 */

public class FragmentMasterFoods extends Fragment implements View.OnClickListener{
    ActivityMasterFoodsBinding activityMasterFoodsBinding;
    Spinner productType;
    private IFragmentMasterEvent event;
    String mode;
    ArrayList<ProductTypeEnt> listProductType;
    int positionProducType;
    ProductEnt productEntModify;
    ProductEntDao productEntDao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activityMasterFoodsBinding = DataBindingUtil.inflate(inflater, R.layout.activity_master_foods, container, false);

        activityMasterFoodsBinding.btnRegOrUpdProduct.setOnClickListener(this);
        activityMasterFoodsBinding.btnDelProduct.setOnClickListener(this);

        event = (IFragmentMasterEvent)getActivity();

        try {
            addItemsToProductType();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        productType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionProducType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return activityMasterFoodsBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        productEntDao = new ProductEntDao(getActivity());
        setInitArgs();
    }

    private void addItemsToProductType() throws SQLException {
        productType = activityMasterFoodsBinding.productType;
        ProductTypeEntDao dao = new ProductTypeEntDao(getActivity());
        listProductType = (ArrayList<ProductTypeEnt>) dao.queryAll();

        ArrayAdapter<ProductTypeEnt> arrayAdapter = new ArrayAdapter<ProductTypeEnt>
            (getActivity(), R.layout.spinner_item , listProductType);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productType.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnRegOrUpdProduct){
            regOrUpdateProductEnt();
            event.onClickMasterMenuActivity(v.getId(),"",0,0);
        } else {
            confirmDelProduct();
        }
    }

    private void regOrUpdateProductEnt(){
        if(ConstActivity.CONST_MODE_NEW.equals(this.mode)){
            regProductEnt();
        } else {
            updProductEnt();
        }
    }

    /**
     * reg product
     */
    private void regProductEnt(){
        ProductEnt productEnt = new ProductEnt();
        productEnt.setName(ConvertUtils.toEmpty(activityMasterFoodsBinding.txtProductName.getText()));
        productEnt.setNameJp(ConvertUtils.toEmpty(activityMasterFoodsBinding.txtProductNamJp.getText()));

        if(!ConvertUtils.nullOrBlank(activityMasterFoodsBinding.txtPrice.getText().toString())){
            productEnt.setPrice(Float.parseFloat(ConvertUtils.toEmpty(activityMasterFoodsBinding.txtPrice.getText())));
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
     * reg product
     */
    private void updProductEnt(){
        productEntModify.setName(ConvertUtils.toEmpty(activityMasterFoodsBinding.txtProductName.getText()));
        productEntModify.setNameJp(ConvertUtils.toEmpty(activityMasterFoodsBinding.txtProductNamJp.getText()));
        productEntModify.setPrice(Float.parseFloat(ConvertUtils.toEmpty(activityMasterFoodsBinding.txtPrice.getText())));

        ProductTypeEnt productTypeEnt = listProductType.get(positionProducType);
        productEntModify.setType(productTypeEnt);
        try {
            productEntDao.updProduct(productEntModify);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * get Mode from Bundle
     */
    private void setInitArgs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mode = bundle.getString(ConstActivity.CONST_MODE_ARG);
        }

        if(!mode.equals(ConstActivity.CONST_MODE_MODIFY)){
            activityMasterFoodsBinding.txtProductName.setText(ConstActivity.BLANK);
            activityMasterFoodsBinding.txtProductNamJp.setText(ConstActivity.BLANK);
            activityMasterFoodsBinding.txtPrice.setText(ConstActivity.BLANK);
            productType.setSelection(0);
            activityMasterFoodsBinding.btnDelProduct.setVisibility(View.INVISIBLE);
            return;
        }

        String productId = bundle.getString("productId");
        ProductEntDao productEntDao = new ProductEntDao(getActivity());
        try {
            productEntModify = productEntDao.getProductEntById(Integer.parseInt(productId));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String groupPosition = bundle.getString("groupPosition");
        productType.setSelection(Integer.parseInt(groupPosition));

        activityMasterFoodsBinding.txtProductName.setText(productEntModify.getName());
        activityMasterFoodsBinding.txtProductNamJp.setText(productEntModify.getNameJp());
        activityMasterFoodsBinding.txtPrice.setText(ConvertUtils.toEmpty(productEntModify.getPrice()));

        activityMasterFoodsBinding.btnDelProduct.setVisibility(View.VISIBLE);
    }

    /**
     * confirm clear all order
     */
    private void confirmDelProduct(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.table_order_dialog_confirm_cancel_all_title);
        builder.setMessage(R.string.master_menu_product_confirm_del_product);

        builder.setPositiveButton(R.string.table_order_dialog_confirm_cancel_all_ok, new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Yes, let's do it", Toast.LENGTH_SHORT).show();
                try {
                    productEntDao.delProductEnt(productEntModify);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dialog.cancel();
                event.onClickMasterMenuActivity(activityMasterFoodsBinding.btnDelProduct.getId(),"",0,0);
            }

        });

        builder.setNegativeButton(R.string.table_order_dialog_confirm_cancel_all_cancel, new Dialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "No, not do it", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        builder.show();
    }
}
