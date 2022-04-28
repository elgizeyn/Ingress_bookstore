FROM openjdk:8
EXPOSE 8080
ADD traget/bookstore.jar bookstore.jar
ENTRYPOINT ["java","-jar","bookstore.jar"]
