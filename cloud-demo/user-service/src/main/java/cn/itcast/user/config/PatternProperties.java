package cn.itcast.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Time : 2021/8/24 23:01
 * @Author : YuanMing
 * @File : PatternProperties.java
 * @Software: IntelliJ IDEA
 */

@Data
@Component
@ConfigurationProperties(prefix = "myname")
public class PatternProperties {
    private String name;

    private String sharename;

    private String localname;
}
