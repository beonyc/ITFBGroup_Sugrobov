# language: ru
Функция: Полностью весь сценарий с cucumber

  @allStepsScenario
  Сценарий: Выполнение всех степов тестового задания с помощью cucumber
    Дано Перейти на "https://demoqa.com/"
    Когда Нажать на «Elements»
    И Нажать на «Text box»
    И Заполнить поля: Full Name, Email, Current Address, Permanent Address
    И Нажать на кнопку «Submit»
    Тогда Проверить, что данные в блоке сохранены корректно
    Когда Нажать на «Buttons»
    И Нажать на кнопку «Click me»
    Тогда Проверить, что появился текст1 "You have done a dynamic click"
    Когда Нажать на кнопку «Right Click me»
    Тогда Проверить, что появился текст2 "You have done a right click"
    Когда Нажать на кнопку «Double Click me»
    Тогда Проверить, что появился текст3 "You have done a double click"
    Когда Нажать на «Alerts, Frame & Windows»
    И Нажать на «Browser Windows»
    И Нажать на кнопку «New Tab»
    Тогда Закрыть новую вкладку
    Когда Нажать на кнопку «New window»
    Тогда Закрыть новое окно
    Когда Нажать на «Alerts»
    И Нажать на кнопку «Click me» рядом с Click Button to see alert
    Тогда Закрыть уведомление
    Когда Нажать на кнопку «Click me» рядом с On button click, alert will appear after 5 seconds
    Тогда Закрыть уведомление с таймером
    Когда Нажать на кнопку «Click me» рядом с On button click, confirm box will appear
    И  Нажать на кнопку «Да» в уведомление
    Тогда Проверить, что появился текст4 "You selected Ok"
    Когда Нажать на кнопку «Click me» рядом с On button click, prompt box will appear
    И Заполнить поле в уведомление данными: "Test name"
    Тогда Проверить, что появился текст "You entered Test name"

