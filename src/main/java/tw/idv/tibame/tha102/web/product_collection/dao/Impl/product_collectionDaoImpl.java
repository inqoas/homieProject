package tw.idv.tibame.tha102.web.product_collection.dao.Impl;

import static tw.idv.tibame.tha102.core.util.CommonMysql.PASSWORD;
import static tw.idv.tibame.tha102.core.util.CommonMysql.URL;
import static tw.idv.tibame.tha102.core.util.CommonMysql.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import tw.idv.tibame.tha102.web.product_collection.dao.product_collectionDao;
import tw.idv.tibame.tha102.web.product_collection.vo.Product_Collection;
import tw.idv.tibame.tha102.web.service.vo.Service;

public class product_collectionDaoImpl implements product_collectionDao {
	
	public static void main(String[] args) {
		
		Product_Collection product_Collection =new Product_Collection();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		product_Collection.setUser_id(2);
		product_Collection.setProduct_id(2);
		//product_Collection.setProduct_collect_time(timestamp);
		
		boolean str =new product_collectionDaoImpl().Select_UserId_ProId(product_Collection);
		
		System.out.println(str);
		
		//System.out.println(product_Collection.toString());
		//new product_collectionDaoImpl().delete_UserId_ProId(product_Collection);
		
	//	new product_collectionDaoImpl().Insert_product_collectionDao(product_Collection);
	
	
	}

	private String Insert_proColl ="insert into product_collection(user_id,product_id,product_collect_time)  "
			                       + "value(?,?,?)";
	
	private String Select_Id_proId="select * from product_collection where user_id =? and product_id =?";
	
	private String Delete_Id_proId="delete from product_collection where user_id =? and product_id =?";
	
	@Override
	public void Insert_product_collectionDao(Product_Collection product_collection) {
		
		try(Connection con =DriverManager.getConnection(URL,USER,PASSWORD);
				PreparedStatement ps =con.prepareStatement(Insert_proColl) ){
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				ps.setInt   (1,product_collection.getUser_id());
				ps.setInt   (2,product_collection.getProduct_id());
				ps.setTimestamp(3,timestamp);
			
				int rowCount = ps.executeUpdate();
				
				System.out.println(rowCount);
				
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	@Override
	public boolean Select_UserId_ProId(Product_Collection product_collection) {
		boolean exist =false;
		try(Connection com =DriverManager.getConnection(URL,USER,PASSWORD);
				PreparedStatement  ps = com.prepareStatement(Select_Id_proId)	) {
				
				ps.setInt(1,product_collection.getUser_id() );
				ps.setInt(2,product_collection.getProduct_id());
				
				ResultSet rs = ps.executeQuery();
				
				 exist=rs.next();
				//Product_Collection pro_coll =null;
//				while(rs.next()) {
//					pro_coll = new Product_Collection();
//						
//					pro_coll.setUser_id(rs.getInt(1));
//					pro_coll.setProduct_id(rs.getInt(2));
//					Timestamp timestamp = new Timestamp(rs.getLong(3));
//					pro_coll.setProduct_collect_time(timestamp);
//				
//				}
				
				return exist;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return exist;
	}

	@Override
	public void delete_UserId_ProId(Product_Collection product_collection) {
		// TODO Auto-generated method stub
		try(Connection con =DriverManager.getConnection(URL,USER,PASSWORD);
				PreparedStatement ps =con.prepareStatement(Delete_Id_proId) ){
	 		
				ps.setInt   (1,product_collection.getUser_id());
				ps.setInt   (2,product_collection.getProduct_id());
			
				int rowCount = ps.executeUpdate();
				
				System.out.println(rowCount);
				
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

}
