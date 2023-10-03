Feature: : полное тестовое задание

  Scenario: Выполнение всех степов тестового задания
    Given 1.Перейти на "https://demoqa.com/"
    When 2.Нажать на «Elements»
    And 3.Нажать на «Text box»
    And 4.Заполнить поля: Full Name, Email, Current Address, Permanent Address
    And 5.Нажать на кнопку «Submit»
    Then 6.Проверить, что данные в блоке сохранены корректно
    When 7.Нажать на «Buttons»
    And 8.Нажать на кнопку «Click me»
    Then 9.Проверить, что появился текст "You have done a dynamic click"
    When 10.Нажать на кнопку «Right Click me»
    Then 11.Проверить, что появился текст "You have done a right click"
    When 12.Нажать на кнопку «Double Click me»
    Then 13.Проверить, что появился текст "You have done a double click"
    When 14.Нажать на «Alerts, Frame & Windows»
    And 15.Нажать на «Browser Windows»
    And 16.Нажать на кнопку «New Tab»
    Then 17.Закрыть новую вкладку
    When 18.Нажать на кнопку «New window»
    Then 19.Закрыть новое окно
    When 20.Нажать на «Alerts»
    And 21.Нажать на кнопку «Click me» рядом с Click Button to see alert
    Then 22.Закрыть уведомление
    When 23.Нажать на кнопку «Click me» рядом с On button click, alert will appear after 5 seconds
    Then 24.Закрыть уведомление
    When 25.Нажать на кнопку «Click me» рядом с On button click, confirm box will appear
    And  26.Нажать на кнопку «Да» в уведомление
    Then 27.Проверить, что появился текст "You selected Ok"
    When 28.Нажать на кнопку «Click me» рядом с On button click, prompt box will appear
    And 29.Заполнить поле в уведомление данными: "Test name"
    Then 30.Проверить, что появился текст "You entered Test name"
