# Springbooks scenario

It is requested to implement a new version of the application that was implemented in ```01-web-restapi``` branch of current repo that covered this [scenario](https://github.com/david-rojo/cloudApps-spring-books/blob/01-web-restapi/doc/scenario.md).

These are the requirements for new version:

* Add persistency with Spring Data and MySQL.
* Delete the web inteface:
  * Delete static files, templates and it dependency located in ```pom.xml```
  * Delete web controller
  * Delete any web resource
* Users that create comments are now an entity (User) with properties nick and email. Nick must be unique.
* New REST API operations:
  * CRUD endpoints to manage users: create, read, update email and delete operations. Only a user without comments can be deleted.
  * Include one endpoint to retrieve all comments of a specific user. In this case, every comment must contains the id of the book that comment.
* Changes in existing REST operations:
  * When a book is retrieved, all its comments must be returned. The comments must contain comment text and user nick and email.
  * When a comment is created, the nick of the user must be provided.
* Documentation:
  * Postman collection must be updated with examples for testing the REST API.
  * OpenAPI documentation must be updated, including the new operations.
