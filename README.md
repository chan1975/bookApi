# bookApi
Api rest for book entity

# Prerequisitos
1. Para conectar spring boot a sql se necesita activar el protocoloo TCP/IP y configurar el puerto en la opcion IPAII agregando el pruerto 1433
2. Reinciar el servicio SQL Server
3. Tener instalado jdk 8

# Compilar Backend
Ejecutar los script sql que se encuentra en la ruta bookApi/sql/usar_table.sql y bookApi/sql/book_data_test.sql
Crear un ususario y base de datospara la conexion 
bd : userdb
user: test
pass: SQLtest
o en su defecto cambiar el fichero application.properties 

# Levantar middleware
3.1 Si se requiere levantar el servicio desde un editor como Intellij o Eclipse solo debe  ejecutar el metodo main de la clase BookApiApplication
3.2 Si se requiere levantar el servicio sin el editor primero hay que abrir una pantalla cmd y ejecutar el comando mvn clean install. Posteriormente dirijirse a la carpeta target y ejecutar por cmd java -jar 'nombre_del_jar_generado' 

