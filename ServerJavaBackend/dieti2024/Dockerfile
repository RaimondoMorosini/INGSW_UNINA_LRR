# Usa un'immagine base con Java 17 o la versione necessaria
FROM openjdk:17-jdk-slim

# Imposta la directory di lavoro
WORKDIR /app

# Copia il file JAR nel contenitore
COPY target/dieti2024-0.0.1-SNAPSHOT.jar app.jar

# Espone la porta (opzionale, sostituire con quella usata dall'app)
EXPOSE 8081

# Comando per eseguire l'applicazione
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
