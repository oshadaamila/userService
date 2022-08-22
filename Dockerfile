FROM public.ecr.aws/h6g3r1y4/openjdk11:jre-11.0.12_7-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9090
ARG db_url
ENV DB_URL $db_url
ARG db_username
ENV DB_USERNAME $db_username
ARG db_password
ENV DB_PASSWORD $db_password
ENTRYPOINT ["java","-jar","/app.jar"]