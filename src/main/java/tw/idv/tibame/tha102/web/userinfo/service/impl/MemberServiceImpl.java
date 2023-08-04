package tw.idv.tibame.tha102.web.userinfo.service.impl;

import java.lang.reflect.Member;

import tw.idv.tibame.tha102.web.userinfo.dao.MemberDao;
import tw.idv.tibame.tha102.web.userinfo.dao.UserInfoDao;
import tw.idv.tibame.tha102.web.userinfo.dao.UserInfoDaoImpl;
import tw.idv.tibame.tha102.web.userinfo.dao.Impl.MemberDaoImpl;
import tw.idv.tibame.tha102.web.userinfo.service.MemberService;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;



public class MemberServiceImpl implements MemberService{
	
	private MemberDao memberDaoImpl = null;
	public MemberServiceImpl() {
		memberDaoImpl = new MemberDaoImpl();
	}
	
	@Override
	public UserInfo register(UserInfo userInfo) {
		if(userInfo.getUser_account() == null || userInfo.getUser_account().isBlank()) {
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
		
		if(userInfo.getUser_password() == null || userInfo.getUser_password().isBlank()) {
			userInfo.setMessage("請輸入密碼");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(!userInfo.getUser_password().matches("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9]{8,20}$")) {
			userInfo.setMessage("密碼為 8 到 20 個字元的英文和數字混合");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(userInfo.getUser_name() == null || userInfo.getUser_name().isBlank()) {
			userInfo.setMessage("請輸入姓名");
			userInfo.setSuccess(false);
			return userInfo;
		}
		String useric = userInfo.getUser_ic();
		if(useric == null || useric.isBlank()) {
			userInfo.setMessage("請輸入身分證字號");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(!useric.matches("[a-zA-Z]\\d{9}")) {
			userInfo.setMessage("身份證字號應該是1個英文字母接著9個數字!");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(userInfo.getUser_phone() == null || userInfo.getUser_phone().isBlank()) {
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
		if(userInfo.getUser_address() == null || userInfo.getUser_address().isBlank()) {
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
		if(userAccount == null || userAccount.isBlank()) {
			userInfo.setMessage("請輸入帳號");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(userPassword == null || userPassword.isBlank()) {
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

	@Override
	public UserInfo header(int user_id) {
		UserInfo userInfo = new UserInfo();
		userInfo = memberDaoImpl.selectByUserId(user_id);
		UserInfo userInfo2 = new UserInfo();
		if(userInfo == null) {
			userInfo2.setSuccess(false);
			return userInfo;
		}
		userInfo2.setUser_id(userInfo.getUser_id());
		userInfo2.setUser_name(userInfo.getUser_name());
		userInfo2.setSeller_identity(userInfo.getSeller_identity());
		userInfo2.setSuccess(true);
		
		return userInfo2;
	}

	@Override
	public UserInfo changeUserInfo(UserInfo userInfo) {
		if(userInfo.getUser_name() == null || userInfo.getUser_name().isBlank()) {
			userInfo.setMessage("請輸入姓名");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(userInfo.getUser_phone() == null || userInfo.getUser_phone().isBlank()) {
			userInfo.setMessage("請輸入電話");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(!userInfo.getUser_phone().matches("09\\d{8}")) {
			userInfo.setMessage("電話格式輸入錯誤");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(userInfo.getUser_address() == null || userInfo.getUser_address().isBlank()) {
			userInfo.setMessage("請輸入地址");
			userInfo.setSuccess(false);
			return userInfo;
		}
		int success = memberDaoImpl.updateUserinfo(userInfo);
		if(success < 1) {
			userInfo.setMessage("修改失敗");
			userInfo.setSuccess(false);
		}
		userInfo.setMessage("修改成功");
		userInfo.setSuccess(true);
		return userInfo;
	}

	@Override
	public UserInfo changeUserPassword(UserInfo userInfo, int userid) {
		if(userInfo.getUser_password() == null || userInfo.getUser_password().isBlank()) {
			userInfo.setMessage("請輸入密碼");
			userInfo.setSuccess(false);
			return userInfo;
		}
		if(!userInfo.getUser_password().matches("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9]{8,20}$")) {
			userInfo.setMessage("密碼為 8 到 20 個字元的英文和數字混合");
			userInfo.setSuccess(false);
			return userInfo;
		}
		UserInfo checkUserInfo = memberDaoImpl.selectByUserId(userid);
		int success = 0;
		if(!userInfo.getMessage().equals(checkUserInfo.getUser_password())) {
			userInfo.setMessage("舊密碼錯誤，請確認後重新輸入");
			return userInfo;
		}else {
			success = memberDaoImpl.updatePassword(userInfo, userid);
		}
		if(success < 1) {
			userInfo.setMessage("更改失敗");
			userInfo.setSuccess(false);
			return userInfo;
		}
		userInfo.setMessage("更改成功");
		userInfo.setSuccess(true);
		return userInfo;
	}

	@Override
	public UserInfo changeUserPic(UserInfo userInfo, int userid) {
		if(userid == 0) {
			userInfo.setSuccess(false);
			userInfo.setMessage("帳號驗證錯誤，請重新登入");
			return userInfo;
		}
		int count =  memberDaoImpl.updateUserpic(userInfo, userid);
		if(count < 1) {
			userInfo.setSuccess(false);
			userInfo.setMessage("更改圖片失敗");
			return userInfo;
		}
		userInfo.setSuccess(true);
		return userInfo;
	}
}
