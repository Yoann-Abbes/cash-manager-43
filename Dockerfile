FROM openjdk:8

WORKDIR .
COPY ./target/cash-manager-43.jar cash-manager-43.jar
CMD ["java","-jar","cash-manager-43.jar"]