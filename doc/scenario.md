# Springbooks scenario

It is requested to implement a new version of the application that was implemented in ```02-restapi-db``` branch of current repo that covered this [scenario](https://github.com/david-rojo/cloudApps-spring-books/blob/02-restapi-db/doc/scenario.md).

These are the requirements for new version:

* Use https instead of http. A self signed certificate is enough and it can be generated.
  
  ```
  sudo keytool -genkey -keyalg RSA -alias selfsigned -keystore keystore.jks -storepass password -validity 360 -keysize 2048
  ```
  
* There are two types of users: registered users and not registered (anonymous)
* Registered users can execute any operation.
* Not registered users only can:
  * Retrieve a list with the id and the title of each book (but not the other information of the book, so also modify these method in order that registered users can retrieve all book information).
  * Create users.
* Define/provide needed endpoints for books management. These endpoints only can be executed by registered users.
* In these endpoints any CRUD operation can be executed. In case of books with comments delete, the comments will be deleted too in the database.
