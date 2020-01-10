FROM openjdk:13-alpine

COPY api-business/server/target/business-server-1.0.0.jar /app/business-server.jar

ENTRYPOINT [ "java", "-jar", "/app/business-server.jar" ]
