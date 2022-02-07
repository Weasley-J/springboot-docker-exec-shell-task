FROM openjdk:11
RUN mkdir -pv /usr/local/webapp/
ADD springboot-exec-shell-task.jar /usr/local/webapp/springboot-exec-shell-task.jar
EXPOSE 10001
WORKDIR /usr/local/webapp/
ENTRYPOINT ["java","-jar", "/usr/local/webapp/springboot-exec-shell-task.jar"]
