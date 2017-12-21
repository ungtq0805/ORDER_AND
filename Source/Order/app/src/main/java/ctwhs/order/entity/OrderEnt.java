/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ctwhs.order.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * The Class Order.
 */
@DatabaseTable(tableName = "ORDER")
public class OrderEnt {

	/** The global id. */
	private static int globalID=0;

	/** The id. */
	@DatabaseField(columnName = "ID",generatedId = true)
	private int id;

	/** The total price. */
	@DatabaseField(columnName = "TOTAL_PRICE")
	private float totalPrice;

	/** The duration, in minutes. */
	@DatabaseField(columnName = "DURATION")
	private int duration;

	/*STATUS IS PAY OR NOT*/
	@DatabaseField(columnName = "IS_PAYMENT")
	private boolean isPayment;

	/** The ordered products. */
	@ForeignCollectionField(eager = false)
	private ForeignCollection<OrderDetailEnt> orderDetails;

	@DatabaseField(columnName = "TABLE_ORDER_ID", foreign = true, foreignAutoRefresh = true)
	private TableOrderEnt tableOrderEnt;

	/** Type of reg date time */
	@DatabaseField(columnName = "REG_DATE_TIME")
	private Date regDateTime;

	/**
	 * Instantiates a new order.
	 */
	public OrderEnt() {
//		id=globalID++;
//		products=new ArrayList<OrderProduct>();
//		totalPrice=0;
//		duration=0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order [id=" + id + ", products=" + orderDetails + ", totalPrice=" + totalPrice + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isPayment() {
		return isPayment;
	}

	public void setPayment(boolean payment) {
		isPayment = payment;
	}

	public ForeignCollection<OrderDetailEnt> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(ForeignCollection<OrderDetailEnt> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public TableOrderEnt getTableOrderEnt() {
		return tableOrderEnt;
	}

	public void setTableOrderEnt(TableOrderEnt tableOrderEnt) {
		this.tableOrderEnt = tableOrderEnt;
	}

	public Date getRegDateTime() {
		return regDateTime;
	}

	public void setRegDateTime(Date regDateTime) {
		this.regDateTime = regDateTime;
	}
}
