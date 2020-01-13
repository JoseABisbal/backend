FROM openjdk:8-jdk
VOLUME /tmp
ADD ./target/thefarmerkit-backend.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS "-Xms256m -Xmx256m"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS $MONITORING -jar /app.jar"]
