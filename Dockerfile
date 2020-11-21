FROM openjdk:8
ADD target/review-service.jar review-service.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar", "review-service.jar"]
