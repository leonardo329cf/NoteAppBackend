# Contributing

Welcome and thanks for been interested in help.  
For any doubt or to make major changes, you may contact me by: leonardo329cf@gmail.com

## Current State:
### What is implemented:
* User related HTTP requests:
	* Find all users;
	* Find by username;
	* Find note by usename and name;
	* Insert user;
	* Update user;
	* Delete user
* Note related HTTP requests:
	* Find all notes;
	* Find note by id;
	* Find by username;
	* Find note by title and author;
	* Insert note;
	* Update note;
	* Delete note;
* Contribution related HTTP requests:
	* Find all contrbutions;
	* Find contributions by contributor_username;
	* Find contributions by note_id;
	* Find contributions by note_id and contributor_username;
	* Insert contribution;
	* Update contribution;
	* Remove contribution;
* Spring Security and access control:
	* Contributors may have permission for:
		* Read;
		* Read and write;
	* Notes permission are:
		* Author read and write;
		* Public read;
		* Public read and write.
	

### How you can contribute
* Identifying problems in the code;
* Implementing missing features or new;
* Making a front-end:
	* Web;
	* Mobile;
* Implementing the WebSocket;


## Pay attetion on:
* This program was develop using the design pattern Spring MVC, so if you are gonna modify it follow the pattern in the backend.
* Please document your contributions:
	* For new features and complex stuff write an UML or flowchart and a good description;
* Run tests before sending it in and add test to the "NoteApp test.postman_collection.json", it opens on Postman;


## Understanding the code:

### Caracteristics:
* Design pattern: Spring MVC;
* Database: MySQL;
* Dependency manager: Maven;
* Object-relational mapping: JPA;
* Test tool: Postman.
	*"NoteApp test.postman_collection.json" is a collection of tests for Postman.


### Detailing:
* Controllers may communicate with all of the services on services package;
* Services may communicate with all of the repositories of repositories package;
* Each service shouldn't return entities of other kind beside their own or their Util version:
	* UserService will return User class or UserUtil class;
* Exceptions related to Model or Repository are thrown in the service class;  
* UML for the entities:  
![UML for the entities](https://github.com/leonardo329cf/NoteAppBackend/blob/master/markdownRelated/NoteAppBackend-entitiesUml.jpg)    
* HTTP requests map:  
![HTTP requests map](https://github.com/leonardo329cf/NoteAppBackend/blob/master/markdownRelated/NoteAppBackend-HTTPmap.jpg)  
