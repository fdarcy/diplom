# Процедура запуска автотестов

## MySQL
1. Выполнить команду docker compose up для запуска контейнеров с базой данных и с эмулятором банковских сервисов
2. Запустить приложение (java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar)
3. Запустить автотесты (./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app")


## PostgreSQL
1. Выполнить команду docker compose up для запуска контейнеров с базой данных и с эмулятором банковских сервисов
2. Запустить приложение (java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/db" -jar artifacts/aqa-shop.jar)
3. Запустить автотесты (./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/db")

[План](docs/Plan.md)  
[Отчет](docs/Report.md)  
[Summary](docs/Summary.md)  
