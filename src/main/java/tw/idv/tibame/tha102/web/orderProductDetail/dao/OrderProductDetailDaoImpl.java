package tw.idv.tibame.tha102.web.orderProductDetail.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static tw.idv.tibame.tha102.core.util.CommonMysql.PASSWORD;
import static tw.idv.tibame.tha102.core.util.CommonMysql.URL;
import static tw.idv.tibame.tha102.core.util.CommonMysql.USER;

import tw.idv.tibame.tha102.web.orderProductDetail.vo.OrderProductDetail;
import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;

public class OrderProductDetailDaoImpl implements OrderProductDetailDao{
	
	public static void main(String[] args) {
		
//		OrderProduct orderproduct =new OrderProduct();
//		orderproduct.setUser_id(2);
//		orderproduct.setProduct_total(10);
//		orderproduct.setProduct_status(0);
//		orderproduct.setTracking_number(123255);
//		orderproduct.setProduct_add_gc(2);
//		orderproduct.setProduct_deduct_gc(2);
//		
//		//order_product_id,product_id,product_quantity,product_name,product_price
//		List<OrderProductDetail> orderproductdetails =new ArrayList();
//		OrderProductDetail ord1 =new OrderProductDetail();
//		
//		//orderproductdetail.setOrderProductId(2);
//		ord1.setProductId(16);
//		ord1.setProductQuantity(20);
//		ord1.setProductName("titiio");
//		ord1.setProductPrice(3000);
//		OrderProductDetail ord =new OrderProductDetail();
//		
//		//orderproductdetail.setOrderProductId(1);
//		ord.setProductId(1);
//		ord.setProductQuantity(20);
//		ord.setProductName("jojo");
//		ord.setProductPrice(2000);
//		
//		orderproductdetails.add(ord1);
//		orderproductdetails.add(ord);
//		
//		
//		new OrderProductDetailDaoImpl().Inset_ProDetail(orderproductdetails,orderproduct);
//		
	}
	

	private final String OrdPro ="insert into order_product(user_id,product_total,product_status,tracking_number,product_add_gc,product_deduct_gc) "
			                    +"value(?,?,?,?,?,?)";
	private final String OrdProDet ="insert into order_product_detail(order_product_id,product_id,product_quantity,product_name,product_price) \r\n"
			                    + "value(?,?,?,?,?)";
	
	@Override
	public void Inset_ProDetail(List<OrderProductDetail> orderproductdetail, OrderProduct orderproduct) {
       
		String [] columns = {"order_product_id"};
		long OrdPro_id=0;
		
		try( Connection  con =DriverManager.getConnection(URL,USER,PASSWORD)){
			 PreparedStatement ps = con.prepareStatement(OrdPro, columns);
				ps.setInt(1, orderproduct.getUser_id());
				ps.setInt(2, orderproduct.getProduct_total());
				ps.setInt(3, orderproduct.getProduct_status());
				ps.setInt(4, orderproduct.getTracking_number());		
				ps.setInt(5, orderproduct.getProduct_add_gc());
				ps.setInt(6, orderproduct.getProduct_deduct_gc());
			    ps.executeUpdate();
			    
			    ResultSet rs = ps.getGeneratedKeys();    
				while(rs.next()) {
					OrdPro_id=rs.getLong(1);
				//	System.out.println(OrdPro_id);
				}
			
//order_product_id,product_id,product_quantity,product_name,product_price			
				PreparedStatement ps1 = con.prepareStatement(OrdProDet);
				for( OrderProductDetail ordprodet :orderproductdetail) {
					ps1.setLong(1,OrdPro_id);
					ps1.setInt(2,ordprodet.getProductId());
					ps1.setInt(3,ordprodet.getProductQuantity());
					ps1.setString(4,ordprodet.getProductName());
					ps1.setInt(5,ordprodet.getProductPrice());
					ps1.addBatch();

				}
				
				int[] counts = ps1.executeBatch();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
