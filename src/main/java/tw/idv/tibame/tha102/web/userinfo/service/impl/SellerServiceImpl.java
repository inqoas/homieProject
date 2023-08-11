package tw.idv.tibame.tha102.web.userinfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

import tw.idv.tibame.tha102.web.userinfo.dao.MemberDao;
import tw.idv.tibame.tha102.web.userinfo.dao.SellerDao;
import tw.idv.tibame.tha102.web.userinfo.service.SellerService;
import tw.idv.tibame.tha102.web.userinfo.vo.Seller;
@Service
@Transactional
public class SellerServiceImpl implements SellerService{
	@Autowired
	private SellerDao sellerDao;
	@Autowired
	private MemberDao memberDao;
	public Seller selectSeller(int userid) {
		Seller seller = new Seller();
		if(userid == 0) {
			seller.setSuccess(false);
			seller.setMessage("帳號驗證錯誤，請重新登入");
			return seller;
		}
		seller = sellerDao.selectSellerById(userid);
		return seller;
	}

	@Override
	public Seller insertSeller(Seller seller, int userid) {
		seller.setUserId(userid);
		if(seller.getSellerPcrc() == null) {
			seller.setMessage("請放入良民證圖");
			seller.setSuccess(false);
			return seller;
		}
		if (seller.getBankCode() == null || !String.valueOf(seller.getBankCode()).matches("^\\d{3}$")) {
	        seller.setMessage("請輸入有效的銀行代碼（3個數字）");
	        seller.setSuccess(false);
	        return seller;
	    }

	    if (seller.getBankAccount() == null || !seller.getBankAccount().matches("^\\d{12}$")) {
	        seller.setMessage("請輸入有效的銀行帳號（12個數字）");
	        seller.setSuccess(false);
	        return seller;
	    }

	    if (seller.getBankHolderName() == null || seller.getBankHolderName().isBlank()) {
	        seller.setMessage("請輸入有效的銀行姓名");
	        seller.setSuccess(false);
	        return seller;
	    }
	    seller.setTotalReviewCount(0);
	    seller.setTotalReviewStars(0);
		int checkCount = sellerDao.insertSeller(seller);
		if(checkCount < 1) {
			seller.setMessage("申請失敗，請聯繫客服");
			seller.setSuccess(false);
			return seller;
		}
		//更改使用者的商家狀態
		checkCount = memberDao.updateUserStatusByUserId(2, userid);
		if(checkCount < 1) {
			seller.setMessage("申請失敗，請聯繫客服");
			seller.setSuccess(false);
			return seller;
		}
		seller.setMessage("申請成功");
		seller.setSuccess(true);
		return seller;
	}

}
