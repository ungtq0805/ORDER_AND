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

import ctwhs.common.utils.CalendarUtils;

/**
 * The Class Product Type.
 */
@DatabaseTable(tableName = "PRODUCT_TYPE")
public class ProductTypeEnt {

	@DatabaseField(columnName = "ID",generatedId = true)
	private int id;

	/** Type of foods */
	@DatabaseField(columnName = "PRODUCT_TYPE_NAME")
	private String productTypeName;

	/** Type of reg date time */
	@DatabaseField(columnName = "REG_DATE_TIME")
	private Date regDateTime;

	/** The ordered products. */
	@ForeignCollectionField(eager = false)
	private ForeignCollection<ProductEnt> products;

	public ProductTypeEnt() {
		this.regDateTime = CalendarUtils.getNow();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getRegDateTime() {
		return regDateTime;
	}

	public void setRegDateTime(Date regDateTime) {
		this.regDateTime = regDateTime;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	@Override
	public String toString() {
		return productTypeName;
	}

	public ForeignCollection<ProductEnt> getProducts() {
		return products;
	}

	public void setProducts(ForeignCollection<ProductEnt> products) {
		this.products = products;
	}
}
