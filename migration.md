# -> 1.2.8

- Добавлено поле `receptionService` в `SupportChatInfo`

# -> 1.2.7

- Переименовано поле `linkTextColor` в `linkText`
- Добавлены поля `codeText`, `codeBackground` в `UltraIcons.Bubble`
- Добавлено поле `systemBubble` в `UltraColors`

# -> 1.2.6

- Перенесены и добавлены некоторые поля в `UltraLocalise.Chats.LastMessage`
- Добавлены поля `incomingCall`, `outgoingCall`, `cancelledCall`, `missedCall`, `noAnswer` в `UltraLocalise.ChatDetail`
- Добавлены поля `incomingCall`, `outgoingCall`, `missedCall` в `UltraIcons.Bubble`

# -> 1.2.5

- Добавлено новое поля в `UltraLocalise.Error.disclaimerNotAccepted`

# -> 1.2.0

- Удалено поле `unrecognized` с `UltraIcons.Bubble.MoneyStatus`
- Удалено поле `MONEY_STATUS_UNKNOWN` с `TransactionStatus`

# -> 1.1.11

- Перенесено поле `you` в `UltraLocalise.Common.you`

# -> 1.1.10

- Добавлено новое поля в `UltraLocalise.Chats.you`

# -> 1.1.8

- Добавлен аргумент `UltraUserType` для метода `UltraUserDelegate.getUserInfo`

# -> 1.1.6

- Добавлены новые поля в `UltraLocalise.Date`
- Добавлены новые правила в proguard для Joda Time:

```text
-dontwarn org.joda.convert.**
-dontwarn org.joda.time.**
-keep class org.joda.time.** { *; }
-keep interface org.joda.time.** { *; }
```

# -> 1.1.5

- Добавлено новое правило в proguard для proto файлов `-keep class com.typi.ultra.proto.** { *; }`
- Изменен ключ `noMessages` в `UltraLocalise.Chat`

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