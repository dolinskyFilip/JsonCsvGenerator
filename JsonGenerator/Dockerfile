FROM adoptopenjdk/openjdk14
EXPOSE 9000
ARG JAR_FILE=target/jsonapp-0.0.1-SNAPSHOT.jar .
COPY ${JAR_FILE} app1.jar
ENTRYPOINT ["java","-jar","app1.jar"]
