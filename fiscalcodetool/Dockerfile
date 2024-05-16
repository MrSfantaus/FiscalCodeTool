# Fase di build: utilizza un'immagine base con Maven e installa JDK 21
FROM eclipse-temurin:21 AS build

# Installa Maven
RUN apt-get update && apt-get install -y maven

# Imposta la directory di lavoro all'interno del container
WORKDIR /app

# Copia il file pom.xml e la cartella src nella directory di lavoro
COPY pom.xml .
COPY src ./src

# Esegui il build del progetto utilizzando Maven
RUN mvn clean package

# Seconda fase: utilizza un'immagine base di OpenJDK 21 per eseguire l'applicazione
FROM eclipse-temurin:21-jre

# Imposta la directory di lavoro all'interno del container
WORKDIR /app

# Copia l'artefatto generato dalla fase di build
COPY --from=build /app/target/fiscalcodetool-0.0.1.jar app.jar

# Specifica il comando per eseguire l'applicazione
ENTRYPOINT ["java", "-jar", "app.jar"]
