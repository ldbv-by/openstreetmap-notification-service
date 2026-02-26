# ---- Build Stage ----
FROM maven:3.9.12-eclipse-temurin-25 AS build

WORKDIR /src
COPY pom.xml .
RUN mvn -B -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -B -DskipTests clean package

# ---- Runtime Stage ----
FROM eclipse-temurin:25-jre

RUN apt-get update && apt-get install -y --no-install-recommends \
    postgresql-client \
 && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY --from=build /src/target/*.jar ./openstreetmap-notification-service.jar

COPY docker/entrypoint/docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh
RUN chmod +x /usr/local/bin/docker-entrypoint.sh \
    && sed -i 's/\r$//' /usr/local/bin/docker-entrypoint.sh

EXPOSE 8080
ENTRYPOINT ["/usr/local/bin/docker-entrypoint.sh"]