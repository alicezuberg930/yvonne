FROM eclipse-temurin:21-jre-alpine

RUN addgroup -S spring && adduser -S spring -G spring

COPY target/*.jar /opt/myApp.jar

USER spring:spring

WORKDIR /opt

ENTRYPOINT [ "java", "-jar", "myApp.jar" ]

EXPOSE 80