package cn.alphahub.sh.task;

import cn.hutool.extra.ssh.JschUtil;
import com.jcraft.jsch.Session;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 自动执行远程主机上的启动nginx的shell脚本
 *
 * @author weasley
 * @version 1.0
 * @date 2022/2/7
 */
@Slf4j
@Data
@Component
public class NginxAutoStart {

    private Boolean isDone = Boolean.FALSE;

    @Resource
    private Session session;

    @Scheduled(cron = "0 0,30 * * * ? ")
    public void runScript() {
        if (Objects.equals(Boolean.TRUE, this.isDone)) {
            log.warn("任务类'{}'已经执行过了", this.getClass().getTypeName());
            return;
        }
        log.warn("任务类'{}'开始执行了", this.getClass().getTypeName());
        String[] shells = new String[]{"./es_log_clean.sh", "./nginx_run.sh", "./feige_client.sh"};
        try {
            for (String shell : shells) {
                String exec = JschUtil.exec(this.session, "chmod -v 0777 " + shell, StandardCharsets.UTF_8);
                System.out.println(exec);
            }
            for (String shell : shells) {
                String exec = JschUtil.exec(this.session, shell, StandardCharsets.UTF_8);
                System.out.println("[" + shell + "]的执行结果:\n" + exec);
            }
            this.isDone = true;
        } catch (Exception e) {
            this.isDone = false;
            log.error("执行shell异常{}", e.getLocalizedMessage(), e);
        } finally {
            JschUtil.close(this.session);
        }
    }
}
