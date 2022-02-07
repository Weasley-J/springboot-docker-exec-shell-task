package cn.alphahub.sh.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SSH服务端配置类
 *
 * @author lwj
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.ssh.server")
public class SshProperties {
    /**
     * 主机ip
     */
    private String host;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
