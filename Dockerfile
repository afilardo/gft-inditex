FROM amazoncorretto:25.0.5-alpine3.24

COPY target/*.jar inditex.jar

ENTRYPOINT ["java", "-jar", "inditex.jar"]