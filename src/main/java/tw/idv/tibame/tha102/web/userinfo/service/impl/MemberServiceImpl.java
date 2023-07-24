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
			userInfo.setSuccess(false);
			return userInfo;
		}
		String emailCheck = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		if(!userInfo.getUser_account().matches(emailCheck)) {
			userInfo.setMessage("帳號要為信箱");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(!(memberDaoImpl.selectByUserAccount(userInfo.getUser_account()) == null)) {
			userInfo.setMessage("帳號重複");
			userInfo.setSuccess(false);
			return userInfo;
		}
		
		if(userInfo.getUser_password() == null) {
			userInfo.setMessage("請輸入密碼");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(!userInfo.getUser_password().matches("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9]{8,20}$")) {
			userInfo.setMessage("密碼為 8 到 20 個字元的英文和數字混合");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(userInfo.getUser_name() == null) {
			userInfo.setMessage("請輸入姓名");
			userInfo.setSuccess(false);
			return userInfo;
		}
		String useric = userInfo.getUser_ic();
		if(useric == null) {
			userInfo.setMessage("請輸入身分證字號");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(!useric.matches("[a-zA-Z]\\d{9}")) {
			userInfo.setMessage("身份證字號應該是1個英文字母接著9個數字!");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(userInfo.getUser_phone() == null) {
			userInfo.setMessage("請輸入電話");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(!userInfo.getUser_phone().matches("09\\d{8}")) {
			userInfo.setMessage("電話格式輸入錯誤");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(userInfo.getUser_birth() == null) {
			userInfo.setMessage("請輸入生日");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(userInfo.getUser_gender() == null) {
			userInfo.setMessage("請輸入性別");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(userInfo.getUser_address() == null) {
			userInfo.setMessage("請輸入地址");
			userInfo.setSuccess(false);
			return userInfo;
		}
		final int result = memberDaoImpl.insert(userInfo);
		if(result < 1) {
			userInfo.setMessage("註冊失敗，請聯繫管理員");
			userInfo.setSuccess(false);
			return userInfo;
		}
		userInfo.setMessage("註冊成功");
		userInfo.setSuccess(true);
		return userInfo;
	}
	@Override
	public UserInfo login(UserInfo userInfo) {
		final String userAccount = userInfo.getUser_account();
		final String userPassword = userInfo.getUser_password();
		if(userAccount == null) {
			userInfo.setMessage("請輸入帳號");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(userPassword == null) {
			userInfo.setMessage("請輸入密碼");
			userInfo.setSuccess(false);
			return userInfo;
		}
		userInfo = memberDaoImpl.selectByLogin(userAccount, userPassword);
		if(userInfo == null) {
			userInfo = new UserInfo();
			userInfo.setMessage("帳號或密碼錯誤");
			userInfo.setSuccess(false);
			return userInfo;
		}
		userInfo.setMessage("登入成功");
		userInfo.setSuccess(true);
		return userInfo;
	}
	

}
