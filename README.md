# Ultra

## О проекте 

Тут будет описание.

## Как подключить

Пример подключения можете посмотреть в модуле `sample`.

### Gradle

Для получения доступа, нужно добавить приватный репозиторий. Данные для подключения нужно получить у соответствующих лиц.
После добавления приватного репозитория, добавляем зависимость в **gradle файл**:

```groovy
implementation 'com.typi.ultra:ultra:1.0.0-beta.6'
```

### Инициализация ComponentHolder

Чтобы инициализировать `UltraComponentHolder` нужно передать зависимости. Зависимости обьявлены в`UltraDependencies`
который содержит интерфейсы которые нужно реализовать. Примеры реализации демонстрированы в папке 
`com.typi.ultra.sample.ultra`. Теперь **component holder** готов к использованию. 

Вы можете внедрить предоставляемые классы в свой DI граф. Получить предоставляемые классы нужно через
`UltraComponentHolder.get()` которая возвращает интерфейс `UltraApi`. В нем содержатся необходимые классы.

### Авторизация

- После авторизации нужно вызвать `UltraAuthProvider.login`, куда нужно передать **sessionId**.

### Пуш-уведомления

- Для создания канала нужно вызвать `UltraPushProvider.createNotificationChannel`.
- При обновлений пуш-токена нужно вызвать `UltraPushProvider.onNewToken`.
- При получений пуш-уведомления, нужно вызвать `UltraPushProvider.isChatPush`, которая возвращает `boolean`. 
Если результат `true`, то нужно вызвать `UltraPushProvider.showNotification`.

### Сервисы

- Для корректной работы сервисов, необходимо добавить слушатель для `UltraLifecycleObserver`.
- В `MainActivity` нужно добавить вызов сервисов:

```kotlin
    startService(Intent(this, UltraService::class.java))
    startService(Intent(this, FileDownloadService::class.java))
```

### Google Maps

- Нужно добавить Google Maps API_KEY в `AndroidManifest.xml` для отображения карт в чате.

