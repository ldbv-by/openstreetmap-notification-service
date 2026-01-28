#!/bin/sh
set -e

export PGHOST="$OSM_NOTIFICATION_SERVICE_DATABASE_HOST"
export PGPORT="$OSM_NOTIFICATION_SERVICE_DATABASE_PORT"
export PGDATABASE="$OSM_NOTIFICATION_SERVICE_DATABASE"
export PGUSER="$OSM_NOTIFICATION_SERVICE_DATABASE_USERNAME"
export PGPASSWORD="$OSM_NOTIFICATION_SERVICE_DATABASE_PASSWORD"

# ---- Create .pgpass ----
echo "$PGHOST:$PGPORT:$PGDATABASE:$PGUSER:$PGPASSWORD" > ~/.pgpass
chmod 600 ~/.pgpass

# ---- Wait on database ----
echo "Wait on database $PGHOST:$PGPORT ..."
until pg_isready -h "$PGHOST" -p "$PGPORT" -U "$PGUSER" >/dev/null 2>&1; do
  sleep 2
done
echo "Database is ready."

exec java -jar /app/openstreetmap-notification-service.jar
