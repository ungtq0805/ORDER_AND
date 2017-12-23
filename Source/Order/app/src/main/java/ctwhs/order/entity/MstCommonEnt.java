/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ctwhs.order.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class OrderProduct.
 */
@DatabaseTable(tableName = "MST_COMMON")
public class MstCommonEnt {

	/** The COMMON NO. */
	@DatabaseField(columnName = "COMMON_NO", id = true)
	private String commonNo;

	/** The class no. */
	@DatabaseField(columnName = "CLASSNO")
	private String classNo;

	@DatabaseField(columnName = "CLASSNAME")
	private String className;

	@DatabaseField(columnName = "DATA_1")
	private String data1;

	@DatabaseField(columnName = "DATA_2")
	private String data2;

	@DatabaseField(columnName = "DATA_3")
	private String data3;

	@DatabaseField(columnName = "DATA_4")
	private String data4;

	@DatabaseField(columnName = "DATA_5")
	private String data5;

	@DatabaseField(columnName = "DATA_6")
	private String data6;

	@DatabaseField(columnName = "DATA_NUMBER_1")
	private BigDecimal dataNumber1;

	@DatabaseField(columnName = "DATA_NUMBER_2")
	private BigDecimal dataNumber2;

	@DatabaseField(columnName = "DATA_NUMBER_3")
	private BigDecimal dataNumber3;

	@DatabaseField(columnName = "DATA_NUMBER_4")
	private BigDecimal dataNumber4;

	@DatabaseField(columnName = "DATA_NUMBER_5")
	private BigDecimal dataNumber5;

	@DatabaseField(columnName = "DATA_NUMBER_6")
	private BigDecimal dataNumber6;

	@DatabaseField(columnName = "DATA_FLG")
	private Boolean dataFlg;

	@DatabaseField(columnName = "DESC")
	private String desc;

	@DatabaseField(columnName = "INSERT_DATE")
	private Date insertDate;

	@DatabaseField(columnName = "UPDATE_DATE")
	private Date updateDate;

	public MstCommonEnt() {
		super();
	}

	public String getCommonNo() {
		return commonNo;
	}

	public void setCommonNo(String commonNo) {
		this.commonNo = commonNo;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public String getData3() {
		return data3;
	}

	public void setData3(String data3) {
		this.data3 = data3;
	}

	public String getData4() {
		return data4;
	}

	public void setData4(String data4) {
		this.data4 = data4;
	}

	public String getData5() {
		return data5;
	}

	public void setData5(String data5) {
		this.data5 = data5;
	}

	public String getData6() {
		return data6;
	}

	public void setData6(String data6) {
		this.data6 = data6;
	}

	public BigDecimal getDataNumber1() {
		return dataNumber1;
	}

	public void setDataNumber1(BigDecimal dataNumber1) {
		this.dataNumber1 = dataNumber1;
	}

	public BigDecimal getDataNumber2() {
		return dataNumber2;
	}

	public void setDataNumber2(BigDecimal dataNumber2) {
		this.dataNumber2 = dataNumber2;
	}

	public BigDecimal getDataNumber3() {
		return dataNumber3;
	}

	public void setDataNumber3(BigDecimal dataNumber3) {
		this.dataNumber3 = dataNumber3;
	}

	public BigDecimal getDataNumber4() {
		return dataNumber4;
	}

	public void setDataNumber4(BigDecimal dataNumber4) {
		this.dataNumber4 = dataNumber4;
	}

	public BigDecimal getDataNumber5() {
		return dataNumber5;
	}

	public void setDataNumber5(BigDecimal dataNumber5) {
		this.dataNumber5 = dataNumber5;
	}

	public BigDecimal getDataNumber6() {
		return dataNumber6;
	}

	public void setDataNumber6(BigDecimal dataNumber6) {
		this.dataNumber6 = dataNumber6;
	}

	public Boolean getDataFlg() {
		return dataFlg;
	}

	public void setDataFlg(Boolean dataFlg) {
		this.dataFlg = dataFlg;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * set default values
	 * @param commonNo
	 * @param classNo
	 * @param className
	 * @param data1
	 * @param data2
	 * @param data3
	 * @param data4
	 * @param data5
	 * @param data6
	 * @param dataNumber1
	 * @param dataNumber2
	 * @param dataNumber3
	 * @param dataNumber4
	 * @param dataNumber5
	 * @param dataNumber6
	 * @param dataFlg
	 * @param desc
	 * @param insertDate
	 * @param updateDate
	 */
	public void setValues(String commonNo,
						  String classNo,
						  String className,
						  String data1,
						  String data2,
						  String data3,
						  String data4,
						  String data5,
						  String data6,
						  BigDecimal dataNumber1,
						  BigDecimal dataNumber2,
						  BigDecimal dataNumber3,
						  BigDecimal dataNumber4,
						  BigDecimal dataNumber5,
						  BigDecimal dataNumber6,
						  Boolean dataFlg,
						  String desc,
						  Date insertDate,
						  Date updateDate) {
		this.commonNo = commonNo;
		this.classNo = classNo;
		this.className = className;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.data4 = data4;
		this.data5 = data5;
		this.data6 = data6;
		this.dataNumber1 = dataNumber1;
		this.dataNumber2 = dataNumber2;
		this.dataNumber3 = dataNumber3;
		this.dataNumber4 = dataNumber4;
		this.dataNumber5 = dataNumber5;
		this.dataNumber6 = dataNumber6;
		this.dataFlg = dataFlg;
		this.desc = desc;
		this.insertDate = insertDate;
		this.updateDate = updateDate;
	}
}
