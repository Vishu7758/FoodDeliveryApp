FROM openjdk:11

# Copy local code to the container image.
#WORKDIR /app

COPY  target/FoodDeliveryApp-*.jar /demo.jar



# Run the web service on container startup.
 CMD  java -jar /demo.jar

