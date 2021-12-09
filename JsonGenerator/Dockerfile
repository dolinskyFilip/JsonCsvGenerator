FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/jsonapp-0.0.1-SNAPSHOT.jar .
EXPOSE 9001
RUN java -jar jsonapp-0.0.1-SNAPSHOT.jar
