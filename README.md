# Gestión de partes #

Está aplicación está diseñada para su uso en una empresa de transportes. Permite la gestión de centros de trabajo, vehículos, trabajadores y partes.

[¿Cómo funciona?](#cómo-funciona)

[Requisitos](#requisitos)

[Pasos para la instalación](#pasos-para-la-instalación)

[Configuración](#configuración)

## ¿Cómo funciona? ##

Existen dos tipos de trabajadores:

* Transportistas 
	
	Crean los partes de trabajo según los repartos que hayan realizado en su jornada laboral. Además pueden adjuntar los gastos de gasoil o dietas, entre otros, y dejar constancia de las incidencias recogidas.
* Administrativos
	
	Gestionan altas y bajas de centros, trabajadores y vehículos. También validan que los transportistas hayan rellenado los partes correctamente y no hayan excedido su jornada laboral.
		
## Requisitos: ##
	
	Una base de datos Oracle DataBase 12c o superior.

## Pasos para la instalación: ##

##### ¡OJO! El orden de ejecución es importante. #####
	
1. Ejecutar el [script](MODELO_FÍSICO/SCRIPT_FISICO.ddl) sobre la base de datos.

Este script crea las tabla necesarias y la configuración de usuario, contraseña y servidor que va a utilizar la aplicación.
Si necesitas cambiar estos datos, puedes consultar [aquí](#configuración) como hacerlo.
	
2. Ejecutar el script de [procedimientos](PL_SQL/Pl-sql.sql) sobre la base de datos.

3. Iniciar el ejecutable [Proyecto](Proyecto/dist/Proyecto.jar).

Si quieres cambiar la ubicación de este archivo debes mover también la carpeta [lib](Proyecto/dist) a la misma carpeta que el ejecutable.
Es recomendable que crees un acceso directo, por ejemplo a tu escritorio.

## Configuración ##

El script de creación configura automáticamente la conexión de la aplicación con la base de datos.

En caso de que necesites otra configuración diferente, sigue estos pasos:

* Cambios en el [script de creación](MODELO_FÍSICO/SCRIPT_FISICO.ddl)

```sql
-- USER SQL
CREATE USER Aplicacion IDENTIFIED BY a12345Abcde ;
--Cambiar Aplicacion por el nuevo usuario y a12345Abcde por la contraseña

-- ROLES
GRANT "DBA" TO Aplicacion ;	
GRANT "CONNECT" TO Aplicacion ;
GRANT "RESOURCE" TO Aplicacion ;
--Cambiar Aplicacion en las tres líneas por el nuevo usuario.
```

* Cambios en java en la clase [ControladorBaseDatos](Proyecto/src/Modelo/ControladorBaseDatos.java)

```java
public static void conectar(){ 
        try{ 
            Class.forName("oracle.jdbc.OracleDriver"); 
            String login = "Aplicacion"; //Cambiar por el usuario creado en la base de datos.
            String pass = "a12345Abcde"; //Cambar por la contraseña usada en la base de datos.
            String url = "jdbc:oracle:thin:@10.10.10.9:1521:db12102"; //Cambiar @10.10.10.9 por la ip del servidor y db12102 por el nombre de la base de datos.            
            conexion = DriverManager.getConnection(url,login,pass); 
            conexion.setAutoCommit(true); 
        }catch(ClassNotFoundException | SQLException ex){ 
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage()); 
        }  
```

## Uso de la aplicación ##

La aplicación se inicia en la ventana login. 

![Ventana de login](Imágenes/login.JPG)

Si es la primera vez que la utilizas, usa las siguientes credenciales para iniciar sesión:

    Usuario: admin
	Contraseña: aaaaa
	
Una vez dados de alta los usuarios, cada uno utilizará sus propias credenciales. También es recomendable borrar este usuario y sus datos relacionados. Puedes ver cómo hacerlo [aquí]().

Dependiendo de si el usuario es _Transportista_ o _Administrativo_ se iniciará la ventana correspondiente.

### Transportista ###

En el caso de _Transportista_ se inicia la ventana _Parte_.

![Ventana parte](Imágenes/ventanaparte.JPG)

Aquí nos encontramos dos opciones:

* Nuevo parte. El formulario aparece vacío.
* Parte abierto. El formulario aparece completado con los datos que hemos guardado previamente.

Para añadir un vehículo pulsamos en el botón _Buscar_ y aparecerá la ventana _Buscar vehículo_.

![Ventana vehículo](Imágenes/buscarvehiculo.JPG)

Seleccionamos el vehículo pinchando en la tabla y a continuación en _Guardar_. Se cerrará esta ventana y volveremos a la anterior.

Para añadir repartos pulsamos el botón _Añadir reparto_ y aparecerá la ventana _Reparto_.

![Ventana reparto](Imágenes/ventanareparto.JPG)

Elegimos la hora de inicio y la hora de fin, escribimos el número de albarán y pulsamos en _Guardar_. 

En caso de equivocación pinchamos en el reparto correspondiente en la tabla y pulsamos en _Borrar reparto_.

Podemos guardar el parte para continuar más tarde en _Guardar_ o cerrarlo definitivamente en _Cerrar parte_.

### Administrativo ###

En el caso de _Administrativo_ se inicia la ventana _Administrativo Home_.

![Ventana Administrativo Home](Imágenes/adminhome.JPG)

En caso de que exista algún aviso se mostrará en el cuadro. Una vez leído se puede borrar seleccionandolo en la tabla y pulsando en el botón _Borrar Aviso_.

En el menú podemos elegir la acción que queremos realizar.

* Centros / Nuevo centro

![Ventana nuevo centro](Imágenes/centroformulario.JPG)

Una vez rellenado el formulario pulsamos en _Guardar_.

* Centros / Listado centros

![Ventana Listado centros](Imágenes/centrolistado.JPG)

Seleccionamos en la tabla el centro a consultar y pulsamos una de las opciones:

	* Ver trabajadores

En la tabla inferior se muestra un listado con los trabajadores de ese centro.

	* Ver detalle

![Ventana centro detalle](Imágenes/centrodetalle.JPG)

Se muestran los datos referentes a ese centro.

** Editar

Se abre un nuevo formulario que permite hacer los cambios necesarios y guardarlos.

** Borrar

Se borra el centro seleccionado.

* Trabajadores / Nuevo trabajador


















































