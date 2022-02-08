package cn.alphahub.sh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Springboot应用执行shell脚本的启动类
 */
@EnableScheduling
@SpringBootApplication
@ConfigurationPropertiesScan({"cn.alphahub.**.config"})
public class DockerExecShellApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockerExecShellApplication.class, args);
    }

}
