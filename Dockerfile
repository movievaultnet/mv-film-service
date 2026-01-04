# --- Etapa 1: Compilación (Builder) ---
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compilamos saltando tests para ir rápido en despliegue
RUN mvn clean package -DskipTests

# --- Etapa 2: Ejecución (Runtime) ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos solo el JAR compilado de la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Configuración para permitir conexión a Redis por Socket (Permisos IO)
RUN apk add --no-cache libstdc++

ENTRYPOINT ["java", "-jar", "app.jar"]
