package tw.idv.tibame.tha102.web.product.vo;

import java.util.Arrays;

public class Product implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    
	private Integer product_id;
    private String product_name;
    private Integer product_price;
    private Integer product_stock;
    private Integer product_shipped;
    private String product_introduction;
    private byte[] product_picture;
    private Integer product_category;
    private Integer product_review_stars;
    private Integer product_review_count;
    
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Integer getProduct_price() {
		return product_price;
	}
	public void setProduct_price(Integer product_price) {
		this.product_price = product_price;
	}
	public Integer getProduct_stock() {
		return product_stock;
	}
	public void setProduct_stock(Integer product_stock) {
		this.product_stock = product_stock;
	}
	public Integer getProduct_shipped() {
		return product_shipped;
	}
	public void setProduct_shipped(Integer product_shipped) {
		this.product_shipped = product_shipped;
	}
	public String getProduct_introduction() {
		return product_introduction;
	}
	public void setProduct_introduction(String product_introduction) {
		this.product_introduction = product_introduction;
	}
	public byte[] getProduct_picture() {
		return product_picture;
	}
	public void setProduct_picture(byte[] product_picture) {
		this.product_picture = product_picture;
	}
	public Integer getProduct_category() {
		return product_category;
	}
	public void setProduct_category(Integer product_category) {
		this.product_category = product_category;
	}
	public Integer getProduct_review_stars() {
		return product_review_stars;
	}
	public void setProduct_review_stars(Integer product_review_stars) {
		this.product_review_stars = product_review_stars;
	}
	public Integer getProduct_review_count() {
		return product_review_count;
	}
	public void setProduct_review_count(Integer product_review_count) {
		this.product_review_count = product_review_count;
	}
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", product_price="
				+ product_price + ", product_stock=" + product_stock + ", product_shipped=" + product_shipped
				+ ", product_introduction=" + product_introduction + ", product_picture="
				+ Arrays.toString(product_picture) + ", product_category=" + product_category
				+ ", product_review_stars=" + product_review_stars + ", product_review_count=" + product_review_count
				+ "]";
	}
    
    
}
