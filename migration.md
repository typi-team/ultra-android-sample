# -> 1.1.4

- Изменения по uikit(colors, typography)

# -> 1.1.2

- Добавлен новый делегат `UltraSupportProvider` для обновления информации о support чатах.

# -> 1.1.0

- Добавлен новый делегат `UltraMessageDelegate` для получение meta данных для сообщений.
- Новые ключи локализации для `Chats` и `ChatDetail`
- Изменена структура `UltraSupportChats`. Убрали `isAssistantEnabled`, и добавили `AssistantInfo`.
- Добавлен новый `UltraChatsScreenMode` параметр в `UltraNavigator.ChatsScreen`.

# -> 1.0.5

- Добавлен новый делегат `UltraSupportDelegate` с одним методом для получения следующей информации:
    - `isAssistantEnabled: Boolean` - флаг для отображение чата с Ассистентом
    - `supportChats: List<SupportChatInfo>` - информация о чатах с поддержкой
    - `personalManagers: List<PersonalManagerInfo>` - информация о персональных менеджерах
- Переименован класс `UserInfo` на `UltraUserInfo` и поле `avatar` на `avatarUrl`
- Добавлен новый провайдер `UltraUserProvider`. Нужно вызывать если изменилось отображаемое имя или ссылка на аватарку
  у пользователя

# -> 1.0.4

- Добавлен новый метод `getEncryptionKey`  в `UltraSettingsDelegate` куда передается ключ для шифрования БД.
- Увеличен minSdk до 23

# -> 1.0.2

- Добавлен новый callback `onMoneyMessageClicked` в `UltraNavigator.ChatDetailScreen` которая срабатывает при нажатий на
  сообщение о переводе денег. В параметрах передается `transactionId`.

# -> 1.0.1

- Удален `UltraLifecycleObserver`.
- Изменена модель для передачи данных о переводе денег. Теперь передаем `UltraTransaction` в метод
  `UltraCacheProvider.saveTransaction`.
- Добавлена новое поле `UltraColors.bubble.linkTextColor` которая описывает цвет выделения ссылок.
- Добавлена новое поле `UltraColors.bubble.moneyStatus` которая описывает иконки перевода денег в зависимости от статуса
  транзакции.