# 12 Factor Exercise

## Docker
For this service Docker with mysql was used. The complete configuration for the Docker Container was done via the Docker run-config in IntelliJ.

## Service
This is a simple service, which displays Blog Entries with some data. The Blog Entrys are stored in a DB and the authors of the Entry are 
stored in a different Database.
To run this service, the docker container must be running, or the DB Connection must be adapted to use the correct one (currently saved in 
env variables of the Service run-config - system env variables were not working correctly)
When the docker container is running the service can just be started.
