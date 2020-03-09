# Note Backed App

The Note App will allow the user to create, keep, modify and  delete their profile, notes and contributors for a note; notes can be set as private, public read or public read and write, futhermore, you may add contributors to a note and allow him/her read or read and write, the security was made using spring security. This program is still in its early development state, for now just the backend was implemented and many of the features need futher work. For now Its just for testing.

## Getting Started
There are two ways of doing it: Docker(easier, but can only test) and running in an IDE(harder, but you may modify the code).

### The Easy way:
Download this project to your preferred folder.

#### What you will need to install:
* [Docker](https://www.docker.com/);
* [Postman](https://www.getpostman.com)

#### Runing:
* Running the containers:
	First make sure mysql isn't running in your machine:
	```
	service mysql stop
	```

	On terminal, start an mysql 8.0 container:
	```
	docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=test -e MYSQL_USER=sa -e MYSQL_PASSWORD=password -d mysql:8.0
	```

	Wait for some seconds and verify if it is running:
	```
	docker inspect mysql-standalone
	```

	The response must contain near to the beginning:  
	```
	"State": {
		    "Status": "running",
		    "Running": true,
		    "Paused": false,
	```
	And close to the end must contain an "IPAddress" not empty
	```
		"IPAddress": "172.17.0.2",
	```

	Start the noteApp container:
	```
	docker run -p 8080:8080 --name backend-noteapp --link mysql-standalone:mysql -d leonardo329cf/backend-noteapp

	```
	Wait for some seconds and verify if it is running:
	```
	docker inspect backend-noteapp
	```

	The response must contain the same fields as the mysql container.


* Open Postman:
Go to import and find NoteAppBackend folder and select "NoteApp test.postman_collection.json"  
Go to Note App Test -> User class related tests -> findAll  
Click in "Send"

* After you finish testing make sure to stop and remove the containers:
```
docker stop backend-noteapp
docker rm backend-noteapp
docker stop mysql-standalone
docker rm mysql-standalone
```


### The Harder way
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

#### What you will need to install:
* [Java 11 SDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html);
* [Spring Tools Suite 4 for Eclipse](https://spring.io/tools);
* [Postman](https://www.getpostman.com);
* [MySQL 8.0 Community Server](https://dev.mysql.com/downloads/);
	* [Getting strated with MySQL](https://www.mysqltutorial.org/getting-started-with-mysql/)
* [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) (Optional)

#### Running the code:
* You will need to clone this repository to your spring tool suite workspace's folder:
	* Open and terminal in the mentioned folder and run:
	```
	git clone https://github.com/leonardo329cf/NoteAppBackend.git
	```
	Or just download the project to the folder.
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
* [Docker](https://www.docker.com/) - Conteinarizing;


## Contributing

Go to [CONTRIBUTING.md](https://github.com/leonardo329cf/NoteAppBackend/blob/master/markdownRelated/CONTRIBUTING.md) for the full documentation of the code and how to contribute.


## Authors

* **Leonardo Cardozo Ferreira** - [leonardo329cf](https://github.com/leonardo329cf)


## License

This project is licensed under the GNU GPL 3.0
