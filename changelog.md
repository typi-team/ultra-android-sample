# Changelog

## 1.2.8 [23.08.2024]

- Добавлена передача receptionService в делегат

## 1.2.7 [20.08.2024]

- Добавлена поддержка markdown для текстовых и системных сообщений

## 1.2.6 [15.08.2024]

- Добавлены сообщения звонков
- Добавлено кэширование ответа GetSupportChats

## 1.2.5.1 [15.08.2024]

- Исправлены отступы системного сообщения

## 1.2.5 [03.07.2024]

- Улучшена обработка нажатия на дисклеймер
- Улучшена обработка real time обновлений

## 1.2.4 [29.06.2024]

- Улучшена работа таймера

## 1.2.3 [25.06.2024]

- Улучшена работа с апдейтами

## 1.2.2 [25.06.2024]

- Исправлена проблема с системными сообщениями

## 1.2.1 [24.06.2024]

- Улучшена стабильность восстановления соединения при возврате в приложение

## 1.2.0 [20.06.2024]

- Улучшена логика синхронизации обновлений
- Добавлено переподключение соединения после ошибок

## 1.1.11 [18.06.2024]

- Отключена возможность отправить видео с галереи и камеры в чатах с поддержкой
- Скрыт статус онлайн в деталях чата с самим с собой

## 1.1.10 [17.06.2024]

- Убрали возможность отправки аудиосообщений в чате с персональным менеджером
- Убрали индикатор онлайн в чате с самим собой и добавили

## 1.1.9 [12.06.2024]

- Добавлена обработка и логирование ошибок в провайдерах для интеграции

## 1.1.8 [11.06.2024]

- Добавлен тип пользователя в метод `UltraUserDelegate.getUserInfo`

## 1.1.7 [07.06.2024]

- Добавлена обновление чата с персональным менеджером
- Исправлен баг с последним временем визита на экране деталей чата

## 1.1.6 [07.06.2024]

- Исправление ошибок с датами
- Исправление отображения чатов с персональными менеджерами в P2P чатах, если там нет сообщений
- Добавлено отключение кнопки отправки сообщения когда печатает ассистент

## 1.1.5 [04.06.2024]

- Добавлен тип чата в пустого состояния для экрана чатов
- Исправлен баг с запросом UserInfo для ботов
- Исправлен автоскролл
- Скрыли заголовок у сообщений в Support чатах
- Исправлена отображение аватарки при созданий чата
- Исправлена создание пустого чата
- Добавлена поддержка чата с самим с собой
- Добавлено сжатие картинок

## 1.1.4 [31.05.2024]

- Дизайн правки на экране списка чатов
- Дизайн правки на экране деталей чата
- Добавлен индикатор статуса "Онлайн" в список чатов
- Добавлено закрепление чата с Ассистентом сверху
- Отключена возможности в чатах с персональным менеджером: Удалить чат/Удалить сообщение/Заблокировать/Перевод денег/
  Переход в детали пользователя
- Исправлен крэш вызова logout до вызова login

## 1.1.3 [27.05.2024]

- Оптимизирована работа с БД
- Исправлен баг с медиа сообщениями

## 1.1.2 [26.05.2024]

- Добавлен метод для обновления информации о support чатах.
- Отключены возможности в Support чатах: Удалить чат/Удалить сообщение/Заблокировать/Звонки/Перевод денег/Вложения
- Обновлен формат даты и времени сообщений
- Механизм удаления файла базы данных

## 1.1.1 [22.05.2024]

- Добавлена поддержка системных сообщений
- Исправлена загрузка initial state

## 1.1.0 [20.05.2024]

- Экран с чатами для support и персональных менеджеров

## 1.0.5 [14.05.2024]

- Добавлен делегат для получения информации о чатах с поддержкой и персональных менеджерах
- Переделана логика получения информации о пользователе

## 1.0.4 [03.05.2024]

- Добавлена возможность шифрования БД и shared preferences
- Увеличен minSdk до 23

## 1.0.3 [16.04.2024]

- Исправлен ripple эффект для всех баблов

## 1.0.2 [12.04.2024]

- Добавлен callback при нажатий на сообщение о переводе денег.

## 1.0.1 [12.04.2024]

- Теперь все процессы чата будут запускаться после авторизации.
- Исправлены ошибки по исчезновению чатов.
- Добавлена загрузка и обработка ошибок при нажатий на кнопку "Согласен" дисклеймера.
- Исправлено отображение времени последнего посещения после нажатия на кнопку "Согласен" дисклеймера.
- Изменения по переводу денег(добавление возможности передавать разные иконки в зависимости от статуса и обновление
  статуса транзакции).
