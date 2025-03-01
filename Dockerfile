FROM ubuntu:latest
LABEL authors="mohamed"

# Utilisation d'une image de base avec Java 21
FROM eclipse-temurin:21-jre-alpine

# Définition du répertoire de travail dans le conteneur
WORKDIR /app

# Copie du fichier JAR généré
COPY target/*.jar app.jar

# Copie du script d'initialisation de la base de données
COPY init.sql /docker-entrypoint-initdb.d/init.sql

# Exposition du port
EXPOSE 8080

# Définition du profil actif via variable d'environnement
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE:-db}

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "app.jar"]
