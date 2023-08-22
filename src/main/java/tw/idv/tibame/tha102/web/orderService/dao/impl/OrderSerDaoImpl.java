package tw.idv.tibame.tha102.web.orderService.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import tw.idv.tibame.tha102.web.Redis.vo.Redis_Data_Service;
import tw.idv.tibame.tha102.web.orderService.vo.OrderService;

import static tw.idv.tibame.tha102.core.util.CommonMysql.PASSWORD;
import static tw.idv.tibame.tha102.core.util.CommonMysql.URL;
import static tw.idv.tibame.tha102.core.util.CommonMysql.USER;

public class OrderSerDaoImpl implements OrderSerDao{

	public static void main(String[] args) {
		OrderService orderservice =new OrderService();
		
		orderservice.setUserIdBuyer(2);
		orderservice.setUserIdSeller(2);
		orderservice.setOrderQuantity(10);
		orderservice.setOrderStatus(0);
		orderservice.setOrderUnitPrice(200);
	    orderservice.setOrderTotal(2000);
		orderservice.setOrderFinishPeriod(0);
		orderservice.setOrderAddGc(10);
		orderservice.setOrderServiceItem(1);
		orderservice.setServiceId(1);
			
		//new OrderSerDaoImpl().Insert_OrdSer(orderservice);
	}
	
	private String OrdProSer ="insert into order_service\r\n"
			+ "(user_id_buyer,user_id_seller,order_quantity,order_status,order_unit_price,order_total,"
			+ "order_placement_time,order_service_date,order_finish_period,order_add_gc,order_service_item,"
			+ "service_id)\r\n"
			+ "value(?,?,?,?,?,?,?,?,?,?,?,?)"; 
	
	@Override
	public void Insert_OrdSer(List<OrderService> orderservices) {
		
		try (Connection con =DriverManager.getConnection(URL,USER,PASSWORD)){
			PreparedStatement ps = con.prepareStatement(OrdProSer);
			
			for(OrderService orderservice :orderservices) {
				Timestamp NowTime = new Timestamp(System.currentTimeMillis());
				Timestamp timestamp = orderservice.getOrderServiceDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String OrderServiceDate = sdf.format(timestamp);
				ps.setInt( 1,orderservice.getUserIdBuyer());
				ps.setInt( 2,orderservice.getUserIdSeller());
				ps.setInt( 3,orderservice.getOrderQuantity());
				ps.setInt( 4,orderservice.getOrderStatus());
				ps.setInt( 5,orderservice.getOrderUnitPrice());
				ps.setInt( 6,orderservice.getOrderTotal());
				ps.setTimestamp(7, NowTime);
				ps.setString(8,OrderServiceDate);		
				ps.setInt( 9,orderservice.getOrderFinishPeriod());
				ps.setInt( 10,orderservice.getOrderAddGc());
				ps.setInt( 11,orderservice.getOrderServiceItem());
				ps.setInt( 12,orderservice.getServiceId());
				ps.addBatch();
			}	
				
			int[] counts = ps.executeBatch();
			
		     
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
