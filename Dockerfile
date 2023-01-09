FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} incidents.jar
ENTRYPOINT ["java","-jar","/incidents.jar", "-web -webAllowOthers -tcp -tcpAllowOthers -browser"]
