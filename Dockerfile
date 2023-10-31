FROM amazoncorretto:17
VOLUME /tmp
#ARG JAR_FILE=build/libs/*.jar
COPY build/libs/*.jar meil_backend.jar
ENTRYPOINT ["java","-jar","meil_backend.jar"]