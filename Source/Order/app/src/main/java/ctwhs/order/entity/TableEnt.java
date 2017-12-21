/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ctwhs.order.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class Table.
 */
@DatabaseTable(tableName = "TABLE")
public class TableEnt implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6785961774305587584L;

	/**
	 * Instantiates a new table.
	 *
	 * @param id the id
	 * @param maxClients the max clients
	 */
//	public TableEnt(int id, int maxClients) {
//		super();
//		this.id = id;
//		this.maxClients = maxClients;
//		this.curClients=0;
//		this.empty=true;
//	}

	/** The id. */
	@DatabaseField(columnName = "ID",generatedId = true)
	private int id;
	
	/** The empty. */
	@DatabaseField(columnName = "EMPTY")
	private boolean empty;
	
	/** The max clients. */
	@DatabaseField(columnName = "MAX_CLIENTS")
	private int maxClients;
	
	/** The cur clients. */
	@DatabaseField(columnName = "CUR_CLIENTS")
	private int curClients;
	
	/** The table order. */
	@DatabaseField(columnName = "TABLE_ORDER", foreign = true)
	private TableOrderEnt tableOrder;

	/** Type of reg date time */
	@DatabaseField(columnName = "REG_DATE_TIME")
	private Date regDateTime;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Table [id=" + id + ", empty=" + empty + ", curClients=" + curClients + ", maxClients="
				+ maxClients + "]";
	}

	public TableEnt() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public int getMaxClients() {
		return maxClients;
	}

	public void setMaxClients(int maxClients) {
		this.maxClients = maxClients;
	}

	public int getCurClients() {
		return curClients;
	}

	public void setCurClients(int curClients) {
		this.curClients = curClients;
	}

	public TableOrderEnt getTableOrder() {
		return tableOrder;
	}

	public void setTableOrder(TableOrderEnt tableOrder) {
		this.tableOrder = tableOrder;
	}

	public Date getRegDateTime() {
		return regDateTime;
	}

	public void setRegDateTime(Date regDateTime) {
		this.regDateTime = regDateTime;
	}
}
