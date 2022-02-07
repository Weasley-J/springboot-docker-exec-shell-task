package cn.alphahub.sh.config;

import cn.hutool.extra.ssh.JschUtil;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * Ssh服务端配置
 *
 * @author weasley
 * @version 1.0
 * @date 2022/2/7
 */
@Slf4j
@Configuration
public class SshServerConfig {

    @Bean
    public Session session(SshProperties properties) {
        Session session = JschUtil.getSession(properties.getHost(), properties.getPort(), properties.getUsername(), properties.getPassword());
        JschUtil.exec(session, "source /etc/profile", StandardCharsets.UTF_8);
        return session;
    }
}
