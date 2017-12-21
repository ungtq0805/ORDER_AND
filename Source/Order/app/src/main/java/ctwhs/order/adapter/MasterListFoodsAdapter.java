package ctwhs.order.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ctwhs.common.utils.ConvertUtils;
import ctwhs.order.R;
import ctwhs.order.activity.MasterFoodsDetailActivity;
import ctwhs.order.constant.ConstActivity;
import ctwhs.order.dao.ProductEntDao;
import ctwhs.order.entity.ProductEnt;
import ctwhs.order.entity.ProductTypeEnt;

/**
 * Created by PC14-02 on 3/14/2017.
 */

public class MasterListFoodsAdapter extends BaseExpandableListAdapter {
    private Context _context;
//    private List<String> _listDataHeader; // header titles
    private List<ProductTypeEnt> _listDataHeader; // header titles
//    IFragmentMasterEvent event;

//    // child data in format of header title, child title
//    private HashMap<String, List<String>> _listDataChild;

    public MasterListFoodsAdapter(Context context, List<ProductTypeEnt> listDataHeader/*,
                                  IFragmentMasterEvent event*/){
//            ,HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
//        this.event = event;
//        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
//        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
//                .get(childPosititon);
        Iterator<ProductEnt> iterator = _listDataHeader.get(groupPosition).getProducts().iterator();

        List<ProductEnt> lstProductEnt = new ArrayList<ProductEnt>() ;
        while (iterator.hasNext()){
            lstProductEnt.add(iterator.next());
        }

        return lstProductEnt.get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = getChild(groupPosition, childPosition).toString(); //(String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.activity_master_list_item_product, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);

        final ProductEnt productEnt = (ProductEnt)getChild(groupPosition, childPosition);
        txtListChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(_context, v);

                final MenuInflater inflater = popupMenu.getMenuInflater();

                inflater.inflate(R.menu.master_list_food_menu_select_product, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.masterFoodsMenuEdit:
                                //  event.onClickMasterMenuActivity(v.getId(), "modify", groupPosition, productEnt.getId());
                                editProduct(groupPosition, productEnt.getId());
                                break;
                            case R.id.masterFoodsMenuDelete:
                                confirmDelProduct(productEnt);
                                break;
                        }
                        return true;
                    }
                });

                popupMenu.show();

                Toast.makeText(_context,
                        "groupPosition: " + groupPosition + ", childPosition: " + childPosition,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Iterator<ProductEnt> iterator = _listDataHeader.get(groupPosition).getProducts().iterator();
        List<ProductEnt> lstProductEnt = new ArrayList<ProductEnt>() ;
        while (iterator.hasNext()){
            lstProductEnt.add(iterator.next());
        }

//        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
//                .size();
        return lstProductEnt.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = getGroup(groupPosition).toString();//(String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.activity_master_list_header, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private void editProduct(int groupPosition, int productId){
        Intent intent = new Intent(_context, MasterFoodsDetailActivity.class);
        intent.putExtra(ConstActivity.CONST_DATA_TRANSFER_FOODS_DETAIL_BUNDLE, getBundleWithModeModify(groupPosition, productId));
        _context.startActivity(intent);
    }

    /**
     * set bundle with mode new and transfer to Product Detail
     * @return
     */
    private Bundle getBundleWithModeModify(int groupPosition, int productId){
        Bundle bundle = new Bundle();
        bundle.putString(ConstActivity.CONST_MODE_ARG, ConstActivity.CONST_MODE_MODIFY);
        bundle.putString("productId", ConvertUtils.toEmpty(productId));
        bundle.putString("groupPosition", ConvertUtils.toEmpty(groupPosition));
        return bundle;
    }

    /**
     * confirm clear all order
     */
    private void confirmDelProduct(final ProductEnt productEntModify){
        final ProductEntDao productEntDao = new ProductEntDao(_context);
        AlertDialog.Builder builder = new AlertDialog.Builder(_context);
        builder.setTitle(R.string.table_order_dialog_confirm_cancel_all_title);
        builder.setMessage(R.string.master_menu_product_confirm_del_product);

        builder.setPositiveButton(R.string.table_order_dialog_confirm_cancel_all_ok, new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(_context, "Yes, let's do it", Toast.LENGTH_SHORT).show();
                try {
                    productEntDao.delProductEnt(productEntModify);
                    notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dialog.cancel();
            }

        });

        builder.setNegativeButton(R.string.table_order_dialog_confirm_cancel_all_cancel, new Dialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(_context, "No, not do it", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        builder.show();
    }
}
