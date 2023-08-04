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

import tw.idv.tibame.tha102.web.service.dao.ServiceDao;
import tw.idv.tibame.tha102.web.service.vo.Service;

public class ServiceDaoImpl implements ServiceDao{

	public static void main(String [] args) throws IOException {
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
//		
//		Service ss =new Service();
//		
//			ss.setService_id(null);
//			ss.setService_id_code(null);
		
		
		List<Service> ll = new ServiceDaoImpl().Service_All();
		for(Service ss : ll) {
			 System.out.println(ss);
		}
			
	}
	
	private final static String All_Service   ="select * from service";
	private final static String Insert_serice ="Insert into service(service_id, service_id_code, user_id, service_name, service_price, service_introduction, service_picture)"
			                                   +"values(?,?,?,?,?,?,?)";
	private final static String Select_img    ="select service_picture from service";
	
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
			PreparedStatement ps =con.prepareStatement(Insert_serice);     ){
 		
			ps.setInt   (1,service.getService_id());
			ps.setInt   (2,service.getService_id_code());
			ps.setInt   (3,service.getUser_id());
			ps.setString(4,service.getService_name());
			ps.setInt   (5,service.getService_price());
			ps.setString(6,service.getService_introduction());
			ps.setBytes (7, service.getService_picture());
			
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
		
		try(Connection con =DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement ps = con.prepareStatement(All_Service)	){
	     
	
	
	    } catch (SQLException e){
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}


	

}
