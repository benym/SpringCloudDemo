package cn.itcast.order.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Time : 2021/12/1 22:24
 * @Author : YuanMing
 * @File : HeaderOriginParser.java
 * @Software: IntelliJ IDEA
 */
@Component
public class HeaderOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        // 1.获取请求头
        String origin = httpServletRequest.getHeader("origin");
        if(StringUtils.isEmpty(origin)){
            origin = "blank";
        }
        return origin;
    }
}
