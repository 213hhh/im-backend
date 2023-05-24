package com.wxy.imback.interceptor;

import com.wxy.imback.constant.BizCodeEnum;
import com.wxy.imback.model.LoginUser;
import com.wxy.imback.utils.CommonUtil;
import com.wxy.imback.utils.JWTUtil;
import com.wxy.imback.utils.Result;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */


@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    public static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //方法返回指定名称的请求头的值
        String token = request.getHeader("token");
        if (token == null) {
            //方法返回指定名称的请求参数的值
            token = request.getParameter("token");
        }
        if (StringUtils.isNotBlank(token)) {
            Claims claims = JWTUtil.checkJWT(token);
            if (claims == null) {
                CommonUtil.sendJsonMessage(response, Result.error(BizCodeEnum.ACCOUNT_UNLOGIN));
                return false;
            }
            Long id = Long.parseLong(claims.get("user_id").toString());
            String mail = (String) claims.get("email");
            String userName = (String) claims.get("user_name");
            String mobile = (String) claims.get("mobile");
            claims.getExpiration();
            LoginUser loginUser = new LoginUser();
            loginUser.setUserId(id);
            loginUser.setEmail(mail);
            loginUser.setMobile(mobile);
            loginUser.setUserName(userName);
            //request.setAttribute("loginUser",loginUser);
            //传递用户信息
            threadLocal.set(loginUser);
            return true;
        }
        CommonUtil.sendJsonMessage(response, Result.error(BizCodeEnum.TOKEN_IS_NULL));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}


