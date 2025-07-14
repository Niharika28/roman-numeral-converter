#compiles the jar using maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /roman-numeral-api
COPY . .
RUN ./mvnw clean package -DskipTests

#runs the jar
FROM eclipse-temurin:17
WORKDIR /roman-numeral-api
COPY --from=build /roman-numeral-api/target/roman-numeral-api.jar app.jar
#COPY target/roman-numeral-api.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]