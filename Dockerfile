FROM alpine:latest AS build
ENV JAVA_HOME /opt/jdk/jdk-21.0.1+12
ENV PATH $JAVA_HOME/bin:$PATH

ADD https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21.0.1%2B12/OpenJDK21U-jdk_x64_alpine-linux_hotspot_21.0.1_12.tar.gz /opt/jdk/
RUN tar -xzvf /opt/jdk/OpenJDK21U-jdk_x64_alpine-linux_hotspot_21.0.1_12.tar.gz -C /opt/jdk/
RUN ["jlink", "--compress=2", \
     "--module-path", "/opt/jdk/jdk-21.0.1+12/jmods/", \
     "--add-modules", "java.base,java.logging,java.naming,java.desktop,jdk.unsupported", \
     "--no-header-files", "--no-man-pages", \
     "--output", "/springboot-runtime"]

FROM alpine:latest
COPY --from=build  /springboot-runtime /opt/jdk
ENV PATH=$PATH:/opt/jdk/bin
EXPOSE 9095
COPY ../target/transaction-demo.jar /opt/app/app.jar
CMD ["java", "-showversion", "-jar", "/opt/app/app.jar"]
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar