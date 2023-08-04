package tw.idv.tibame.tha102.core.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;
@Component
public class UserInfoJwtUtil {
	private JwtSecretKey jKey;
	@Autowired
	public UserInfoJwtUtil(JwtSecretKey jKey) {
		this.jKey = jKey;
	}
	
	public String creatUserInfoJwt(UserInfo userInfo) {
		//設定過期時間
		long expirationTimeMills = 1000 * 60 * 60 * 6;
		Date now =new Date();
		Date expirationTime = new Date(now.getTime() + expirationTimeMills);
		String userInfoJwt = Jwts.builder()
								 .claim("user_id", userInfo.getUser_id())
								 .setExpiration(expirationTime)
								 .signWith(jKey.getKey())
								 .compact();
		
		return userInfoJwt;
	}
	public int checkUserInfoJwt(String userInfoJwt) {
	    try {
	        // 檢查 Authorization 標頭是否存在且符合 Bearer 方式
	        if (userInfoJwt == null || !userInfoJwt.startsWith("Bearer ")) {
	            return 0;
	        }

	        // 提取 JWT 字串，去除 "Bearer " 部分
	        String jwtToken = userInfoJwt.substring(7);

	        // 解析 JWT
	        JwtParserBuilder jBuilder = Jwts.parserBuilder();
	        jBuilder.setSigningKey(jKey.getKey());
	        JwtParser parser = jBuilder.build();
	        Jws<Claims> jws = parser.parseClaimsJws(jwtToken);
	        Claims claims = jws.getBody();

	        // 檢查 JWT 是否有效，例如檢查過期時間等
	        Date expirationTime = jws.getBody().getExpiration();
	        Date now = new Date();
	        if (expirationTime.before(now)) {
	            // JWT 已過期，返回 null 或其他錯誤處理
	            return 0;
	        }

	        
	       return (Integer) claims.get("user_id");
	       

	    } catch (Exception e) {
//	        e.printStackTrace();
	        return 0;
	    }
	}

		
}
