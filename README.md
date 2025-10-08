# 🎟️ FGP_Actividad4
Aplicación web desarrollada con **Spring Boot 3**, **Thymeleaf**, **Spring Security** y **JPA (MySQL)** para la gestión de eventos y reservas de usuarios.

---

## 🚀 Descripción general
FGP_Actividad4 es una aplicación MVC que permite a los usuarios autenticarse, visualizar eventos activos, realizar reservas y gestionar accesos según el rol del usuario.

Incluye:
- Inicio de sesión y control de acceso por roles.
- Listado de eventos activos, cancelados y terminados.
- Gestión de reservas con control de aforo.
- Plantillas HTML dinámicas con Thymeleaf.
- Base de datos MySQL para persistencia de datos.

---

## 🧠 Tecnologías utilizadas
- ☕ **Java 21**
- 🌱 **Spring Boot 3.4.2**
- 🔐 **Spring Security 6**
- 🧩 **Spring Data JPA (Hibernate)**
- 🗃️ **MySQL 8**
- 🧰 **Maven**
- 🎨 **Thymeleaf + Bootstrap**
- ⚙️ **Lombok**
- 🔁 **Spring DevTools** (recarga automática en desarrollo)

---

## ⚙️ Instalación y ejecución

### 🔧 Requisitos previos
Asegúrate de tener instalado:
- [Java JDK 21+](https://adoptium.net/)
- [Maven 3.9+](https://maven.apache.org/)
- [MySQL 8+](https://dev.mysql.com/downloads/)
- Un IDE como Spring Tool Suite, IntelliJ IDEA o VS Code con extensiones de Java.

---

### 💾 Configura la base de datos
Crea una base de datos en MySQL llamada:
```sql
CREATE DATABASE eventos_bbdd;
