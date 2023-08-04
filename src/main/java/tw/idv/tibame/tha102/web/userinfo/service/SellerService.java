package tw.idv.tibame.tha102.web.userinfo.service;

import tw.idv.tibame.tha102.web.userinfo.vo.Seller;

public interface SellerService {
	public Seller selectSeller(int userid);
	public Seller insertSeller(Seller seller, int userid);
}
