package com.example.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomInterceptor implements HandlerInterceptor {

   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      // 컨트롤러 실행 전 로직
      System.out.println("CustomInterceptor - Before Controller");
      return true; // true: 다음 단계로 진행, false: 요청 중단
   }

   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
      // 컨트롤러 실행 후, 뷰 렌더링 전 로직
      System.out.println("CustomInterceptor - After Controller");
   }

   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
      // 뷰 렌더링 이후 로직
      System.out.println("CustomInterceptor - After View Rendering");
   }
}