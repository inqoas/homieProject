package tw.idv.tibame.tha102.web.userinfo.service.impl;

import java.lang.reflect.Member;

import tw.idv.tibame.tha102.web.userinfo.dao.MemberDao;
import tw.idv.tibame.tha102.web.userinfo.dao.MemberDaoImpl;
import tw.idv.tibame.tha102.web.userinfo.dao.UserInfoDao;
import tw.idv.tibame.tha102.web.userinfo.dao.UserInfoDaoImpl;
import tw.idv.tibame.tha102.web.userinfo.service.MemberService;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;



public class MemberServiceImpl implements MemberService{
	
	private MemberDao memberDaoImpl = null;
	public MemberServiceImpl() {
		memberDaoImpl = new MemberDaoImpl();
	}
	
	@Override
	public UserInfo register(UserInfo userInfo) {
		if(userInfo.getUser_account() == null) {
			userInfo.setMessage("請輸入帳號");
			return userInfo;
		}
		if(userInfo.getUser_password() == null) {
			userInfo.setMessage("請輸入密碼");
			return userInfo;
		}
		if(userInfo.getUser_name() == null) {
			userInfo.setMessage("請輸入姓名");
			return userInfo;
		}
		if(userInfo.getUser_ic() == null) {
			userInfo.setMessage("請輸入身分證字號");
			return userInfo;
		}
		if(userInfo.getUser_phone() == null) {
			userInfo.setMessage("請輸入電話");
			return userInfo;
		}
		if(userInfo.getUser_birth() == null) {
			userInfo.setMessage("請輸入生日");
			return userInfo;
		}
		if(userInfo.getUser_gender() == null) {
			userInfo.setMessage("請輸入性別");
			return userInfo;
		}
		if(userInfo.getUser_address() == null) {
			userInfo.setMessage("請輸入地址");
			return userInfo;
		}
		final int result = memberDaoImpl.insert(userInfo);
		if(result < 1) {
			userInfo.setMessage("註冊失敗，請聯繫管理員");
			return userInfo;
		}
		userInfo.setMessage("註冊成功");
		return userInfo;
	}
	@Override
	public UserInfo login(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
