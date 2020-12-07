# Springbooks scenario

It is requested to implement a web application with a list of books and reviews of each book. This application must has the following functionalities:

* The webpage will be able to manage many books.
* In the main page will appear the book titles.
* Every book title will be a link that when it will be clicked, a webpage will be open where it will be shown the book content (title, summary, author, publisher and publication year).
* In the main page will be a link that when it will be clicked, a webpage to create a new book will be shown.
* Every book could have comments associated that they will be shown below of its content with a score between 0 and 5.
* In order to be able to create a comment, below of the book content will be shown a form to fill user, comment and score.
* When a user has previously created a comment and is going to create another one, its name will appear preloaded in the form.
* Every comment will be shown with a delete button that will allow to delete it.
* There is not any type of access control. Anyone could create new boocks or comments. Anyone could delete any comment.

In addition to the web interface, the application will also export a REST API. This REST API
will have the following operations:

* Retrieve a list with the id and the title of each book (but not the other attributes of a book).
* Retrieve the information from a book (comments included).
* Create a book.
* Create a comment associated to a book.
* Delete a comment.

From a technical point of view, it will be taken in account the following aspects:

* Information is stored in memory, any persistence will be implemented.
* Web application will be implemented with Java 8 (or higher) and SpringBoot 2.4.0 (or higher).
* Is not needed to worry about the design of the web, it is enough that it is functional.
* Make sure that two simultaneous requests to manage comments on the same book do not have concurrency problems.
* REST API must comply with maturity level 2 and the format of the URLs must identify resources, not actions. The name of the resource should appear in plural and in English.