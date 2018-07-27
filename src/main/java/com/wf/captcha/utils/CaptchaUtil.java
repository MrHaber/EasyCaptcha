package com.wf.captcha.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wf.captcha.Captcha;
import com.wf.captcha.GifCaptcha;

/**
 * 图形验证码工具类
 * Created by 王帆 on 2018-07-27 上午 10:08.
 */
public class CaptchaUtil {
    private static final String SESSION_KEY = "captcha";

    /**
     * 验证验证码
     */
    public static boolean ver(String code, HttpServletRequest request) {
        if (code != null && !code.trim().isEmpty()) {
            String captcha = (String) request.getSession().getAttribute(SESSION_KEY);
            return code.trim().toLowerCase().equals(captcha);
        }
        return false;
    }

    /**
     * 输出验证码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public static void out(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        out(130, 38, 5, request, response);
    }

    /**
     * 输出验证码
     *
     * @param len      长度
     * @param request
     * @param response
     * @throws IOException
     */
    public static void out(int len, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        out(130, 38, len, request, response);
    }

    /**
     * 输出验证码
     *
     * @param width    宽度
     * @param height   高度
     * @param len      长度
     * @param request
     * @param response
     * @throws IOException
     */
    public static void out(int width, int height, int len, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        setHeader(response);
        Captcha captcha = new GifCaptcha(130, 38, 5);
        request.getSession().setAttribute(SESSION_KEY, captcha.text().toLowerCase());
        captcha.out(response.getOutputStream());
    }

    /**
     * 设置相应头
     *
     * @param response
     */
    private static void setHeader(HttpServletResponse response) {
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }

}
