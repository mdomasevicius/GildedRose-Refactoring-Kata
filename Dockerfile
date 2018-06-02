FROM java:8-jre-alpine

ADD build/libs/gilded-rose-kata.jar app.jar

ENTRYPOINT exec java $JAVA_OPTS -server -jar app.jar
