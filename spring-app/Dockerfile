FROM openjdk:17
# VOLUME /tmp

ARG JAR_FILE
ARG PORT
# ARG DATABASE_DIALECT

# ENV DATABASE_DIALECT $DATABASE_DIALECT

COPY ${JAR_FILE} app.jar

CMD java \
    -Dserver.port=$PORT \
    -Djava.security.egd=file:/dev/./urandom \
    -jar /app.jar