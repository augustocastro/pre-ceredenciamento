FROM openjdk:8
VOLUME /tmp
ADD target/precredenciamento-0.0.1-SNAPSHOT.jar precredenciamento-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","precredenciamento-0.0.1-SNAPSHOT.jar"]