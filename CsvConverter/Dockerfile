FROM adoptopenjdk/openjdk14
EXPOSE 9001
ARG JAR_FILE=target/csvconverter-0.0.1-SNAPSHOT.jar .
COPY ${JAR_FILE} app2.jar
ENTRYPOINT ["java","-jar","app2.jar"]
