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

import java.io.Serializable;
import java.util.Date;

/**
 * The Class TableOrder.
 */
@DatabaseTable(tableName = "TABLE_ORDER")
public class TableOrderEnt implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6577234748166232293L;

	/** The id. */
	@DatabaseField(columnName = "ID",generatedId = true)
	private int id;

	/** The duration, in minutes. */
	@DatabaseField(columnName = "DURATION")
	private int duration;

	/** The orders. */
	@ForeignCollectionField(eager = false)
	private ForeignCollection<OrderEnt> orders;

	/** Type of reg date time */
	@DatabaseField(columnName = "REG_DATE_TIME")
	private Date regDateTime;

	/**
	 * Instantiates a new table order.
	 */
	public TableOrderEnt() {
		super();
//		orders=new ArrayList<Order>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public ForeignCollection<OrderEnt> getOrders() {
		return orders;
	}

	public void setOrders(ForeignCollection<OrderEnt> orders) {
		this.orders = orders;
	}

	public Date getRegDateTime() {
		return regDateTime;
	}

	public void setRegDateTime(Date regDateTime) {
		this.regDateTime = regDateTime;
	}
}
