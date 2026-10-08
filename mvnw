#!/bin/sh
# Maven Wrapper simple para Linux / macOS.
# Descarga Maven 3.9.9 una sola vez en ~/.m2/wrapper si no existe.
# Uso: ./mvnw spring-boot:run
set -e
MVN_VERSION=3.9.9
MVN_DIR="$HOME/.m2/wrapper/apache-maven-$MVN_VERSION"
MVN_URL="https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/$MVN_VERSION/apache-maven-$MVN_VERSION-bin.tar.gz"

if [ ! -x "$MVN_DIR/bin/mvn" ]; then
  echo "Descargando Maven $MVN_VERSION (solo la primera vez)..."
  mkdir -p "$HOME/.m2/wrapper"
  curl -fsSL "$MVN_URL" | tar -xz -C "$HOME/.m2/wrapper"
fi

exec "$MVN_DIR/bin/mvn" "$@"
