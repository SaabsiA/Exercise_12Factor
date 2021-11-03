# 12 Factor Exercise

## Github
First Clone the Repository from Github:
- [Link to Repo](https://github.com/SaabsiA/Exercise_12Factor)
Or Clone with the following command:
```gh repo clone SaabsiA/Exercise_12Factor```

## Docker
For this service Docker with mysql was used. The complete configuration for the Docker Container was done via the Docker run-config in IntelliJ.
The Docker Compose file must run, before the Service can be started. Environment Variables must be added, since the Docker File uses Variables 
to configure the DB Connection locally. The Following Variables must exist:

- *DB_ROOT_PWD* = This is the root password which is used for the Root user of the MySQL Database
- *DB_USER* = This is the user which connects to the database
- *DB_PWD* = This is the password of the connecting user
- *DB_CONN* = This is the Connection/Image which is used (for this service it should be mysql in this variable)
- *DB_HOST* = This is the host for the service (normally it should be localhost if there is no server defined)
- *DB_PORT* = This is the port which is used for the DB Connection (for this example it is 3307, this is defined in the docker file as well)
- *DB_DATABASE* = This is the name of the database which is used in the service

If all the Variables are defined, the Docker Container can be started from the Root Folder with the command ````docker-compose up````

## Service
This is a simple service, which displays Blog Entries with some data. The Blog Entrys are stored in a DB and the authors of the Entry are 
stored in a different Database.
To run this service, the docker container must be running, or the DB Connection must be adapted to use the correct one (see the Env. Variables 
which must be defined)
When the docker container is running the service can just be started. Once the Server is started, you can have a look at the Website on the 
defined Port (here 8080, which is also the default port). On the Website you have a list of all entries and can look at the Details of an 
entry by clicking on it. It is also possible to add new entries by clicking the button at the top of the Website.
