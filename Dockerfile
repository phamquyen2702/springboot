FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar demo2-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","demo2-0.0.1-SNAPSHOT.jar"]