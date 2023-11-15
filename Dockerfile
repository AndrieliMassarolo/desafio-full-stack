FROM jelastic/maven:3.9.5-openjdk-21 as maven

COPY ./pom.xml ./pom.xml

RUN mvn dependency:go-offline -B
COPY ./src ./src

RUN mvn package

FROM openjdk:21-jdk

VOLUME /tmp

# copy over the built artifact from the maven image
COPY --from=maven target/*.jar ./app.jar

RUN sh -c 'touch /app.jar'

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]