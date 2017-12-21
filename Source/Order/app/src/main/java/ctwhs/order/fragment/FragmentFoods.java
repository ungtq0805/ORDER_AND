package ctwhs.order.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ctwhs.order.R;
import ctwhs.order.databinding.ActivityFoodsBinding;

/**
 * Created by HOCVIEN on 3/18/2017.
 */

public class FragmentFoods extends Fragment {
    ActivityFoodsBinding activityFoodsBinding;
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activityFoodsBinding = DataBindingUtil.inflate(inflater, R.layout.activity_foods, container, false);
        return activityFoodsBinding.getRoot();
    }
}
