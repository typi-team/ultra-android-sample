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