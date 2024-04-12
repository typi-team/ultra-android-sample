# Ultra

## Как подключить

Чтобы подключить sdk, необходимо добавить данные приватного репозитория:

```groovy
repositories {
    google()
    mavenCentral()
    maven {
        url 'https://gitlab.typi.team/api/v4/projects/19/packages/maven'
        name 'GitLab'
        credentials(HttpHeaderCredentials) {
            name = 'Deploy-Token'
            value = '{some-token}'
        }
        authentication {
            header(HttpHeaderAuthentication)
        }
    }
}
```

Токен нужно получить у соответствующих лиц.
После добавления приватного репозитория, добавляем зависимость в **gradle файл**:

```groovy
implementation 'com.typi.ultra:ultra:1.0.2'
```

Пример подключения можете посмотреть в модуле `sample`.

### Немного о Component Holder

При разработке был использован подход Component Holder. Если вы не знакомы с этим подходом, рекомендую прочитать статью
[Ленивая склейка модулей Android-приложения](https://habr.com/ru/articles/536106/).

### Инициализация ComponentHolder

Чтобы инициализировать `UltraComponentHolder` нужно передать зависимости. Зависимости обьявлены в `UltraDependencies`
который содержит интерфейсы которые нужно реализовать. Примеры реализации демонстрированы в папке
`com.ultra.sample.ultra`. Теперь **component holder** готов к использованию.

Вы можете внедрить предоставляемые классы в свой DI граф. Получить предоставляемые классы нужно через
`UltraComponentHolder.get()` которая возвращает интерфейс `UltraApi`. В нем содержатся необходимые классы.

### Авторизация

- После авторизации нужно вызвать метод `UltraAuthProvider.login`, куда нужно передать **sessionId**.
- После выхода из профиля, нужно вызвать метод `UltraAuthProvider.logout` для очистки данных.

### Пуш-уведомления

- Для создания канала нужно вызвать метод `UltraPushProvider.createNotificationChannel`.
- При обновлений пуш-токена нужно вызвать метод `UltraPushProvider.onNewToken`.
- При получений пуш-уведомления, нужно вызвать метод `UltraPushProvider.parseUltraPush`, которая возвращает
  модель `UltraPush`. Если результат `UltraPush.isSdkPush == true`, то нужно вызвать метод
  `UltraPushProvider.showNotification`.

### Google Maps

- Нужно добавить Google Maps API_KEY в `AndroidManifest.xml` для отображения карт в чате.

