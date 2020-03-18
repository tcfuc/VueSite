package com.ccsu.zy.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);
    //每次打开此类只初始化一次countMap
    private static Map<String, Object> countMap = new ConcurrentHashMap<String,Object>();
	
	
	/**
	 * 过期时间15分钟
	 */
	private static final long EXPIRE_TIME = 60 * 60 * 1000;
	/**
	 * token私钥
	 */
	private static final String TOKEN_SECRET = "93504b34627d44f69814294eb0db86da";
	
	//生成token
	public static String sign(String login_code,String user_code) {
		try {
			logger.info("生成token");
			//过期时间
			Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			//私钥及加密算法
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			//设置头部信息
			Map<String, Object> header = new HashMap<>(2);
			header.put("typ", "JWT");
			header.put("alg", "HS256");
			return JWT.create()
					.withHeader(header)
					.withClaim("login_code", login_code)
					.withClaim("user_code", user_code)
					.withExpiresAt(date)
					.sign(algorithm);
		} catch (UnsupportedEncodingException e){
			return null;
		}
	}
	
	//验证token
	public static boolean verify(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			JWTVerifier verifier = JWT.require(algorithm)
					.build();
			DecodedJWT jwt = verifier.verify(token);
			JWTUtil.insertToken(token);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}
	
	//获取登录账号
	public static String getLogin_code(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("login_code").asString();
		} catch (JWTDecodeException exception) {
			return null;
		}
	}
	
	//获取登录用户编码
	public static String getUser_code(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("user_code").asString();
		} catch (JWTDecodeException exception) {
			return null;
		}
	}
	
    /**
     * @auther: Arong
     * @description: 解析token并且将数据插入CountMap中
     * @param token
     * @return: void
     * @date: 2019/1/22 17:44
     */
    public static void insertToken(String token){
        //获得当前时间(毫秒)
        long currentTime = System.currentTimeMillis();
        //解析token，获得签发时间
        DecodedJWT jwt = JWT.decode(token);
        String login_code = JWTUtil.getLogin_code(token);
        //以签发时间为key。当前时间+60s为value存入countMap中
        countMap.put(login_code,currentTime+60*60*1000);
    }
	
    /**
     * @auther: Arong
     * @description: 获取当前在线用户数
     * @param
     * @return: java.lang.Integer
     * @date: 2019/1/22 17:51
     */
    public static Integer getOnlineCount(){
        int onlineCount = 0;
        //获取countMap的迭代器
        Iterator iterator = countMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,Object>  entry = (Map.Entry<String, Object>) iterator.next();
            Long value = (Long) entry.getValue();
            if (value > System.currentTimeMillis()) {
                //过期时间大于当前时间则没有过期
                onlineCount++;
            }
        }
        return onlineCount;
    }
	
	
}
