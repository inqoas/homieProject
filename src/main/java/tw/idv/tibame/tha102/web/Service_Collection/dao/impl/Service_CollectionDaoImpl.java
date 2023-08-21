package tw.idv.tibame.tha102.web.Service_Collection.dao.impl;

import static tw.idv.tibame.tha102.core.util.CommonMysql.PASSWORD;
import static tw.idv.tibame.tha102.core.util.CommonMysql.URL;
import static tw.idv.tibame.tha102.core.util.CommonMysql.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import tw.idv.tibame.tha102.core.test.testmain;
import tw.idv.tibame.tha102.web.Service_Collection.vo.Service_Collection;

public class Service_CollectionDaoImpl implements Service_CollectionDao{
	
	public static void main(String[] args) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Service_Collection sss = new Service_Collection();
		
		sss.setUser_id(3);
		sss.setCategory_id(3);
		//sss.setCollect_time(timestamp);
		boolean bol= new Service_CollectionDaoImpl().Select_SerById(sss);
		//new Service_CollectionDaoImpl().Delete_SerById(sss);
		//new Service_CollectionDaoImpl().Insert_Ser_Coll(sss);
		System.out.println(bol);
	}
	
	private String Insert_ser ="insert into service_collection( user_id, category_id, collect_time)\r\n"
			+ "value(?,?,?);"; 
	private String Select_ser ="select * from service_collection where user_id=? and category_id=?";
	private	String Delete_ser ="delete from service_collection where user_id =? and category_id=?";
	
	
	@Override
	public void Insert_Ser_Coll(Service_Collection ser_coll) {
		
		try(Connection con =DriverManager.getConnection(URL,USER,PASSWORD);
				PreparedStatement ps =con.prepareStatement(Insert_ser) ){
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				ps.setInt   (1,ser_coll.getUser_id());
				ps.setInt   (2,ser_coll.getCategory_id());
				ps.setTimestamp(3,timestamp);
			
				int rowCount = ps.executeUpdate();
				
				System.out.println(rowCount);
				
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	@Override
	public boolean Select_SerById(Service_Collection ser_coll) {
		boolean exist =false;
		try(Connection com =DriverManager.getConnection(URL,USER,PASSWORD);
				PreparedStatement  ps = com.prepareStatement(Select_ser)) {
				
				ps.setInt(1,ser_coll.getUser_id() );
				ps.setInt(2,ser_coll.getCategory_id());
				
				ResultSet rs = ps.executeQuery();
				
				 exist=rs.next();

				return exist;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return exist;
	}

	@Override
	public void Delete_SerById(Service_Collection ser_coll) {
		
		try(Connection con =DriverManager.getConnection(URL,USER,PASSWORD);
				PreparedStatement ps =con.prepareStatement(Delete_ser) ){
				
				ps.setInt   (1,ser_coll.getUser_id());
				ps.setInt   (2,ser_coll.getCategory_id());
			
				int rowCount = ps.executeUpdate();
				
				System.out.println(rowCount);
				
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
