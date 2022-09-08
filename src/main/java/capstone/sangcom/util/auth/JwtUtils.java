package capstone.sangcom.util.auth;

import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.User;
import capstone.sangcom.entity.UserRole;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import io.jsonwebtoken.*;
import java.security.Key;
import java.util.*;

/**
 * JWT를 활용하기 위한 Util
 * JWT 생성, 정보 분석, 유효 등의 기능 존재
 */
@Slf4j
public class JwtUtils {

    public static final int ACCESS_EXPIRE_TIME = 60 * 60 * 4;

//    public static final int ACCESS_EXPIRE_TIME = 3;
    public static final int REFRESH_EXPIRE_TIME = 60 * 60 * 24 * 14;
    //private static final int REFRESH_EXPIRE_TIME = 60 * 5;

    private static final String secretKey = "ThisIsASecretKeyForSangComThisIsASecretKeyForSangComThisIsASecretKeyForSangCom";

    public static String createAccessToken(User user) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(user.getId())
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setExpiration(createExpireDateForOneYear(ACCESS_EXPIRE_TIME))
                .signWith(createSigningKey(), SignatureAlgorithm.HS256);

        return builder.compact();
    }

    public static String createRefreshToken(User user) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(user.getId())
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setExpiration(createExpireDateForOneYear(REFRESH_EXPIRE_TIME))
                .signWith(createSigningKey(), SignatureAlgorithm.HS256);

        return builder.compact();
    }

    public static boolean isValidToken(String token) {
        try {
            getClaimsFormToken(token);
            
            return true;
        } catch (ExpiredJwtException exception) {
            log.error("Token Expired");
            return false;
        } catch (JwtException exception) {
            log.error("Token Tampered");
            return false;
        } catch (NullPointerException exception) {
            log.error("Token is null");
            return false;
        }
    }

    public static String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }

    private static Date createExpireDateForOneYear(int time) {
        // 토큰 만료시간은 30일으로 설정
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, time);
        return c.getTime();
    }

    private static Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    private static Map<String, Object> createClaims(User user) {
        // 공개 클레임에 사용자의 이름과 이메일을 설정하여 정보를 조회할 수 있다.
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", user.getId());
        claims.put("schoolgrade", user.getSchoolgrade());
        claims.put("schoolclass", user.getSchoolclass());
        claims.put("schoolnumber", user.getSchoolnumber());
        claims.put("role", user.getRole());
        claims.put("year", user.getYear());
        claims.put("name", user.getName());

        return claims;
    }

    private static Key createSigningKey() {
        byte[] apiKeySecretBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(apiKeySecretBytes);
    }

    private static Claims getClaimsFormToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Decoders.BASE64.decode(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static JwtUser getUserFromToken(String token) {
        Claims claims = getClaimsFormToken(token);

        return new JwtUser(
                (String) claims.get("id"),
                (Integer) claims.get("schoolgrade"),
                (Integer) claims.get("schoolclass"),
                (Integer) claims.get("schoolnumber"),
                UserRole.valueOf((String) claims.get("role")),
                (Integer) claims.get("year"),
                (String) claims.get("name")
        );
    }

}
