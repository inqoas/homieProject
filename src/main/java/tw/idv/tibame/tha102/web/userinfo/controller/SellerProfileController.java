package tw.idv.tibame.tha102.web.userinfo.controller;


import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Part;
import tw.idv.tibame.tha102.core.util.UserInfoJwtUtil;
import tw.idv.tibame.tha102.web.userinfo.service.SellerService;
import tw.idv.tibame.tha102.web.userinfo.vo.Seller;

@RestController
@RequestMapping("/sellerProfile")
public class SellerProfileController {
	@Autowired
	private UserInfoJwtUtil userInfoJwtUtil;
	@Autowired
	private SellerService sellerService;
	
	@GetMapping("/getSellerProfile")
	public Seller getSellerProfile(@RequestHeader("Authorization") String authorization) {
		int userId = userInfoJwtUtil.checkUserInfoJwt(authorization);
		Seller seller = new Seller();
		if(userId == 0) {
			seller.setSuccess(false);
			seller.setMessage("帳號驗證失敗，請重新登入");
			return seller;
		}
		seller = sellerService.selectSeller(userId);
		byte[] sellerPcrc = seller.getSellerPcrc();
		if(sellerPcrc != null) {
			seller.setSellerPcrc(null);
			seller.setSellerPcrcBase64(Base64.getEncoder().encodeToString(sellerPcrc));
		}
		return seller;
	}
	
		@PostMapping("/insertSeller")
		public Seller insertSeller(@RequestHeader("Authorization") String jwtString, 
				@RequestPart("sellerPcrc") Part sellerPcrc,
				@RequestParam("bankCode") int bankCode,
				@RequestParam("bankAccount") String bankAccount,
				@RequestParam("bankHolderName") String bankHolderName) throws IOException {
			int userid = userInfoJwtUtil.checkUserInfoJwt(jwtString);
			byte[] pic = sellerPcrc.getInputStream().readAllBytes();
			Seller seller = new Seller();
			seller.setBankCode(bankCode);
			seller.setBankAccount(bankAccount);
			seller.setBankHolderName(bankHolderName);
			seller.setSellerPcrc(pic);
			if(userid == 0) {
				seller.setMessage("驗證失敗，請重新登入");
				seller.setSuccess(false);
				return seller;
			}
			return sellerService.insertSeller(seller, userid);
		}
}
