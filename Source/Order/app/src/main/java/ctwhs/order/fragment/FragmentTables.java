package ctwhs.order.fragment;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

import ctwhs.order.R;
import ctwhs.order.activity.TableActivity;
import ctwhs.order.adapter.TableAdapter;
import ctwhs.order.data.DataProvider;
import ctwhs.order.databinding.ActivityTableBinding;
import ctwhs.order.dto.Table;

/**
 * Created by HOCVIEN on 3/18/2017.
 */

public class FragmentTables extends Fragment implements AdapterView.OnItemClickListener {
    ActivityTableBinding activityTableBinding;
    static boolean initFinished;
    int customersNo;
    ArrayList<Table> tables;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activityTableBinding = DataBindingUtil.inflate(inflater, R.layout.activity_table, container, false);

        loadTables();

        return activityTableBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        //activityTableBinding.gridView.invalidateViews();
        loadTables();
    }

    private void loadTables(){
        oneTimeInit();

//        //Check if it's a restricted tables activity
//        Bundle b = getIntent().getExtras();
//        if(b!=null)
//        {
//            customersNo = b.getInt("customersNO");
//            Log.d("CG","Main activity for no customers: "+customersNo);
//        }
//        else customersNo=0;

        //Get Tables
        tables=new ArrayList<Table>(DataProvider.getTables());

        //Prepare the grid
//        activityTableBinding.gridView = (GridView) findViewById(R.id.gridView);
        activityTableBinding.gridView.setAdapter(new TableAdapter(getActivity(), tables));

        activityTableBinding.gridView.setOnItemClickListener(this);
    }

    /**
     * One time init.
     */
    public static void oneTimeInit()
    {
        if(!initFinished)
        {
            initFinished=true;
            //Prepare the tables
            DataProvider.generateTables();
            DataProvider.generateProducts();
            DataProvider.generateProductCategories();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Table "+position+" selected.",Toast.LENGTH_SHORT).show();
        final Table table=tables.get(position);
        Log.d("CG","Clicked on table "+table);
        final Activity mactivity=getActivity();

//        //If the table is empty, select number of customers
//        if(table.empty)
//        {
//            //Generate numbers
//            final CharSequence[] customers= new CharSequence[table.maxClients];
//            for(int i=0;i<customers.length;i++)
//                customers[i]=Integer.toString(i+1);
//
//            //If the number of customers is already selected
//            if(customersNo>0)
//            {
//                table.empty=false;
//                table.curClients=customersNo;
//                table.tableOrder=new TableOrder();
//
//                Intent myIntent = new Intent(mactivity, TableActivity.class);
//                myIntent.putExtra("tableID", table.id);
//                mactivity.startActivity(myIntent);
//
//                mactivity.finish();
//            }
//            else
//            {
//                //Create the dialog
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("Number of customers");
//                builder.setItems(customers, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int item) {
//                        Toast.makeText(getApplicationContext(), customers[item] + " customers selected.", Toast.LENGTH_SHORT).show();
//                        table.empty=false;
//                        table.curClients=item+1;
//                        table.tableOrder=new TableOrder();
//
//                        Intent myIntent = new Intent(mactivity, TableActivity.class);
//                        myIntent.putExtra("tableID", table.id);
//                        mactivity.startActivity(myIntent);
//
//                    }
//                });
//
//                //Show the dialog
//                AlertDialog alert = builder.create();
//                alert.show();
//            }
//        }
//        //If the table is not empty, start the Table Activity
//        else {
            Intent myIntent = new Intent(getActivity(), TableActivity.class);
            myIntent.putExtra("tableID", table.id);
            this.startActivity(myIntent);

            //Kill the intermediate activity if it's a search type
            if(customersNo>0)
                getActivity().finish();
//        }
    }
}
