package com.hisense.hiutbd.platform.exceptionresolver;


import com.hisense.hiutbd.platform.config.ReplyVO;
import com.hisense.hiutbd.platform.utils.AjaxUtils;
import com.hisense.hiutbd.platform.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理
 * <pre>
 * 统一处理系统Controller层抛出的异常。
 * 
 * @author  xo
 * @version 2.0 2012-05-05
 */
public class CommonHandlerExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(CommonHandlerExceptionResolver.class);

    /**
     * 处理异常
     * 
     * @param  request HttpServletRequest
     * @param  response HttpServletResponse
     * @param  handler 
     * @param  e 系统抛出的异常 
     * 
     * @return ModelAndView spring的ModelAndView
     */
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {

        logger.error("系统异常！", e);

        if(AjaxUtils.isAjaxRequest(request)) {
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                
                PrintWriter out = response.getWriter();
                out.print(JsonUtils.writeValueAsString(new ReplyVO(1, "系统出错了，请联系开发人员。")));
                out.flush();
                out.close();
            } catch(IOException e1) {
                logger.error("记录系统异常时出现异常！", e1);
            }
            return null;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("path", request.getRequestURI());
            map.put("requestContent", request.getParameterMap());
            return new ModelAndView("500", map);
        }
    }
}
