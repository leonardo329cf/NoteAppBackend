# Note Backed App

The Note App will allow the user to create, keep, modify and  delete their profile, notes and contributors for a note; notes can be set as private, public read or public read and write, futhermore, you may add contributors to a note and allow him read or read and write, the security was made using spring security. This program is still in its early development state, for now just the backend was implemented and many of the features need futher work.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### What you will need to install:
* [Java 11 SDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html);
* [Spring Tools Suite 4 for Eclipse](https://spring.io/tools);
* [Postman](https://www.getpostman.com);
* [MySQL 8.0 Community Server](https://dev.mysql.com/downloads/);
	* [Getting strated with MySQL](https://www.mysqltutorial.org/getting-started-with-mysql/)
* [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

### Running the code:
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


## Running functional tests

The folders "xxxx class related tests" use the same mocked data, and they will interfere with each other, you must restart the note application before running other folder or run in the following order: Contribution class related tests, Note class related tests, User class related tests.
* On Postman:
You can run the folders:  
	* User class related tests;  
	* Note class related tests;  
	* Contribution class related tests.  


## Built With

* [Java](https://www.java.com/) - Programming language;
* [Spring](https://spring.io/) - The framework used;
* [Maven](https://maven.apache.org/) - Dependency Management;
* [Eclipse](https://www.eclipse.org/) - IDE;
* [Postman](https://www.getpostman.com) - Automated tests;
* [MySQL](https://www.mysql.com/) - Database;


## Contributing

Go to [CONTRIBUTING.md](https://github.com/leonardo329cf/NoteAppBackend/blob/master/markdownRelated/CONTRIBUTING.md) for the full documentation of the code and how to contribute.


## Authors

* **Leonardo Cardozo Ferreira** - [leonardo329cf](https://github.com/leonardo329cf)


## License

This project is licensed under the GNU GPL 3.0

## Acknowledgments

* **[PurpleBooth](https://github.com/PurpleBooth)** - for the readme.md base template
