/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ctwhs.order.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * The Class OrderProduct.
 */
@DatabaseTable(tableName = "ORDER_DETAILS")
public class OrderDetailEnt {

	@DatabaseField(columnName = "ID",generatedId = true)
	private int id;

	/** The product. */
	@DatabaseField(columnName = "PRODUCT_ID", foreign = true)
	private ProductEnt product;
	
	/** The quantity. */
	@DatabaseField(columnName = "QUANTITY")
	private int quantity;

	@DatabaseField(columnName = "ORDER_ID", foreign = true, foreignAutoRefresh = true)
	private OrderEnt orderEnt;

	/** Type of reg date time */
	@DatabaseField(columnName = "REG_DATE_TIME")
	private Date regDateTime;

	public OrderDetailEnt() {

	}

	/* (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailEnt other = (OrderDetailEnt) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProductEnt getProduct() {
		return product;
	}

	public void setProduct(ProductEnt product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderEnt getOrderEnt() {
		return orderEnt;
	}

	public void setOrderEnt(OrderEnt orderEnt) {
		this.orderEnt = orderEnt;
	}

	public Date getRegDateTime() {
		return regDateTime;
	}

	public void setRegDateTime(Date regDateTime) {
		this.regDateTime = regDateTime;
	}
}
