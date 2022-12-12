FROM maven:3.8.6-openjdk-18
WORKDIR /gestionStock-app
COPY . .
ENTRYPOINT ["cd","/gestionStock-app"]
RUN mvn clean install
CMD mvn spring-boot:run