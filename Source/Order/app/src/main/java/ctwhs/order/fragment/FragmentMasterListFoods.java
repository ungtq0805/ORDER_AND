package ctwhs.order.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.sql.SQLException;
import java.util.List;

import ctwhs.order.R;
import ctwhs.order.dao.ProductTypeEntDao;
import ctwhs.order.databinding.ActivityMasterListFoodsBinding;
import ctwhs.order.entity.ProductTypeEnt;

/**
 * Created by HOCVIEN on 3/18/2017.
 */

public class FragmentMasterListFoods extends Fragment implements View.OnClickListener{
    ActivityMasterListFoodsBinding activityMasterListFoodsBinding;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<ProductTypeEnt> listProductTypeHeader;
    private IFragmentMasterEvent event;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activityMasterListFoodsBinding = DataBindingUtil.inflate(inflater, R.layout.activity_master_list_foods, container, false);

        event = (IFragmentMasterEvent)getActivity();

        // get the listview
        expListView = (ExpandableListView) activityMasterListFoodsBinding.expListView;

//        // preparing list data
//        prepareListData();
        ProductTypeEntDao dao = new ProductTypeEntDao(getActivity());
        try {
            listProductTypeHeader = dao.queryAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        listAdapter = new MasterListFoodsAdapter(getActivity(), listProductTypeHeader, event);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        activityMasterListFoodsBinding.btnAddNewProduct.setOnClickListener(this);

        return activityMasterListFoodsBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        event.onClickMasterMenuActivity(v.getId(), "new", 0, 0);
    }
}
