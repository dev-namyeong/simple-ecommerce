package com.example.demo.common;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(IllegalArgumentException e, RedirectAttributes redirectAttributes, HttpServletRequest request){
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        // 요청한 주소 기준으로 다시 보내기
        return "redirect:" + request.getHeader("Referer");
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, RedirectAttributes redirectAttributes, HttpServletRequest request){
        redirectAttributes.addFlashAttribute("error", "알 수 없는 오류가 발생했습니다.");
        return "redirect:" + request.getHeader("Referer");
    }
}
