# FreeToGame Android App

## Descripción del Proyecto

FreeToGame es una aplicación Android que permite a los usuarios explorar, buscar y obtener información detallada sobre diversos juegos gratuitos disponibles en diferentes plataformas.
La aplicación muestra una lista de juegos, permite filtrar por categorías y ofrece detalles completos de cada juego, como la descripción, la plataforma, el editor y el desarrollador.

### Tecnologías Utilizadas
- **Kotlin**: Lenguaje principal para el desarrollo de la aplicación.
- **Jetpack Compose**: Framework moderno para diseñar la interfaz de usuario de la aplicación.
- **Hilt (Dagger)**: Para la inyección de dependencias en el proyecto.
- **Retrofit**: Para hacer llamadas HTTP y consumir la API de juegos.
- **Room**: Base de datos local para almacenar y consultar los juegos descargados.
- **Coroutines y Flow**: Para manejar la concurrencia y las emisiones de datos de manera asíncrona.

## Características
- Listado de juegos gratuitos con imágenes y descripciones.
- Filtro por categorías y búsqueda por palabras clave.
- Detalle de cada juego, incluyendo descripción, editor, plataforma, género y enlace para abrir en el navegador.
- Persistencia de datos usando Room para mantener los juegos descargados disponibles offline.

## Requisitos Previos
Antes de comenzar, asegúrate de tener instaladas las siguientes herramientas:
- Android Studio última versión
- Android SDK
- Java 11 o superior

## Configuración Inicial del Proyecto
Este proyecto requiere acceso al SDK de Android. Asegúrate de tener instalado Android Studio y de configurar un archivo `local.properties` en la raíz del proyecto con la ruta a tu SDK de Android.

Ejemplo del archivo `local.properties`:

```properties
sdk.dir=/ruta/a/tu/android/sdk
```

## Instalación
Sigue estos pasos para ejecutar el proyecto localmente:

1. **Clona el repositorio**

   ```bash
   git clone <URL_DE_TU_REPOSITORIO>
   ```

2. **Abre el proyecto en Android Studio**

3. **Sincroniza las dependencias**
   Android Studio sincronizará automáticamente las dependencias especificadas en los archivos `build.gradle`.

4. **Ejecuta la aplicación**
   Selecciona un dispositivo virtual o físico y presiona el botón "Run" para compilar y ejecutar la aplicación.

## Arquitectura
El proyecto sigue el patrón MVVM (Model-View-ViewModel) para garantizar una separación clara de la lógica de negocio, los datos y la interfaz de usuario.

- **Model**: Representa los datos (entidades de Room y respuestas de la API).
- **View**: Composables de Jetpack Compose que representan la UI.
- **ViewModel**: Contiene la lógica de negocio y maneja la comunicación con el repositorio.

## Navegación
La aplicación usa la librería de **Jetpack Navigation** para manejar la navegación entre pantallas. Esto permite una navegación clara y sencilla a través de las diferentes secciones de la app, como el listado de juegos, los detalles y la edición.

## Pantallas Principales
- **Listado de Juegos**: Muestra todos los juegos disponibles con opciones de filtro y búsqueda.
- **Detalle del Juego**: Proporciona información detallada sobre un juego, incluyendo un botón para abrir el juego en el navegador.
- **Edición del Juego**: Permite editar los datos del juego, como título, descripción y categoría.



## Contacto
Si tienes alguna pregunta o sugerencia, no dudes en contactarme:
- **Nombre**: Rigoberto Torres
- **Correo Electrónico**: [rigoberto.to@gmail.com]

