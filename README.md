# Demo Spring Boot Application

A minimal Maven-based Spring Boot application using Java 21 and Spring Web.

## Sum API

Send two whole numbers to receive their sum:

```http
POST /api/sum
Content-Type: application/json

{
  "a": 1,
  "b": 2
}
```

Successful responses contain HTTP 200 and:

```json
{
  "sum": 3
}
```

Missing fields, malformed JSON, non-whole-number values, and sums outside the `long` range return HTTP 400 with a JSON `error` field.

## API documentation

When the application is running, the generated OpenAPI specification is available at:

```text
http://localhost:8080/v3/api-docs
```

Interactive Swagger UI is available at:

```text
http://localhost:8080/swagger-ui.html
```

## Run

```powershell
mvn spring-boot:run
```

Build the application with:

```powershell
mvn clean package
```
