# Test Task By RESLIV (Tereshkov Oleg)

----------------------------------

Задача:

Необходимо создать web приложение по управлению собственным туристическим телеграм ботом.

1) Телеграм бот выдает пользователю справочную информацию о введенном городе. Например, пользователь вводит: «Москва»,
   чат-бот отвечает: «Не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить)))».
2) Данные о городах должны храниться в базе данных.
3) Управлять данными о городах (добавлять новые города и информацию о них, изменять и удалять любую информацию)
   необходимо через REST WebService.

----------------------------------

Technology:

* JDK 1.8
* Spring Boot 2.4.5
* H2
* Maven
* Hibernate Validation
* Logback(SLF4J)
* JUnit
* IntelliJ IDEA

----------------------------------
Для запуска необходимо:

1) Скачать repositorie.
2) Запустить приложение.
3) Найти в телеграмме бота с именем TouristTelegramBot. Токен бота: 1812348566:AAGQyWXK1lzO8YVngU3sGFKok-Rbg-OViEI
4) Для проверки REST использовать TouristTelegramBot.postman_collection.json или test.http(в папке controller).

| URL          | Method    | Action          |
| ---------    | -----     | --------       |
| /city/all    | GET       | All city       |
| /city        | POST      | Create city    |
| /city/{id}   | DELETE    | Deleate city   |
| /city/{id}   | GET       | Get City By Id |
| /city/{id}   | PUT       | Update City    |
