FROM openjdk:13-alpine

COPY api-user-mgmt/server/target/user-mgmt-server-1.0.0.jar /app/user-mgmt-server.jar

ENTRYPOINT [ "java", "-jar", "/app/user-mgmt-server.jar" ]
