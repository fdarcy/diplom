# Процедура запуска автотестов

## MySQL

1. В файле application.properties задать данные и URL для подключения MySQL   
(spring.datasource.url=jdbc:mysql://localhost:3306/app)
2. Запустить контейнер с MySQL
3. Запустить контейнер с эмулятором банковских сервисов
4. Запустить приложение (java -jar aqa-shop.jar)
5. Запустить автотесты 


## PostgreSQL

1. В файле application.properties задать данные и URL для подключения PostgreSQL   
   (spring.datasource.url=jdbc:postgresql://localhost:5432/db)
2. Запустить контейнер с PostgreSQL
3. Запустить контейнер с эмулятором банковских сервисов
4. Запустить приложение (java -jar aqa-shop.jar)
5. Запустить автотесты 