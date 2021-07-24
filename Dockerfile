FROM openjdk:12-alpine

EXPOSE 5500

ADD target/SpringBoot_MoneyTransferCardToCard-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]