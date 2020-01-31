# Note Backed App

The Note App will allow the user to create, keep, modify and  delete their profile, notes and contributors for a note. This program is still in its early development state, for now just the backend was implemented and many of the features need futher work.

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

### What is still missing:
* Security:
	* Login;
	* Authentication;
	* Autorization;
	* Roles;
* WebSocket;
* Frontend web and mobile;


## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### What you will need to install:
* [Java 11 SDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html);
* [Spring Tools Suite 4 for Eclipse](https://spring.io/tools);
* [Postman](https://www.getpostman.com);
* [MySQL 8.0 Community Server](https://dev.mysql.com/downloads/);
	* [Getting strated with MySQL](https://www.mysqltutorial.org/getting-started-with-mysql/)
* [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

### Running the program:
* You will need to clone this repository to your spring tool suite workspace's folder:
	* Open and terminal in the mentioned folder and run:
	```
	git clone https://github.com/leonardo329cf/NoteAppBackend.git
	```
* Start MySQL server:
```
sudo service mysql start
```

* Go to Spring Tool Suite 4:
Click on File/Open Projects from File System  
Select the folder you cloned  

Run configuration  
Arguments  
On "VM arguments" paste: -Djava.security.egd=file:/dev/./urandom  

The default port is the 8080, you can configure local.port in the:  
NoteAppBackend/src/main/resources/application-test.properties

Right click on /src/main/java/com/leonardocardozo/notesappbackend/NoteAppBackendApplication.java  
Run as Springboot App

* Open Postman:
Go to import and find NoteAppBackend folder and select "NoteApp test.postman_collection.json"  
Go to Note App Test -> User class related tests -> findAll  
Click in "Send"  


## Running the tests

The folders "xxxx class related tests" use the same mocked data, and they will interfere with each other.
* On Postman:
You can run the folders:  
User class related tests  
Restart the note application  
Note class related tests  
Restart the note application   
Contribution class related tests  
Restart the note application   


## Built With

* [Spring](https://spring.io/) - The framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Eclipse](https://www.eclipse.org/) - IDE

## Contributing

The [CONTRIBUTING.md](https://github.com/leonardo329cf/NoteAppBackend/blob/master/CONTRIBUTING.md) is under construction.


## Authors

* **Leonardo Cardozo Ferreira** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)


## License

This project is licensed under the GNU GPL 3.0

## Acknowledgments
