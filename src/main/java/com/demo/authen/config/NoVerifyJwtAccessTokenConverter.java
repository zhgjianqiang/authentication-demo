package com.demo.authen.config;

import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

public class NoVerifyJwtAccessTokenConverter extends JwtAccessTokenConverter {

    private JsonParser objectMapper = JsonParserFactory.create();

    @Override
    protected Map<String, Object> decode(String token) {
        try {
            //这里不校验token的签名, 直接解析即可
            Jwt jwt = JwtHelper.decode(token);
            String claimsStr = jwt.getClaims();
            Map<String, Object> claims = objectMapper.parseMap(claimsStr);
            if (claims.containsKey(EXP) && claims.get(EXP) instanceof Integer) {
                Integer intValue = (Integer) claims.get(EXP);
                claims.put(EXP, new Long(intValue));
            }
            this.getJwtClaimsSetVerifier().verify(claims);
            return claims;
        }
        catch (Exception e) {
            throw new InvalidTokenException("Cannot convert access token to JSON", e);
        }
    }
}
