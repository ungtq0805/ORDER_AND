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
 * The Class Product.
 */
@DatabaseTable(tableName = "PRODUCT")
public class ProductEnt {

	/** The global id. */
	private static int globalID=0;

	/** The id. */
	@DatabaseField(columnName = "ID",generatedId = true)
	private int id;

	/** The description. */
	@DatabaseField(columnName = "PRODUCT_NAME")
	private String name;

	/** The description. */
	@DatabaseField(columnName = "PRODUCT_NAME_JP")
	private String nameJp;

	/** The price. */
	@DatabaseField(columnName = "PRODUCT_PRICE")
	private float price;

	/** The resource. */
	@DatabaseField(columnName = "RESOURCE")
	private int resource;

	/** The type. */
	@DatabaseField(columnName = "PRODUCT_TYPE_ID", foreign = true)
	private ProductTypeEnt type;

	/** The duration. */
	@DatabaseField(columnName = "DURATION")
	private int duration;

	@DatabaseField(columnName = "NOTES")
	private String notes;

	@DatabaseField(columnName = "IMAGE_PATH")
	private String imagePath;

	@DatabaseField(columnName = "IS_OUT_OF_STOCK")
	private Boolean isOutOfStock;

	/** Type of reg date time */
	@DatabaseField(columnName = "REG_DATE_TIME")
	private Date regDateTime;

	public ProductEnt() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameJp() {
		return nameJp;
	}

	public void setNameJp(String nameJp) {
		this.nameJp = nameJp;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getResource() {
		return resource;
	}

	public void setResource(int resource) {
		this.resource = resource;
	}

	public ProductTypeEnt getType() {
		return type;
	}

	public void setType(ProductTypeEnt type) {
		this.type = type;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getRegDateTime() {
		return regDateTime;
	}

	public void setRegDateTime(Date regDateTime) {
		this.regDateTime = regDateTime;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Boolean getOutOfStock() {
		return isOutOfStock;
	}

	public void setOutOfStock(Boolean outOfStock) {
		isOutOfStock = outOfStock;
	}

	@Override
	public String toString() {
		return name;
	}
}
