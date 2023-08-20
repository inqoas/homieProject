package tw.idv.tibame.tha102.web.service.dao.Imp;

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

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import tw.idv.tibame.tha102.web.seller.vo.Seller;
import tw.idv.tibame.tha102.web.service.dao.ServiceDao;
import tw.idv.tibame.tha102.web.service.vo.Service;

public class ServiceDaoImpl implements ServiceDao{

	public static void main(String [] args)  {
//		byte [] bytes;
//		InputStream ii;
//		try {
//			ii =new FileInputStream("C:\\pic\\02.jpg");
//			
//			int aa = ii.available();
//			
//			bytes = new byte[aa];		
//			
//			ii.read(bytes);
//			
//			ii.close();
//			
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		byte [] bytes =null;
//		
//		InputStream in =null;
//		
//		try {
//			in = new FileInputStream("C:\\pic\\11.jpg");
//			
//			int num =in.available();
//			
//			bytes = new byte[num];
//			
//			in.read(bytes);
//			
//			in.close();
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		Service service =new Service();
//		
//		service.setService_name("玩命服務");
//		service.setService_id_code(3);
//		service.setService_introduction("身體跟心靈刺激");
//		service.setService_price(1000);
//		service.setUser_id(2);
//		service.setService_picture(bytes);
//		
//		System.out.println(new ServiceDaoImpl().add_service(service));
// 3  and  avt.date = '2023-08-15'
		
//		List<Service>  ll =new ServiceDaoImpl().select_check("台北市",3,"2023-08-15",2);
// 		
//		for( Service  ss: ll ) {
//			System.out.println(ss);
//		}
		
//		Service service =new ServiceDaoImpl().Select_byId(2);
//		
//		System.out.println(service.toString());
		
		String sql ="SELECT distinct se.*, sel.* FROM seller sel join service se on  sel.user_id = se.user_id where se.service_id =2 ";
		try(Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement ps =con.prepareStatement(sql)	){
			ResultSet rs =ps.executeQuery();
			Service service =null;
			while( rs.next()) {
				service =new Service();
				Seller seller =new Seller();
				seller.setTotalReviewCount(rs.getInt("total_review_count"));
				seller.setTotalReviewStars(rs.getInt("total_review_stars"));
				
				service.setSeller(seller);
					
				
				
			}
					
			System.out.println(service.getSeller().getTotalReviewStars());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	private final static String All_Service   ="select * from service ";
	private final static String Insert_serice ="Insert into service(service_id_code, user_id, service_name, service_price, service_introduction, service_picture)"
			                                   +"values(?,?,?,?,?,?)";
	private final static String Select_img    ="select service_picture from service where service_id =?";
	private final static String Select_ById   ="SELECT distinct se.*, sel.* FROM seller sel join service se on sel.user_id = se.user_id where se.service_id =? ";

	
	@Override
	public List<Service> Service_All() {
		List<Service> services = new ArrayList<>();
		
		try(Connection com =DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement  ps = com.prepareStatement(All_Service)	) {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Service service = new Service();
				service.setService_id(rs.getInt("service_id"));
				service.setService_id_code(rs.getInt("service_id_code"));
				service.setUser_id(rs.getInt("user_id"));
				service.setService_name(rs.getString("service_name"));
				service.setService_price(rs.getInt("service_price"));
				service.setService_introduction(rs.getString("service_introduction"));
				service.setService_picture(rs.getBytes("service_picture"));
				services.add(service);
			}
			return services;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public Integer add_service(Service service) {
		
		try(Connection con =DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement ps =con.prepareStatement(Insert_serice)     ){
 		
			ps.setInt   (1,service.getService_id_code());
			ps.setInt   (2,service.getUser_id());
			ps.setString(3,service.getService_name());
			ps.setInt   (4,service.getService_price());
			ps.setString(5,service.getService_introduction());
			ps.setBytes (6, service.getService_picture());
			
			int rowCount = ps.executeUpdate();
			return rowCount;
			
		}catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public Service select_ImgbyId(Integer id) {
			Service service = null;
		try(Connection con =DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement ps = con.prepareStatement(Select_img)	){
			ps.setInt(1, id);
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				service =new Service();
				service.setService_picture(rs.getBytes("service_picture"));	
			}
				return service;
	    } catch (SQLException e){
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return service;
	}
     
    
	@Override
	public List<Service> select_check(String area_name, Integer service_id_code, String service_date,
	Integer service_state) {
		
		String Select_check  ="select DISTINCT ser.*from seller sel\r\n"
				+ "join service ser on ser.user_id = sel.user_id\r\n"
				+ "join available_time avt on sel.user_id = avt.user_id\r\n"
				+ "join area ar on sel.user_id = sel.user_id ";
		
	 	List<Service> services =new ArrayList();
		boolean isWhere = false;
			
		if( !"01".equals(area_name) ) {
			
			Select_check = Select_check + "where ar.area_name= ? ";
			isWhere =true;
		
		}
			 
		if(service_id_code != 1) {
			
			if( isWhere ) {
				
				Select_check = Select_check + "and ser.service_id_code= ? ";
					
			}else {
				
				Select_check = Select_check + "where ser.service_id_code= ? ";
				isWhere = true;
				
			}
			
		}	
		
		if(!"".equals(service_date)) {
		
			if( isWhere ) {
				
				Select_check = Select_check + "and avt.date= ? ";
					
			}else {
				
				Select_check = Select_check + "where avt.date= ? ";
				isWhere = true;
				
			}
			
		}
			
		if(service_state != 0) {
			
			if( isWhere ) {
				if( service_state == 1 ) {
					Select_check = Select_check + "and avt.morning= ? ";
				}else if( service_state == 2 ) {
					Select_check = Select_check + "and avt.afternoon= ? ";
				}else if( service_state ==  3) {
					Select_check = Select_check + "and avt.night= ? ";
				}	
			}else {
				if( service_state == 1 ) {
					Select_check = Select_check + "and avt.morning= ? ";
					isWhere = true;
				}else if( service_state == 2 ) {
					Select_check = Select_check + "and avt.afternoon= ? ";
					isWhere = true;
				}else if( service_state ==  3) {
					Select_check = Select_check + "and avt.night= ? ";
					isWhere = true;
				}	
				
				
			}
			
		}	
			
			
		try(Connection con =DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement ps =con.prepareStatement(Select_check)) {
			
			int parameterIndex = 1;
			
			if (!"01".equals(area_name)) {
	            ps.setString(parameterIndex++, area_name);
	        }

	        if (service_id_code != 1) {
	            ps.setInt(parameterIndex++, service_id_code);
	        }

	        if (!"".equals(service_date)) {
	            ps.setString(parameterIndex++, service_date);
	        }

	        if (service_state != 0) {
	        	
	        	if( service_state == 1) {
	        		ps.setInt(parameterIndex, 1);
	        	}else if( service_state == 2) {
	        		ps.setInt(parameterIndex, 1);
	        	}else if( service_state == 3) {
	        		ps.setInt(parameterIndex, 1);
	        	}
	        	     
	        }
			
	        ResultSet rs =ps.executeQuery();
	        
	        while(rs.next()) {
	        	
	        	Service service =new Service();
      
				service.setService_id(rs.getInt("service_id"));
				service.setService_id_code(rs.getInt("service_id_code"));
				service.setUser_id(rs.getInt("user_id"));
				service.setService_name(rs.getString("service_name"));
				service.setService_price(rs.getInt("service_price"));
				service.setService_introduction(rs.getString("service_introduction"));
				service.setService_picture(rs.getBytes("service_picture"));
				
				services.add(service);
	        		
	        }		
	        
	        
	        return services;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}


	@Override
	public Service Select_byId(Integer id) {
		
		try(Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement ps= con.prepareStatement(Select_ById)	) {
			ps.setInt(1,id);
			ResultSet rs =ps.executeQuery();
			while( rs.next()) {
				Service service = new Service();
				Seller  seller  = new Seller();
				service.setService_id(rs.getInt("service_id"));
				service.setService_id_code(rs.getInt("service_id_code"));
				service.setUser_id(rs.getInt("user_id"));
				service.setService_name(rs.getString("service_name"));
				service.setService_price(rs.getInt("service_price"));
				service.setService_introduction(rs.getString("service_introduction"));
				service.setService_picture(rs.getBytes("service_picture"));
				
				seller.setTotalReviewCount(rs.getInt("total_review_count"));
				seller.setTotalReviewStars(rs.getInt("total_review_stars"));
				
				service.setSeller(seller);
				
				return service;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}


	

}
