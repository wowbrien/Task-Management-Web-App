# Task-Management-Web-App

# General Information
*	App runs on ‘http://localhost:8090/’
*	List of users register and their passwords on start-up:
    * User = test; Password = pwd123 – Has 2 tasks
    * User = test2; Password = pwd – Has no tasks
    * User = test3; Password = pwd2 – Has 1 task
# Design Approaches
*	Web app is RESTFUL Spring Boot application.
*	Technologies used: 
    * Frontend rendering; Thymeleaf, 
    * WebApp; Spring Web
    * Testing; Junit, 
    * In Memory DB; H2, 
    * DB interaction; Spring JPA
    * Security; Spring Security


I chose Spring boot as it is incredibly lightweight and easy to deploy, as well as myself having quite a bit of experience with it. Using the spring initializr I was able to create a project with all my dependencies in no time at all and allowed me to get stuck into development much quicker. 
Junit makes unit testing easy to do as all it requires is an annotation to indicate which your test methods are, and if you wish to run them with spring boot.
Thymeleaf is designed to work very well with Spring and makes mapping backend objects with the frontend interface very easy to do. It is not as ideal for dynamic content, but I chose not to use a JS framework in conjunction with the front end as the requirements for the UI seemed quite basic and did not require this.
Spring security is a dependency that can be added from the initializer website and streamlines the access management so that you only need to indicate which your login models are and will actually handle the login request mapping for you. It comes with a default login page, but you can overwrite it with whatever you want, the main benefit is it automatically requiring authentication from a user (they have to login) before they can successfully navigate to any other page.
# Design Patterns
The main design pattern that was implement was the Data Access Object (DAO) pattern. This is a design pattern where you abstract the application layer from the data persistence layer. In my code you can see the interface UserAccountDAO and its implementation are responsible for any interaction with the relational in memory Database. As such the RestController responsible for handling the API calls does not directly interact with the DB at any point.

# Extra Items
If I had more time the areas that I would focus on in order of importance are:
•	Testing Security – There could be more focus testing different ways that the website could be exploited whether through standard injections or redirection attacks.
•	Improving UI design – My primary focus for this project was to make an efficient and secure web application, as such I did not create a sophisticated modern design. This would have to be changed to have an appropriate appearance and ensure smooth user interactions.
# Clean Code
I’m a big believer in clean code in general development and believe that it should be a large focus. As such I have tried to follow the general naming conventions of methods, classes & variables so that they follow an appropriate typographical convention and are descriptive enough that it shouldn’t require additional comments. Spacing and indentation are also areas that I focus on as readability is a very important part of development, a very useful tool for this is a plugin called “SaveActions” that can be configured to automatically format your code whenever you save.

If you wish to give any feedback you can reach me at williamobrien900@gmail.com.
