/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ctwhs.order.dto;


/**
 * The Class ProductCategory.
 */
public class ProductCategory {
	
	/** The name. */
	public String name;
	
	/** The type. */
	public Product.Type type;

	/** The resource. */
	public int resource;
	
	/**
	 * Instantiates a new product category.
	 *
	 * @param type the type
	 * @param resource the resource
	 */
	public ProductCategory(String name, Product.Type type, int resource) {
		super();
		this.type = type;
		this.name=name;
		this.resource = resource;
	}
}
