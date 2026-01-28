FROM azul/zulu-openjdk:24.0.2-24.32

RUN apt-get update && apt-get install -y \
    maven dos2unix postgresql-client \
 && rm -rf /var/lib/apt/lists/*

WORKDIR /src/openstreetmap-notification-service
COPY pom.xml .
RUN mvn -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -B -DskipTests clean package && \
    install -d /app && \
    JAR="$(find target -maxdepth 1 -type f -name '*.jar' \
           ! -name '*-sources.jar' ! -name '*-javadoc.jar' | head -n 1)" && \
    cp "$JAR" /app/openstreetmap-notification-service.jar

RUN rm -rf /src
WORKDIR /app

# ---- Entrypoint Script ----
COPY docker/entrypoint/docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh
RUN chmod +x /usr/local/bin/docker-entrypoint.sh

EXPOSE 8080
ENTRYPOINT [ "/bin/sh", "-c", "dos2unix /usr/local/bin/docker-entrypoint.sh && /usr/local/bin/docker-entrypoint.sh" ]