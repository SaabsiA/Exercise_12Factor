# 12 Factor App
Here you can find the description on how and which 8 of the 12 Factors where implemented.

## Codebase
Create a local Repository (git init) for the project and connect it to a remote Repository (github, ...). Then everyone can push and pull from 
the same Codebase and it can always be up-to-date

## Dependencies
The dependencies are handled via maven, and all dependencies for the project have to be divined in the pom.xml.

## Config
The configuration is handled in the application.yml file. It is possible to either hard-code the connection url, etc. in this file or use 
environment variables to make it more flexible. As docker is used for the database connection, you need to make sure you have the correct 
variables for your docker connection defined.

## Backing services
This service uses a MySql database (via Docker), but this can be changed in the application.yml, there the connection URL is defined and can be 
changed to any other DB Connection. The structure must still be the same as in the models. A JPA Repository is used and the columns are defined 
in the classes (/models).

## Build, release, run
To change the behaviour of the service (after it was already started), the service needs to be stopped and started again. The data from the 
config file (application.yml) is loaded after the build (so if there is an issue with the config file it will only be noticed once the service 
started running and not during the build).
There are also tools to support this process, for Java Jenkins or Gradle could be used as a tool.

## Processes
The Service gets all necessary data from the DB, if for example another user uses the GUI at the same time and saves something first, another 
user tries to save the same thing, the second user should get an exception.

## Port Binding
In the application.yml define the Port you want to use for your service. If you have a Gateway and want to use random ports for the services, 
define the port with 0. Otherwise just define the exact Port number.

## Concurrency

## Disposability

## Dev/prod parity

## Logs
This Service writes its log messages via lombock. This plugin handles the logging and writes the messages in the console (basically stdout) and 
can simply be added to a class with the @Log Annotation

## Admin processes
