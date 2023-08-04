package tw.idv.tibame.tha102.web.userinfo.dao.Impl;

import tw.idv.tibame.tha102.web.userinfo.dao.MemberDao;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;
import static tw.idv.tibame.tha102.core.util.CommonMysql.PASSWORD;
import static tw.idv.tibame.tha102.core.util.CommonMysql.URL;
import static tw.idv.tibame.tha102.core.util.CommonMysql.USER;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;
@Repository
public class MemberDaoImpl implements MemberDao{
	
	
	@Override
	public int insert(UserInfo userInfo) {
		String sql = """
				insert into user_info (user_account, user_password, user_name, user_address, user_phone, user_gender, user_birth, user_ic) 
				values (?, ?, ?, ?, ?, ?, ?, ?)
				""";
		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			preparedStatement.setString(1, userInfo.getUser_account());
			preparedStatement.setString(2, userInfo.getUser_password());
			preparedStatement.setString(3, userInfo.getUser_name());
			preparedStatement.setString(4, userInfo.getUser_address());
			preparedStatement.setString(5, userInfo.getUser_phone());
			preparedStatement.setInt(6, userInfo.getUser_gender());
			preparedStatement.setDate(7, userInfo.getUser_birth());
			preparedStatement.setString(8, userInfo.getUser_ic());
			return preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int updateUserinfo(UserInfo userInfo) {
		String sql = """
				update user_info 
					set user_name=?, user_address=?, user_phone=?
					where user_id=?
				""";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			preparedStatement.setString(1, userInfo.getUser_name());
			preparedStatement.setString(2, userInfo.getUser_address());
			preparedStatement.setString(3, userInfo.getUser_phone());
			preparedStatement.setInt(4, userInfo.getUser_id());
			return preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public UserInfo selectByLogin(String userAccount, String password) {
		String sql = """
				select * from user_info
					where user_account = ? and user_password = ?;
				""";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			preparedStatement.setString(1, userAccount);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setUser_id(resultSet.getInt("user_id"));
				userInfo.setUser_account(resultSet.getString("user_account"));
				userInfo.setUser_password(resultSet.getString("user_password"));
				userInfo.setUser_name(resultSet.getString("user_name"));
				userInfo.setUser_address(resultSet.getString("user_address"));
				userInfo.setUser_phone(resultSet.getString("user_phone"));
				userInfo.setUser_gender(resultSet.getInt("user_gender"));
				userInfo.setUser_birth(resultSet.getDate("user_birth"));
				userInfo.setUser_ic(resultSet.getString("user_ic"));
				userInfo.setUser_pic(resultSet.getBytes("user_pic"));
				userInfo.setUser_status(resultSet.getInt("user_status"));
				userInfo.setGarbage_coin(resultSet.getInt("garbage_coin"));
				userInfo.setSeller_identity(resultSet.getInt("seller_identity"));
				return userInfo;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public UserInfo selectByUserAccount(String userAccount) {
		String sql = """
				select * from user_info
					where user_account = ?;
				""";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			preparedStatement.setString(1, userAccount);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setUser_id(resultSet.getInt("user_id"));
				userInfo.setUser_account(resultSet.getString("user_account"));
				userInfo.setUser_password(resultSet.getString("user_password"));
				userInfo.setUser_name(resultSet.getString("user_name"));
				userInfo.setUser_address(resultSet.getString("user_address"));
				userInfo.setUser_phone(resultSet.getString("user_phone"));
				userInfo.setUser_gender(resultSet.getInt("user_gender"));
				userInfo.setUser_birth(resultSet.getDate("user_birth"));
				userInfo.setUser_ic(resultSet.getString("user_ic"));
				userInfo.setUser_pic(resultSet.getBytes("user_pic"));
				userInfo.setUser_status(resultSet.getInt("user_status"));
				userInfo.setGarbage_coin(resultSet.getInt("garbage_coin"));
				userInfo.setSeller_identity(resultSet.getInt("seller_identity"));
				return userInfo;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public UserInfo selectByUserId(Integer userid) {
		String sql = """
				select * from user_info
					where user_id = ?;
				""";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			preparedStatement.setInt(1, userid);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setUser_id(resultSet.getInt("user_id"));
				userInfo.setUser_account(resultSet.getString("user_account"));
				userInfo.setUser_password(resultSet.getString("user_password"));
				userInfo.setUser_name(resultSet.getString("user_name"));
				userInfo.setUser_address(resultSet.getString("user_address"));
				userInfo.setUser_phone(resultSet.getString("user_phone"));
				userInfo.setUser_gender(resultSet.getInt("user_gender"));
				userInfo.setUser_birth(resultSet.getDate("user_birth"));
				userInfo.setUser_ic(resultSet.getString("user_ic"));
				userInfo.setUser_pic(resultSet.getBytes("user_pic"));
				userInfo.setUser_status(resultSet.getInt("user_status"));
				userInfo.setGarbage_coin(resultSet.getInt("garbage_coin"));
				userInfo.setSeller_identity(resultSet.getInt("seller_identity"));
				return userInfo;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updatePassword(UserInfo userInfo, int userid) {
		String sql = """
				update user_info 
					set user_password=?
					where user_id=?
				""";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			preparedStatement.setString(1, userInfo.getUser_password());
			preparedStatement.setInt(2, userid);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int updateUserpic(UserInfo userInfo, int userid) {
		String sql = """
				update user_info 
					set user_pic=?
					where user_id=?
				""";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			preparedStatement.setBytes(1, userInfo.getUser_pic());
			preparedStatement.setInt(2, userid);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			return -1;
		}
	}

	public int updateSellerIdentityByUserId(int sellerIdentity, int userid) {
	    String sql = "UPDATE user_info SET seller_identity = ? WHERE user_id = ?";
	    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, sellerIdentity);
	        preparedStatement.setInt(2, userid);
	        return preparedStatement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -1;
	    }
	}


	
}
