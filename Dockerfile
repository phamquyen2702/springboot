FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /app
COPY . .
RUN dos2unix gradlew
RUN ./gradlew build -x test

FROM eclipse-temurin:17-jre-alpine as production
WORKDIR /app
COPY --from=build  app/build/libs/demo2-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]