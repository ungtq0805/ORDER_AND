package ctwhs.order.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ctwhs.order.R;
import ctwhs.order.databinding.ActivityMainMenuBinding;

/**
 * Created by HOCVIEN on 3/18/2017.
 */

public class FragmentMainMenu extends Fragment implements View.OnClickListener {
    ActivityMainMenuBinding activityMainMenuBinding;
    FragmentManager fragmentManager;
    IEvent event;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activityMainMenuBinding = DataBindingUtil.inflate(inflater, R.layout.activity_main_menu, container, false);
        fragmentManager = getFragmentManager();

        activityMainMenuBinding.buttonFoods.setOnClickListener(this);
        activityMainMenuBinding.buttonTable.setOnClickListener(this);

        return activityMainMenuBinding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.buttonFoods){
            //load foods
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.resultAreas, new FragmentFoods());
            fragmentTransaction.commit();
        }

        if(view.getId() == R.id.buttonTable){
            //load table
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.resultAreas, new FragmentTables());
            fragmentTransaction.commit();
        }
    }
}
