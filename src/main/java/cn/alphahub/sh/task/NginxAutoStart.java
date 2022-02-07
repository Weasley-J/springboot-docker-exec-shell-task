package cn.alphahub.sh.task;

import cn.hutool.extra.ssh.JschUtil;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * 自动执行远程主机上的启动nginx的shell脚本
 *
 * @author weasley
 * @version 1.0
 * @date 2022/2/7
 */
@Slf4j
@Component
public class NginxAutoStart implements ApplicationRunner {

    @Resource
    private Session session;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] shells = new String[]{"./es_log_clean.sh", "./nginx_run.sh"};
        try {
            for (String shell : shells) {
                String exec = JschUtil.exec(this.session, "chmod -v 0777 " + shell, StandardCharsets.UTF_8);
                System.out.println(exec);
            }
            for (String shell : shells) {
                String exec = JschUtil.exec(this.session, shell, StandardCharsets.UTF_8);
                System.out.println("[" + shell + "]的执行结果:\n" + exec);
            }
        } catch (Exception e) {
            log.error("执行shell异常{}", e.getLocalizedMessage(), e);
        } finally {
            JschUtil.close(this.session);
        }
    }
}
