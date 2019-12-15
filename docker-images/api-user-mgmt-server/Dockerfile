FROM openjdk:13-alpine

COPY api-user-mgmt/server/target/swagger-spring-1.0.0.jar /app/api-user-mgmt-server.jar

ENTRYPOINT [ "java", "-jar", "/app/api-user-mgmt-server.jar" ]
