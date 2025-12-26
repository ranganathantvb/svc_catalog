# Catalog Service

Spring Boot service providing CRUD APIs for product categories and products. Defaults to an in-memory H2 database so you can run without external dependencies.

## Prerequisites
- JDK 17+
- Gradle wrapper (provided)
- (Optional) Docker, if you prefer Postgres via `docker compose up`

## Setup and Run (H2 in-memory)
1. Install dependencies and build:
   ```bash
   ./gradlew build
   ```
2. Start the service:
   ```bash
   ./gradlew bootRun
   ```
3. Service will listen on `http://localhost:8080`. Basic checks:
   - `GET /` → "Catalog service is running."
   - `GET /health` → `{"status":"UP"}`
   - H2 console (enabled): `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:catalogdb`, user `sa`, empty password).
4. Swagger/OpenAPI:
   - Swagger UI: `http://localhost:8080/swagger-ui/index.html`
   - OpenAPI JSON: `http://localhost:8080/v3/api-docs`
   - Fetch the spec via curl:
     ```bash
     curl http://localhost:8080/v3/api-docs | head
     ```

## Running with Postgres (optional)
1. Start Postgres:
   ```bash
   docker compose up -d postgres
   ```
2. Update `src/main/resources/application.yml` with the Postgres JDBC URL, user, and password (or supply via env vars), then run `./gradlew bootRun`.

## API Examples

### Categories
- Create:
  ```bash
  curl -X POST http://localhost:8080/api/categories \
       -H "Content-Type: application/json" \
       -d '{"name":"Electronics","description":"Devices"}'
  ```
- List:
  ```bash
  curl http://localhost:8080/api/categories
  ```
- Get by id:
  ```bash
  curl http://localhost:8080/api/categories/1
  ```
- Update:
  ```bash
  curl -X PUT http://localhost:8080/api/categories/1 \
       -H "Content-Type: application/json" \
       -d '{"name":"Electronics","description":"Updated"}'
  ```
- Delete:
  ```bash
  curl -X DELETE http://localhost:8080/api/categories/1
  ```

### Products
- Create (associate to category id `1`):
  ```bash
  curl -X POST "http://localhost:8080/api/products?categoryId=1" \
       -H "Content-Type: application/json" \
       -d '{"name":"Laptop","sku":"LP-001","price":1499.99,"active":true}'
  ```
- List all:
  ```bash
  curl http://localhost:8080/api/products
  ```
- List active:
  ```bash
  curl http://localhost:8080/api/products/active
  ```
- List by category:
  ```bash
  curl http://localhost:8080/api/products/by-category/1
  ```
- Get by id:
  ```bash
  curl http://localhost:8080/api/products/1
  ```
- Update (optionally change category with `categoryId` query param):
  ```bash
  curl -X PUT "http://localhost:8080/api/products/1?categoryId=1" \
       -H "Content-Type: application/json" \
       -d '{"name":"Laptop","sku":"LP-001","price":1599.99,"active":true}'
  ```
- Delete:
  ```bash
  curl -X DELETE http://localhost:8080/api/products/1
  ```

### Users (in-memory demo)
- Create:
  ```bash
  curl -X POST http://localhost:8080/api/users \
       -H "Content-Type: application/json" \
       -d '{"name":"Alice","email":"alice@example.com"}'
  ```
- List:
  ```bash
  curl http://localhost:8080/api/users
  ```
- Get by id:
  ```bash
  curl http://localhost:8080/api/users/1
  ```
- Update:
  ```bash
  curl -X PUT http://localhost:8080/api/users/1 \
       -H "Content-Type: application/json" \
       -d '{"name":"Alice B","email":"alice.b@example.com"}'
  ```
- Delete:
  ```bash
  curl -X DELETE http://localhost:8080/api/users/1
  ```

### Tasks (in-memory demo)
- Create (assignee must be an existing user id):
  ```bash
  curl -X POST http://localhost:8080/api/tasks \
       -H "Content-Type: application/json" \
       -d '{"title":"Write docs","assigneeUserId":1}'
  ```
- List (optionally filter by assignee):
  ```bash
  curl "http://localhost:8080/api/tasks?assigneeUserId=1"
  ```
- Get by id:
  ```bash
  curl http://localhost:8080/api/tasks/1
  ```
- Update:
  ```bash
  curl -X PUT http://localhost:8080/api/tasks/1 \
       -H "Content-Type: application/json" \
       -d '{"title":"Write docs","completed":true,"assigneeUserId":1}'
  ```
- Delete:
  ```bash
  curl -X DELETE http://localhost:8080/api/tasks/1
  ```

### Insecure demo endpoints (opt-in)
`UserController` exposes intentionally vulnerable routes under `/api/users/demo/*` (mass assignment, IDOR, weak hash, etc.) when `demo.insecure.enabled=true` (default in `src/main/resources/application.yml`). Disable them with `DEMO_INSECURE_ENABLED=false ./gradlew bootRun` or by setting `demo.insecure.enabled: false` in your config; once disabled, these paths return 404.

## Testing
```bash
./gradlew test
```
