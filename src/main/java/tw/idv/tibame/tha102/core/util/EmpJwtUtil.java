package tw.idv.tibame.tha102.core.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tw.idv.tibame.tha102.web.emp.vo.Emp;

import java.util.Date;

@Component
public class EmpJwtUtil {
    private JwtSecretKey jKey;
    @Autowired
    public EmpJwtUtil(JwtSecretKey jKey) {
        this.jKey = jKey;
    }

    public String createEmpJwt(Emp emp) {
        // 設定過期時間
        long expirationTimeMills = 1000 * 60 * 60 * 6; // 例如，6小時
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + expirationTimeMills);
        String empJwt = Jwts.builder()
                .claim("emp_id", emp.getEmpId())
                .setExpiration(expirationTime)
                .signWith(jKey.getKey())
                .compact();

        return empJwt;
    }

    public Integer checkEmpJwt(String empJwt) {
        try {
            // 檢查 Authorization 標頭是否存在且符合 Bearer 方式
            if (empJwt == null || !empJwt.startsWith("Bearer ")) {
                return null; // 返回 null 或其他表示無效的值
            }

            // 提取 JWT 字串，去除 "Bearer " 部分
            String jwtToken = empJwt.substring(7);

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
                return null;
            }

            // 如果JWT有效，則返回其中存儲的員工ID
            return (Integer) claims.get("emp_id");

        } catch (Exception e) {
            // 處理解析或驗證過程中的異常
            return null; // 或者你可以返回其他表示錯誤的值
        }
    }

}

