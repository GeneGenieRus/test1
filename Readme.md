**Задача**

- Реализовать логику добавления/уменьшения золота в клан, при этом предусмотреть, что золото может зачислиться из сотни(100) разных потоков в один момент. Разными пользователями по разной причине.

- Реализовать отслеживание разных действий начисления золота, чтобы служба поддержки смогла идентифицировать когда и по какой причине в казне изменилось количества золота, сколько было, сколько стало и т.д.


**Сделано**

- Реализован сервис изменения золота в клане. Для обеспечения потокобезопасности используется класс AtomicInteger
- Для отслеживания изменений создана таблица в бд:
  ```
    create table gold_audit(
    user_id long,
    clan_id long not null,
    income_amount integer not null,
    total integer not null,
    reason varchar,
    date_time_updated timestamp not null
    );
  ```
  Запись в таблицу аудита производится асинхронно, что дает выигрыш в производительности
- Написан тест, эмулирующий работу сервиса в многопоточном режиме
- Использованные технологии: Java11, Maven, H2, HikariCP, junit-jupiter
  