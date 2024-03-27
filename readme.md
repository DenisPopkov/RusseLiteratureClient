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
│   ├── ui
│   │   ├── components.. Базовые компоненты для всего UI
│   │   ├── ui.. Логика взаимодействия с состояниями, абстракция для MVI
├── features....... Все экраны разбиты на модуль-фичи
│   ├── auth.......... Фича авторизации
│   ├── home.......... Фича главного экрана
│   ├── search.......... Фича поиска
│   ├── fave.......... Фича избранного
│   ├── settings.......... Фича настроек
│   ├── splash.......... Фича сплэш экрана
├── gradle-plugins.......... Convention gradle плагин для проброса зависимостей между модулями
└── theme............ Тема приложения
```

## Интерфейс авторизации

<img width="800" alt="image" src="https://github.com/DenisPopkov/RusseLiteratureClient/assets/57343209/966a4fd2-88d5-4ecd-89b2-b90d371417a0">

## Интерфейс основной части приложения

<img width="800" alt="image" src="https://github.com/DenisPopkov/RusseLiteratureClient/assets/57343209/2cba2176-16c0-48fe-b745-c7ad7bd39d26">

## Интерфейс флоу вертикальных клипов

<img width="800" alt="image" src="https://github.com/DenisPopkov/RusseLiteratureServer/assets/57343209/3916a678-889a-4c73-a284-52392ac4bde7">