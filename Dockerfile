FROM openjdk:8
EXPOSE 8090
ADD target/bootDocker.jar bootdocker.jar
ENTRYPOINT ["java","-jar","bootDocker.jar"]