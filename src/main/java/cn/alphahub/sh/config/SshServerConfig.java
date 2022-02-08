package cn.alphahub.sh.config;

import cn.alphahub.sh.task.NginxAutoStart;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.extra.ssh.JschUtil;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
public class SshServerConfig implements ApplicationRunner {


    @Bean
    public Session session(SshProperties properties) {
        Session session = JschUtil.getSession(properties.getHost(), properties.getPort(), properties.getUsername(), properties.getPassword());
        JschUtil.exec(session, "source /etc/profile", StandardCharsets.UTF_8);
        return session;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SpringUtil.getBean(NginxAutoStart.class).runScript();
    }
}
