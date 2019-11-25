FROM maven:3.5-jdk-9

COPY . /app
WORKDIR /app
SHELL ["/bin/bash", "-c"]
RUN mvn -f /app/pom.xml clean package install 
ENTRYPOINT ["java","-jar","/app/target/cash-manager-43.jar"]
