# -> 1.0.1

- Удален `UltraLifecycleObserver`.
- Изменена модель для передачи данных о переводе денег. Теперь передаем `UltraTransaction` в метод
  `UltraCacheProvider.saveTransaction`.
- Добавлена новое поле `UltraColors.bubble.linkTextColor` которая описывает цвет выделения ссылок.
- Добавлена новое поле `UltraColors.bubble.moneyStatus` которая описывает иконки перевода денег в зависимости от статуса
  транзакции.