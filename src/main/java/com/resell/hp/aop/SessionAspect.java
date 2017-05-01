package com.resell.hp.aop;


import java.lang.reflect.Method;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.resell.hp.annotation.SignInRequired;

@Aspect
@Component
@Order(3)
public class SessionAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionAspect.class);
	@Around("@annotation(com.resell.hp.annotation.SignInRequired)")
	public Object checkSignedIn(ProceedingJoinPoint joinPoint) throws Throwable {
      ServletRequestAttributes requestAttributes = 
            (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
      HttpSession session = requestAttributes.getRequest().getSession();
     
      
      if (session.getAttribute("signedIn") == null ||
            !((Boolean) session.getAttribute("signedIn"))) {
         throw new RuntimeException("로그인이 필요합니다.");
      }
      
      MethodSignature methodSignature=(MethodSignature)joinPoint.getSignature();
      SignInRequired signInRequired = methodSignature.getMethod().getAnnotation(SignInRequired.class);
      
      String signInValue = signInRequired.value();
      
      if ("ADMIN".equals(signInValue)) {
          if (!("admin".equals(session.getAttribute("userRank")))) {
               throw new RuntimeException("관리자 권한이 필요합니다.");
            }
      }
      
      return joinPoint.proceed();
   }
   
}
