package tw.idv.tibame.tha102.web.userinfo.service;

import java.util.List;

import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;



public interface UserInfoService {
	public List<UserInfo> findAll();
	public UserInfo updateUser(UserInfo user);
	public UserInfo newUser(UserInfo user);
	public List<UserInfo> findUserByName(String userName);
}
