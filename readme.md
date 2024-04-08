# Мобильное приложение Русская литература в клипах

Курсовая работа по дисциплине: прикладное программирование

Клиент-серверное приложение. В качестве клиента Android приложение. Сервер написан на Go, тут смесь
gRPC+Rest.
Ссылка на сервер - https://github.com/DenisPopkov/RusseLiteratureServer.

## Архитектура в приложении MVI

<img width="468" alt="image" src="https://github.com/DenisPopkov/RusseLiteratureServer/assets/57343209/cb02034e-63e4-4313-93a9-b93fd5e6c80e">

## Стек технологий в проекте

* DI (Hilt)
* Jetpack Compose Based UI
* Jetpack Compose Navigation Component
* Gradle Configuration (Version catalog, gradle convention plugin)
* Coil
* Multi-module architecture
* Coroutines
* MVI
* Rest+gRPC via Retrofit+Google grpc library

## Структура проекта

``` text
├── app.............. Точка входа в мобильное приложение
│   ├── MainWindow.... Координирует всю навигацию в приложении
│   └── MainNavBar......... Нижнее навигационное меню
├── core......... Независимая логика проекта/компонентов
│   ├── datastore.......... Логика для сохранения мелких локальных файлов
│   ├── nav....... Логика для навигации
│   ├── data....... Бизнес логика
│   ├── domain....... Прослойка между UI и бизнес логикой для упрощения взаимодействия
│   ├── ui
│   │   ├── components.. Базовые компоненты для всего UI
│   │   ├── ui.. Логика взаимодействия с состояниями, абстракция для MVI
├── features....... Все экраны разбиты на модуль-фичи
│   ├── auth.......... Фича авторизации
│   ├── home.......... Фича главного экрана
│   ├── search.......... Фича поиска
│   ├── fave.......... Фича избранного
│   ├── settings.......... Фича настроек
│   ├── clip.......... Фича клипов
│   ├── quiz.......... Фича теста
│   ├── splash.......... Фича сплэш экрана
├── gradle-plugins.......... Convention gradle плагин для проброса зависимостей между модулями
└── theme............ Тема приложения
```

## Интерфейс авторизации

### Тёмная тема

<img width="800" alt="image" src="https://github.com/DenisPopkov/RusseLiteratureClient/assets/57343209/2aab78ff-327d-4d79-b31e-f0abb7f7a0b3">

### Светлая тема

<img width="800" alt="image" src="https://github.com/DenisPopkov/RusseLiteratureClient/assets/57343209/7aabb36c-ba03-4992-8d6d-ec3951670f2f">

## Интерфейс основной части приложения

### Тёмная тема

<img width="800" alt="image" src="https://github.com/DenisPopkov/RusseLiteratureClient/assets/57343209/665d192b-e0c2-431a-9900-0646668f4b43">

### Светлая тема

<img width="800" alt="image" src="https://github.com/DenisPopkov/RusseLiteratureClient/assets/57343209/0bb9b10d-fed4-4366-8e8c-b5a403bcb6c7">

## Интерфейс флоу вертикальных клипов

### Тёмная тема

<img width="800" alt="image" src="https://github.com/DenisPopkov/RusseLiteratureClient/assets/57343209/0433f7c0-c667-4bc7-ad1e-e4e7c2f2db33">

### Светлая тема

<img width="800" alt="image" src="https://github.com/DenisPopkov/RusseLiteratureClient/assets/57343209/58002131-c195-4ab5-b4e2-bb6157d49ba3">
