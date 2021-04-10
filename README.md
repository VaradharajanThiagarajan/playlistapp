# Readme

This repository contains the code for Spring boot application which can be executed
in three environments listed below.

1) Standalone application in  any java IDE
2) Docker container.
3) Heroku cloud platform.

## Instructions for Execution in Docker container hosted in your local desktop( windows Powershell)

### command 1 - Define the ipaddress123 variable using the docker inspect command. in the below command <my-guestpostgres> is the container name.
### of postgres
$ipaddress123 = docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' <my-guestpostgres>

### command 2 - run the springboot application container from the docker image by passing two environment parameters -  ipaddress and application-docker.properties as profile.
docker run -d -p 1000:8080 -e ipaddress=$ipaddress123  -e "SPRING_PROFILES_ACTIVE=docker"  --name guest1 --rm guestservice:dev