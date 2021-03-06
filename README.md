# Readme

This repository contains the code for Spring boot application which can be executed
in three environments listed below.

1) Standalone application in  any java IDE
2) Docker container.
3) Heroku cloud platform.

## API Specifications
Endpoint    | Method| Request Body| Response
------------|-------|-------------|----------
/playlist  |  POST  | {"name":"abc"}| 201
/playlist/playlist name |  GET | |["song1", "song2"]
/playlist/playlist_name|POST|"song1"|{"name":"playlist_name","songs":[...]}
/playlist/playlist_name|DELETE|"song1"|{"name":"playlist_name","songs":[...]}

## Instructions for Execution in Docker container hosted in your local desktop( windows Powershell)

### List of commands - Define the network and add the postgres as well as springboot application container to the network

    docker pull postgres
    docker network create --driver bridge playlistnetwork
    docker run --name my-playlistpostgres --network playlistnetwork  -e POSTGRES_PASSWORD=open -e POSTGRES_DB=playlistdb -p 5432:5432  -d  postgres
    docker build -t playlistservice:dev .
    docker run --name playlist1   --network playlistnetwork  -e PORT=8080  -e SPRING_PROFILES_ACTIVE=docker   -p 1000:8080  -d  playlistservice:dev


## Instruction for Heroku 

    $ heroku login
    $ heroku container:login
    $ heroku container:push web
    $ heroku container:release web

Finally verified the Herokuwebapp

1) Did post method using postman tool
   
   Request body:
   
        {
        "name": "playlistone"
       }

2)
https://playlistservice123.herokuapp.com/playlist

https://playlistservice123.herokuapp.com/playlist/playlistone
Above returned empty list, since there were no songs in the
playlistone 

