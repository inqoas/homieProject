package tw.idv.tibame.tha102.web.product.dao;

import static tw.idv.tibame.tha102.core.util.CommonMysql.PASSWORD;
import static tw.idv.tibame.tha102.core.util.CommonMysql.URL;
import static tw.idv.tibame.tha102.core.util.CommonMysql.USER;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.idv.tibame.tha102.web.product.vo.Product;

public class ProductDaoImpl implements ProductDao{
	
	private static final String INSERT_STMT = "INSERT INTO product (product_name, product_price, product_stock, product_shipped, product_introduction, product_picture, product_category, product_review_stars, product_review_count) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_STMT = "UPDATE product SET product_name = ?, product_price = ?, product_stock = ?, product_shipped = ?, product_introduction = ?, product_picture = ?, product_category = ?, product_review_stars = ?, product_review_count = ? WHERE product_id = ?";
    private static final String DELETE_STMT = "DELETE FROM product WHERE product_id = ?";
    private static final String GET_ALL_STMT = "SELECT * FROM product";
    private static final String GET_BY_ID_STMT = "SELECT * FROM product WHERE product_id = ?";
    private static final String GET_IMG_BY_ID_STMT ="SELECT product_picture FROM product WHERE product_id = ?";
    private static final String GET_PRO_COUNT_STMT ="select count(*) from product  group by product_category order by product_category ";
    private static final String GET_PRO_BY_ITEM_STMT="select * from product where product_category = ?";
    
    
    
    
    public void insert(Product product) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(INSERT_STMT)) {
            ps.setString(1, product.getProduct_name());
            ps.setInt(2, product.getProduct_price());
            ps.setInt(3, product.getProduct_stock());
            ps.setInt(4, product.getProduct_shipped());
            ps.setString(5, product.getProduct_introduction());
            ps.setBytes(6, product.getProduct_picture());
            ps.setInt(7, product.getProduct_category());
            ps.setInt(8, product.getProduct_review_stars());
            ps.setInt(9, product.getProduct_review_count());
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Product product) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(UPDATE_STMT)) {
            ps.setString(1, product.getProduct_name());
            ps.setInt(2, product.getProduct_price());
            ps.setInt(3, product.getProduct_stock());
            ps.setInt(4, product.getProduct_shipped());
            ps.setString(5, product.getProduct_introduction());
            ps.setBytes(6, product.getProduct_picture());
            ps.setInt(7, product.getProduct_category());
            ps.setInt(8, product.getProduct_review_stars());
            ps.setInt(9, product.getProduct_review_count());
            ps.setInt(10, product.getProduct_id());
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer product_id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(DELETE_STMT)) {
            ps.setInt(1, product_id);
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(GET_ALL_STMT);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getInt("product_id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getInt("product_price"));
                product.setProduct_stock(rs.getInt("product_stock"));
                product.setProduct_shipped(rs.getInt("product_shipped"));
                product.setProduct_introduction(rs.getString("product_introduction"));
                product.setProduct_picture(rs.getBytes("product_picture"));
                product.setProduct_category(rs.getInt("product_category"));
                product.setProduct_review_stars(rs.getInt("product_review_stars"));
                product.setProduct_review_count(rs.getInt("product_review_count"));
                
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
//    public List<Product> getAll() {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        List<Product> products = null;
//
//        try {
//            Query<Product> query = session.createQuery("FROM Product", Product.class);
//            products = query.list();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//
//        return products;
//    }

    public Product getById(Integer product_id) {
        Product product = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(GET_BY_ID_STMT)) {
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProduct_id(rs.getInt("product_id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getInt("product_price"));
                product.setProduct_stock(rs.getInt("product_stock"));
                product.setProduct_shipped(rs.getInt("product_shipped"));
                product.setProduct_introduction(rs.getString("product_introduction"));
                product.setProduct_picture(rs.getBytes("product_picture"));
                product.setProduct_category(rs.getInt("product_category"));
                product.setProduct_review_stars(rs.getInt("product_review_stars"));
                product.setProduct_review_count(rs.getInt("product_review_count"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
    public List<Product> getProduct_item(Integer product_category ){
    	List<Product> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(GET_PRO_BY_ITEM_STMT);){
             ps.setInt(1,product_category);
        	 ResultSet rs = ps.executeQuery(); 
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getInt("product_id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getInt("product_price"));
                product.setProduct_stock(rs.getInt("product_stock"));
                product.setProduct_shipped(rs.getInt("product_shipped"));
                product.setProduct_introduction(rs.getString("product_introduction"));
                product.setProduct_picture(rs.getBytes("product_picture"));
                product.setProduct_category(rs.getInt("product_category"));
                product.setProduct_review_stars(rs.getInt("product_review_stars"));
                product.setProduct_review_count(rs.getInt("product_review_count"));
                
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    	
    	
    }
   
    //查產品數量
    public List<Integer> getProductCount(){
    	List<Integer> ll=null;
    	
    	try(Connection con =DriverManager.getConnection(URL,USER,PASSWORD);) {
			PreparedStatement ps =con.prepareStatement(GET_PRO_COUNT_STMT);
			ResultSet rs =ps.executeQuery();
			ll =new ArrayList<>();
			while(rs.next()) {
				ll.add(rs.getInt(1));
			}
    		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return ll;
    	
    }
    
    //查圖片
	public Product getProduct_ImgById(Integer Product_id) {
		Product product = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(GET_IMG_BY_ID_STMT)) {
			ps.setInt(1, Product_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product = new Product();
				product.setProduct_picture(rs.getBytes("product_picture"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;

	}
 

}
