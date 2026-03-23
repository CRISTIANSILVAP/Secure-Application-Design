FROM eclipse-temurin:21-jre-jammy

WORKDIR /usrapp/bin

ENV PORT=6000

COPY target/classes ./classes
COPY target/dependency ./dependency

CMD ["java", "-cp", "./classes:./dependency/*", "co.edu.escuelaing.apigateway.secureapp.SecureAppApplication"]

