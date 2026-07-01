#!/bin/bash
set -e

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
echo "=== Caderno Digital Colaborativo – Build All Microservices ==="
echo "Root: $ROOT_DIR"
echo ""

SERVICES=("usuario-service" "gamificacao-service" "midia-service" "interacao-service")

for service in "${SERVICES[@]}"; do
  echo "--- Building $service ---"
  cd "$ROOT_DIR/$service"
  mvn clean package -DskipTests -q
  echo "✓ $service built"
  cd "$ROOT_DIR"
done

echo ""
echo "=== All services built successfully! ==="
echo ""
echo "To start all services:"
echo "  docker compose up --build"
echo ""
echo "To start in background:"
echo "  docker compose up -d --build"
echo ""
echo "Service ports:"
echo "  usuario-service    → http://localhost:8081"
echo "  midia-service      → http://localhost:8082"
echo "  interacao-service  → http://localhost:8083"
echo "  gamificacao-service → http://localhost:8084"
