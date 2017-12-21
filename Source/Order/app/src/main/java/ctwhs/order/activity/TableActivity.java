/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ctwhs.order.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import ctwhs.order.R;
import ctwhs.order.adapter.TableOrderAdapter;
import ctwhs.order.data.DataProvider;
import ctwhs.order.dto.Order;
import ctwhs.order.dto.Table;
import ctwhs.order.dto.TableOrder;


/**
 * The Class MainActivity.
 */
public class TableActivity extends Activity implements OnClickListener, OnItemClickListener {
	
	/** The table. */
	Table table;
	
	/** The table order. */
	TableOrder tableOrder;
	
	/** The listview. */
	ListView listview;
	
	/** The button add. */
	Button buttonAdd;
	
	/** The button finalize. */
	Button buttonFinalize;

	/** The button Payment. */
	Button buttonPayment;
	
    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState the saved instance state
     */
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_table_order);
	
	    //Get the table
	    Bundle b = getIntent().getExtras();
	    table = DataProvider.getTable(b.getInt("tableID"));
	    Log.d("CG","Table activity for "+table);
	    
	    //Set table order
	    if(table.tableOrder==null)
	    	table.tableOrder=new TableOrder();
	    tableOrder=table.tableOrder;
	    
	    //Set data
	    listview = (ListView) findViewById(R.id.tableListView);
	    listview.setAdapter(new TableOrderAdapter(this, tableOrder));
	    listview.setOnItemClickListener(this);
	    
	    buttonAdd =(Button) findViewById(R.id.orderAddButton);
	    buttonAdd.setOnClickListener(this);
	    
	    buttonFinalize =(Button) findViewById(R.id.tableOrderFinalizaButton);
	    buttonFinalize.setOnClickListener(this);

		buttonPayment =(Button) findViewById(R.id.btnPayment);
		buttonPayment.setOnClickListener(this);

	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	protected void onStart()
	{
		super.onStart();
		
	    TextView text= (TextView) findViewById(R.id.tableTitle);
	    text.setText(getResources().getString(R.string.txt_view_table_order) + " "+ table.id);
	    
	    text= (TextView) findViewById(R.id.tableCustomers);
	    text.setText("Customers: "+table.curClients+"/"+table.maxClients+"");
	    
	    listview.invalidateViews();
	}

	/* Event triggered at click on "New Order button"
	 * 
	 * (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View button) {
		if(button==buttonAdd){
			addNewOrder();
		} else if(button == buttonFinalize){
			confirmClearAllOrder();
		} else if(button == buttonPayment){
			openOrderPayment();
		}
	}

	/* Event triggered on click on one of the orders
	 * 
	 * (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		Intent myIntent = new Intent(this, OrderActivity.class);
		myIntent.putExtra("tableID", table.id);
		myIntent.putExtra("orderID", table.tableOrder.orders.get(position).id);
		this.startActivity(myIntent);
	}

	/**
	 * confirm clear all order
	 */
	private void confirmClearAllOrder(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.table_order_dialog_confirm_cancel_all_title);
		builder.setMessage(R.string.table_order_dialog_confirm_cancel_all_message);

		builder.setPositiveButton(R.string.table_order_dialog_confirm_cancel_all_ok, new Dialog.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "Yes, let's do it", Toast.LENGTH_SHORT).show();
				clearAllOrder();
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
	 * clear all order
	 */
	private void clearAllOrder(){
		table.tableOrder.orders.clear();
		listview.setAdapter(null);
		table.empty=true;
		table.curClients=0;
		Toast.makeText(this, "Finalized the order!", Toast.LENGTH_SHORT).show();
		table.empty = true;
	}

	/**
	 * add new order
	 */
	private void addNewOrder(){
		table.tableOrder.orders.add(new Order());
		listview.setAdapter(new TableOrderAdapter(this, tableOrder));
		Toast.makeText(this, R.string.table_order_msg_add_new_order, Toast.LENGTH_SHORT).show();
		table.empty = false;
	}

	private void openOrderPayment(){
		Intent myIntent = new Intent(this, OrderPaymentListActivity.class);
		myIntent.putExtra("tableID", table.id);
		this.startActivity(myIntent);
	}
}