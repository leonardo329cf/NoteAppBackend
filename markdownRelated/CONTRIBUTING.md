# NoteAppBackend
## Contributing

Welcome and thanks for been interested in help.  
For any doubt or idea related to contributing, you may contact me by: leonardo329cf@gmail.com

### How you can contribute
* Identifying problems in the code;
* Implementing new features like:
	* Security(using Spring Security);
* Making a front-end:
	* Web;
	* Mobile;


### Pay attetion on:
* This program was develop using the design pattern Spring MVC, so if you are gonna modify it follow the pattern in the backend.
* Please document your contributions:
	* For new features and complex stuff write an UML or flowchart and a good description;
* Run tests before sending it in and add test to the "NoteApp test.postman_collection.json", it opens on Postman;


### Understanding the code:

#### Caracteristics:
* Design pattern: Spring MVC;
* Database: MySQL;
* Dependency manager: Maven;
* Object-relational mapping: JPA;
* Test tool: Postman.
	*"NoteApp test.postman_collection.json" is a collection of tests for Postman.


#### Detailing:
* Controllers may communicate with all of the services on services package;
* Services may communicate with all of the repositories of repositories package;
* Each service shouldn't return entities of other kind beside their own or their Util version:
	* UserService will return User class or UserUtil class;
* Exceptions related to Model or Repository are thrown in the service class;  
* UML for the entities:  
![UML for the entities](https://github.com/leonardo329cf/NoteAppBackend/blob/master/markdownRelated/NoteAppBackend-entitiesUml.jpg)    
* HTTP requests map:  
![HTTP requests map](https://github.com/leonardo329cf/NoteAppBackend/blob/master/markdownRelated/NoteAppBackend-HTTPmap.jpg)  
