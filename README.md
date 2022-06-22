#Picto-AI

Picto-AI es un Sistema de Texto Predictivo Para Lenguaje de Pictogramas pertenieciente al Trabajo Final de Grado - Prototipado Tecnológico de la carrera: Licenciatura en Informática de la Universidad Siglo 21 de Maria Cecilia Gabelloni.

##Demo

Se podrá acceder a un video de una demo del prototipo en el siguiente [Link](www.link.aca)

##Uso

El sistema puede utilizarse tanto desde la web como también acceder al backend embebido desde cualquier otra página y/o teclado virtual.

---
Para instalar el sistema completo se debera clonar el repositorio con 

```
git clone https://github.com/Bashie/predictivo.git
git clone https://github.com/Bashie/predictivoUI.git
```

En ambas carpetas creadas se deberá correr el siguiente comando para generar los ejecutables:

```
mvn clean install
```

---
Para embeber la inteligencia artificial en su propia web deberá comunicarse con los webservices predictivos:

```
/users/authenticate   				{ "username": username, "password": password }
/users/register   				{ "username": username, "password": password, "nombre": nombre, "apellido": apellido }
/pictogramas/{tipo}   				'Bearer ' + Token
/pictogramas/{tipo}/{categoria}   		'Bearer ' + Token
/categorias/{tipo}   				'Bearer ' + Token
/prediccion   					{ "fraseUsada": pictos, "usuarioId": usuarioId } 'Bearer ' + Token
```

##Condiciones de uso
El sistema enviará una vez por dia la información de uso (sin datos de usuario) al datawarehouse principal. De éste mismo, descargará los últimos pesos de los vectores de IA apra el uso del mismo.



