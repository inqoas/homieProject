package tw.idv.tibame.tha102.web.userinfo.service.impl;

import java.util.List;

import tw.idv.tibame.tha102.web.userinfo.dao.UserInfoDao;
import tw.idv.tibame.tha102.web.userinfo.dao.UserInfoDaoImpl;
import tw.idv.tibame.tha102.web.userinfo.service.UserInfoService;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;



public class UserInfoServiceImpl implements UserInfoService {
	
	private UserInfoDao dao;
	
	public UserInfoServiceImpl() {
		dao = new UserInfoDaoImpl();
	}

	@Override
	public List<UserInfo> findAll() {
		return dao.getAll();
	}
	
	@Override
	public UserInfo updateUser(UserInfo user) {
		dao.update(user);
		return user;
	}

	@Override
	public UserInfo newUser(UserInfo user) {
		dao.insert(user);
		return user;
	}

	@Override
	public List<UserInfo> findUserByName(String userName) {
		return dao.findByUserName(userName);
	}

	@Override
	public List<UserInfo> findUserById(Integer userId) {
		return dao.findByUserId(userId);
	}

	@Override
	public UserInfo findAddressById(Integer userId) {
		return dao.getAddressById(userId);
	}

	@Override
	public Integer sellerPass(Integer userId) {
		return dao.updateStatusByIdPass(userId);
	}

	@Override
	public Integer sellerFail(Integer userId) {
		return dao.updateStatusByIdFail(userId);
	}

	@Override
	public List<UserInfo> findPendingSeller() {
		return dao.findByUserStatus();
	}
}
