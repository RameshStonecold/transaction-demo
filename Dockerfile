FROM alpine:latest AS build
ENV JAVA_HOME /opt/jdk/jdk-17.0.13+B11
ENV PATH $JAVA_HOME/bin:$PATH

#ADD https://github.com/adoptium/temurin17-binaries/releases/tag/jdk-17.0.13%2B11/OpenJDK17U-jdk_x64_alpine-linux_hotspot_17.0.13_11.tar.gz /opt/jdk/
ADD https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.13%2B11/OpenJDK17U-jdk_x64_alpine-linux_hotspot_17.0.13_11.tar.gz /opt/jdk/
#RUN tar -xzvf /opt/jdk/OpenJDK17U-jdk_x64_alpine-linux_hotspot_17.0.13_11.tar.gz -C /opt/jdk/
# Extract the JDK
RUN tar -xzvf /opt/jdk/OpenJDK17U-jdk_x64_alpine-linux_hotspot_17.0.13_11.tar.gz -C /opt/jdk/

RUN ["jlink", "--compress=2", \
     "--module-path", "/opt/jdk/jdk-17.0.13+B11/jmods/", \
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


