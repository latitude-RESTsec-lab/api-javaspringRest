FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/gs*.jar service-java.jar
RUN sh -c 'touch /service-java.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /service-java.jar" ]

