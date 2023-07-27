package tw.idv.tibame.tha102.web.userinfo.dao;

import static tw.idv.tibame.tha102.core.util.CommonMysql.PASSWORD;
import static tw.idv.tibame.tha102.core.util.CommonMysql.URL;
import static tw.idv.tibame.tha102.core.util.CommonMysql.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tw.idv.tibame.tha102.web.product.vo.Product;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;



public class UserInfoDaoImpl implements UserInfoDao{
	
	private static final String INSERT_STMT = "insert into user_info (user_account, user_password, user_name, user_address, user_phone, user_gender, user_birth, user_ic, user_pic, user_status, garbage_coin, seller_identity) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	private static final String UPDATE_STMT = "update user_info set user_account=? , user_password=?, user_name=?, user_address=?, user_phone=?, user_gender=?, user_birth=?, user_ic=?, user_pic=?, user_status=?, garbage_coin=?, seller_identity=? where user_id=? ";
	private static final String GET_ONE_NAME_STMT = "select user_id, user_account, user_password, user_name, user_address, user_phone, user_gender, user_birth, user_ic, user_pic, user_status, garbage_coin, seller_identity from user_info where user_name like CONCAT('%', ?, '%')";
	private static final String GET_ALL_STMT = "select user_id, user_account, user_password, user_name, user_address, user_phone, user_gender, user_birth, user_ic, user_pic, user_status, garbage_coin, seller_identity from user_info order by user_id ";
	private static final String GET_IMG_BY_ID_STMT = "select user_pic from user_info where user_id = ?";
	
	
	@Override
	public void insert(UserInfo users) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(INSERT_STMT)) {
			ps.setString(1, users.getUser_account());
			ps.setString(2, users.getUser_password());
			ps.setString(3, users.getUser_name());
			ps.setString(4, users.getUser_address());
			ps.setString(5, users.getUser_phone());
			ps.setInt(6, users.getUser_gender());
			ps.setDate(7, users.getUser_birth());
			ps.setString(8, users.getUser_ic());
			ps.setBytes(9, users.getUser_pic());
			ps.setInt(10, users.getUser_status());
			ps.setInt(11, users.getGarbage_coin());
			ps.setInt(12, users.getSeller_identity());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}

	@Override
	public void update(UserInfo users) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(UPDATE_STMT)) {
			ps.setString(1, users.getUser_account());
			ps.setString(2, users.getUser_password());
			ps.setString(3, users.getUser_name());
			ps.setString(4, users.getUser_address());
			ps.setString(5, users.getUser_phone());
			ps.setInt(6, users.getUser_gender());
			ps.setDate(7, users.getUser_birth());
			ps.setString(8, users.getUser_ic());
			ps.setBytes(9, users.getUser_pic());
			ps.setInt(10, users.getUser_status());
			ps.setInt(11, users.getGarbage_coin());
			ps.setInt(12, users.getSeller_identity());
			ps.setInt(13, users.getUser_id());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<UserInfo> findByUserName(String user_name) {
		List<UserInfo> userList = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(GET_ONE_NAME_STMT)) {
			ps.setString(1, user_name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserInfo user = new UserInfo();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_account(rs.getString("user_account"));
				user.setUser_password(rs.getString("user_password"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_address(rs.getString("user_address"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_gender(rs.getInt("user_gender"));
				user.setUser_birth(rs.getDate("user_birth"));
				user.setUser_ic(rs.getString("user_ic"));
				user.setUser_pic(rs.getBytes("user_pic"));
				user.setUser_status(rs.getInt("user_status"));
				user.setGarbage_coin(rs.getInt("garbage_coin"));
				user.setSeller_identity(rs.getInt("seller_identity"));
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;

	}

	@Override
	public List<UserInfo> getAll() {
		List<UserInfo> list = new ArrayList<>();
		UserInfo user = null;
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	            PreparedStatement ps = connection.prepareStatement(GET_ALL_STMT);
	            ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                user = new UserInfo();
	                user.setUser_id(rs.getInt("user_id"));
	                user.setUser_account(rs.getString("user_account"));
	                user.setUser_password(rs.getString("user_password"));
	                user.setUser_name(rs.getString("user_name"));
	                user.setUser_address(rs.getString("user_address"));
	                user.setUser_phone(rs.getString("user_phone"));
	                user.setUser_gender(rs.getInt("user_gender"));
	                user.setUser_birth(rs.getDate("user_birth"));
	                user.setUser_ic(rs.getString("user_ic"));
	                user.setUser_pic(rs.getBytes("user_pic"));
	                user.setUser_status(rs.getInt("user_status"));
	                user.setGarbage_coin(rs.getInt("garbage_coin"));
	                user.setSeller_identity(rs.getInt("seller_identity"));
	                list.add(user);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	}
	
	public UserInfo getUserPicById(Integer user_id) {
		UserInfo userInfo = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(GET_IMG_BY_ID_STMT)) {
               ps.setInt(1, user_id);
               ResultSet rs = ps.executeQuery();
               if (rs.next()) {
                   userInfo = new UserInfo();
                   userInfo.setUser_pic(rs.getBytes("user_pic"));       
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
		
		
		return userInfo;
		
	}
	
	public static void main(String[] args) {
		

	}
	
}
