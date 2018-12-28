package com.sunkang.shiro;

import com.sunkang.service.RedisService;
import com.sunkang.shiro.token.AcessToken;
import com.sunkang.shiro.token.JWTToken;
import com.sunkang.shiro.token.JWTUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * shiro验证
 */
public class ServerShiroRealm extends AuthorizingRealm {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //先写死，后期改成从数据库和缓存中
        String token = (String)authenticationToken.getCredentials();
        if(token==null||token.length()==0){
            throw new AuthenticationException("4000");
        }
        //先从redis缓存中取出对比
        if(redisService==null){
            redisService=new RedisService();
        }
        boolean hasToken= redisService.existsToken("token",token);
        if(hasToken){
            return new SimpleAuthenticationInfo(token,token, "ServerShiroRealm");
        }
        AcessToken at=null;
        try {
            at=JWTUtils.parseJwtToken(token);
        }catch (ExpiredJwtException e1){//jwt超时解析异常
            throw new AuthenticationException("4003");
        }
        catch (Exception e){
            throw new AuthenticationException("4001");//非法token
        }
        if(!"sunkang".equals(at.getUsername())||!"kang".equals(at.getPassword())){
            throw new AuthenticationException("4002");
        }
        if(System.currentTimeMillis()>at.getExpiresIn()){//超时
            throw new AuthenticationException("4003");
        }
        if(!"sunkangClient".equals(at.getTokenType())){//非法请求
            throw new AuthenticationException("4004");
        }
        return new SimpleAuthenticationInfo(token,token, "ServerShiroRealm");
    }
}
