# Web Services | Laboration 2 | JU21
###### Developer: Patrik Fallqvist Magnusson

This is our second assignment for the Web Services course at IT-Högskolan in Gothenburg.
The purpose of this assignment is to implement a simple Micronaut application using GraalVM to build a native image, and MongoDB database using asynchronous calls and responses. Then push the image to Docker hub or GitHub.

This demo application represents a very simple motorcycle parts database where you can GET all parts and POST a new part with either just the name, or name and description.

![Release](https://img.shields.io/github/v/release/LordRekishi/lab2-micronaut-webservices)
![repo_size](https://img.shields.io/github/repo-size/LordRekishi/lab2-micronaut-webservices)

---

## How to use 

1. `Download`, `install` and `run` [Docker Desktop](https://www.docker.com/products/docker-desktop/).
2. Create a `network`
```docker
docker network create lab2-net
``` 
3. Create a `volume` for MongoDB
```docker
docker volume create lab2-db
``` 
4. Start `MongoDB` container
```docker
docker run -d --name mongo --network lab2-net --volume lab2-db:/data/db -p 27017:27017 mongo
```
5. Start this `app` container
```docker
docker run -d --name parts-app --network lab2-net -p 8080:8080 -e MONGO_HOST=mongo ghcr.io/lordrekishi/lab2-micronaut-webservices:latest
```
6. `Test` the endpoints using the `AppTesting.http` file in the root of this project, or use [Insomnia](https://insomnia.rest/download).

---

## Endpoints

| HTTP-verb | URL    | Information   | Status Code |
|-----------|--------|---------------|-------------|
| GET       | /parts | Get all parts | 200         |
| POST      | /parts | Save a part   | 201         |

### GET
This endpoint will give you a list of all parts currently in the MongoDB database. This will return in JSON format.

### POST
There are two endpoints using POST, both take a JSON body. The first one just takes a `"name"`, the second one takes both a `"name"` and a `"description"`.

1. Only "name":
```json
{
  "name": "Handlebar"
}
```
2. Both "name" and "description":
```json
{
  "name": "Handlebar",
  "description": "Handling business"
}
```