# Book Store Application
Technologies  Used
- Spring Boot
- H2 Database
- Spring Security
- JUnit Testing
- AOP for Logging
## Functions
- Sign up/login as a publisher or user
-  Search a list of books
-  Search the details about the specific book
- Insert the book as a publisher

### Step 1 : Endpoints

#### localhost:8080/home : Home Page
#### localhost:8080/signUp : Sign Up

#### localhost:8080/publisher/newBook : Adding book
#### localhost:8080/customers : List of customers
#### localhost:8080/books/all : List of books
#### localhost:8080/books/details?name : Details of specific book
#### localhost:8080/books/paging/1 : List of books by page
#### localhost:8080/publisher/update : Update book


## Running app
- docker build -t bookstore .
- docker run --name bookstore -d -p 8003:8080 bookstore


## Contacting
[LinkedIn](https://www.linkedin.com/in/elgiz-zeynalov-172265166/)