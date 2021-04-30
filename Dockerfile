FROM openjdk:11-jre-slim-buster
MAINTAINER zhw <2261842924@qq.com>

RUN groupadd -r helloworld && useradd -r -ghelloworld helloworld
RUN mkdir -m 0755 -p /usr/local/helloworld/bin
RUN mkdir -m 0755 -p /usr/local/helloworld/config
RUN mkdir -m 0755 -p /usr/local/helloworld/logs/

VOLUME ["/usr/local/helloworld/logs", "/usr/local/helloworld/logs"]
VOLUME ["/usr/local/helloworld/config", "/usr/local/helloworld/config"]

COPY web-api/target/web-api.jar /usr/local/helloworld/bin

RUN chown -R helloworld:helloworld /usr/local/helloworld

EXPOSE 8080
EXPOSE 8443

WORKDIR "/usr/local/helloworld/bin"

CMD ["java -jar web-api.jar --spring.config.location=../config/application.yml"]
