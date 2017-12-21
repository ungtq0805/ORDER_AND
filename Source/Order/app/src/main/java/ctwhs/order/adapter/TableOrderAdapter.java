package ctwhs.order.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import ctwhs.common.utils.ConvertUtils;
import ctwhs.order.dto.Order;
import ctwhs.order.dto.TableOrder;


public class TableOrderAdapter extends BaseAdapter{

	private final Context mContext;
	private TableOrder tableOrder;

	public TableOrderAdapter(Context c, TableOrder tableOrder) {
		this.mContext = c;
		this.tableOrder=tableOrder;
	}

	
	@Override
	public int getCount() {
		return tableOrder.orders.size();
	}


	@Override
	public Object getItem(int position) {
		return tableOrder.orders.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LinearLayout layout;
		TextView orderButton;
		CheckBox chkBox;

		Order order=tableOrder.orders.get(position);
		//Try to use a recycled button
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
//			layout = new LinearLayout(mContext);
//			layout.setOrientation(LinearLayout.HORIZONTAL);

//			chkBox = new CheckBox(mContext);
//			chkBox.setGravity(Gravity.LEFT);
//			chkBox.setPadding(0, 8, 8, 8);
//			layout.addView(chkBox);

			orderButton = new TextView(mContext);
			orderButton.setGravity(Gravity.CENTER);
			orderButton.setPadding(8, 8, 8, 8);
			orderButton.setTextColor(Color.WHITE);
//			layout.addView(orderButton);

		} else {
//			layout = (LinearLayout) convertView;
//			int count = layout.getChildCount();
//			chkBox = (CheckBox)layout.getChildAt(0);
//			orderButton = (TextView) layout.getChildAt(1);

			orderButton = (TextView) convertView;

//			chkBox = (CheckBox) convertView;
//			layout.addView(chkBox);
		}
		
		//Add the specific data
		orderButton.setText("Order "+order.id+"\n\nProducts: "+order.products.size()+"\t\tTotal Price: " + ConvertUtils.getDecimal(order.totalPrice));
		
		//tableButton.setImageResource(mThumbIds[position]);
		return orderButton;
	}

}
