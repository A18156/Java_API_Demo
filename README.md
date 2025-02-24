# Java_API_Demo
Project using Java Spring, this about CRUD, pagination, search API and generic CRUD API

* Constructure:
- Entity
- DTO
- Repository
- Service
- Usecase
- Controller
- Exception using ControllerAdvice

* DB:
- MySQL 

change application.properties file before run this code

  spring.application.name=[Your_project_name]
  spring.datasource.url= jdbc:mysql://localhost:3306/[Your_db_name]?createDatabaseIfNotExist=true&useSSL=false
  spring.datasource.username=root
  spring.datasource.password=[your_password]
  spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
