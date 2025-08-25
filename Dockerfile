# Базовый образ с JDK
FROM eclipse-temurin:17-jdk-alpine

# Рабочая директория внутри контейнера
WORKDIR /app

# Копируем jar
COPY target/spring-petclinic-*.jar app.jar

# Запуск
ENTRYPOINT ["java", "-jar", "app.jar"]

# Приложение слушает 8080
EXPOSE 8080
