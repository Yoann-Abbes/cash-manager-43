FROM openjdk:8
ADD ../target/cash-manager-0.0.1-SNAPSHOT.jar cash-manager.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "cash-manager.jar"]