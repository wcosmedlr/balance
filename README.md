# Getting Started with Balanced app

This project proposes a way to balance the economy between a group of members through a list of suggested transactions.

## Modules

To launch the system you must have installed docker and execute the next commands:

### Database (MYSQL)

- docker run -d --name balance-db-container -p 3306:3306 -p 33060:33060 -e TZ=Europe/Madrid -e MYSQL_USER=balance-us -e MYSQL_ROOT_PASSWORD=balance-pw-root -e MYSQL_PASSWORD=balance-pw -e MYSQL_DATABASE=balance-db mysql

### Backend (Micronaut)

- cd backend
- mvn package -Dpackaging=docker
- docker run -d --name balance-backend-container -p 8080:8080 balance-backend

### Frontend (ReactJS)

- cd fronted
- docker build -t balance-frontend .
- docker run -d --name balance-frontend-container -p 3000:3000 balance-frontend

## Example
![alt text](https://imgur.com/PsPJzq9)
