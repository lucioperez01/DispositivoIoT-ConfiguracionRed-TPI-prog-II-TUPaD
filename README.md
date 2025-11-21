[README .md](https://github.com/user-attachments/files/23666034/README.md)
# <img src="https://bignews.ar/wp-content/uploads/2023/05/utn-nacional.jpg" width="200">  
# **Trabajo Práctico Integrador – Programación II (UTN)**  
### *Dispositivos IoT – JDBC – CRUD – Menú de Consola*

![Java](https://img.shields.io/badge/Java-17-orange?style=flat&logo=java)  
![MySQL](https://img.shields.io/badge/MySQL-Base%20de%20Datos-4479A1?style=flat&logo=mysql)  
![NetBeans](https://img.shields.io/badge/IDE-NetBeans-blue?style=flat&logo=apache-netbeans)  

---

## 📌 **Descripción del Proyecto**

🎥 **Presentación en video y documentacion:** [https://drive.google.com/drive/folders/1GZjmk3FO-h7m5-9CJsfujR49Vq2YAQDX?usp=drive_link]

Este repositorio contiene el **Trabajo Integrador de Programación II**, cuyo objetivo es desarrollar una aplicación de consola en Java que administre **Dispositivos IoT** y su **Configuración de Red**, utilizando:

- Programación orientada a objetos (POO)  
- Conexión a base de datos mediante **JDBC**  
- Patrón **DAO** (Data Access Object)  
- Gestión transaccional  
- **CRUD completo** + búsqueda por Serial  
- Menú interactivo por consola  
- Eliminación lógica  

La aplicación permite registrar dispositivos IoT, asignarles configuraciones de red y realizar operaciones de administración utilizando MySQL.

---

## 🗄️ **Base de Datos**

### ✔ Motor utilizado: **MySQL 8+**  
### ✔ Script de creación:

```sql
CREATE TABLE dispositivo_iot (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    serial VARCHAR(100) UNIQUE NOT NULL,
    modelo VARCHAR(100),
    ubicacion VARCHAR(100),
    firmware_version VARCHAR(50),
    eliminado BOOLEAN DEFAULT FALSE
);

CREATE TABLE configuracion_red (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dispositivo_id BIGINT NOT NULL,
    ip VARCHAR(50),
    mascara VARCHAR(50),
    gateway VARCHAR(50),
    dns_primario VARCHAR(50),
    dhcp_habilitado BOOLEAN,
    eliminado BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (dispositivo_id) REFERENCES dispositivo_iot(id)
);
```

---

## ⚙️ **Tecnologías Utilizadas**

- **Java 17**
- **NetBeans 17**
- **MySQL 8**
- **JDBC**
- **POO / Patrón DAO**
- **Transacciones SQL**
- **Menú de consola interactivo**

---

## 🚀 **Funcionalidades Principales**

### 📌 CRUD Completo de Dispositivos IoT
- ✔ Crear  
- ✔ Listar  
- ✔ Buscar por ID  
- ✔ Buscar por Serial  
- ✔ Actualizar  
- ✔ Eliminación lógica  

### 📌 Configuración de Red
- ✔ Crear configuración ligada al dispositivo  
- ✔ Búsqueda por ID de dispositivo  
- ✔ Integración transaccional

### 📌 Menú Interactivo
Implementado en **AppMenu.java**:
```
1 - Crear dispositivo + config
2 - Listar dispositivos
3 - Buscar por ID
4 - Buscar por Serial
5 - Actualizar dispositivo
6 - Eliminar dispositivo
0 - Salir
```

---

## ▶️ **Cómo Ejecutar el Proyecto (NetBeans)**

1. Clonar el repositorio o descargar ZIP.  
2. Abrir el proyecto en **NetBeans**.  
3. Configurar la conexión MySQL en `DatabaseConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/iot_db";
private static final String USER = "root";
private static final String PASSWORD = "1234";
```

4. Crear la base de datos y ejecutar el script SQL.  
5. Compilar el proyecto:  
   **Run → Clean and Build**  
6. Ejecutar:  
   **Run → Run Project**

---

## 🧪 Ejemplo de Uso

```
================================
      MENÚ DISPOSITIVOS IoT     
================================
1 - Crear dispositivo + configuración
2 - Listar dispositivos
3 - Buscar dispositivo por ID
4 - Buscar dispositivo por SERIAL
5 - Actualizar dispositivo
6 - Eliminar dispositivo (lógico)
0 - Salir
================================
Opción:
```

---

## 👥 **Integrantes**

| Nombre | Rol |
|--------|------|
| **Danilo Peirano** | Programación general |
| **Pérez Lucio** | DAO + JDBC |
| **Valentin Piñeyro** | SQL + pruebas |
| **Pérez Leandro** | Documentación + menú |
| **Grupo:** 46_DispositivoIoT_ConfiguracionRed | **Comisión:** 5 |

---

## 📄 **Licencia**

Proyecto académico para la **Universidad Tecnológica Nacional**  
Materia: **Programación II**  
Año **2025**


> **Repositorio del trabajo:**  
> [https://github.com/lucioperez01/DispositivoIoT-ConfiguracionRed-TPI-prog-II-TUPaD.git]
---
