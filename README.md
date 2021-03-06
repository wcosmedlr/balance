[![Codacy Badge](https://app.codacy.com/project/badge/Grade/dd420e037340436bb5c3b051bacf73da)](https://www.codacy.com/gh/wcosmedlr/balance/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=wcosmedlr/balance&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/dd420e037340436bb5c3b051bacf73da)](https://www.codacy.com/gh/wcosmedlr/balance/dashboard?utm_source=github.com&utm_medium=referral&utm_content=wcosmedlr/balance&utm_campaign=Badge_Coverage)

# Getting Started with Balanced app

This project proposes a way to balance the economy between a group of members through a list of suggested transactions.

## Modules

To launch the system you must have installed docker and execute the next commands:

### Database (MYSQL)

- docker run -d --name balance-db-container -p 3306:3306 -p 33060:33060 -e TZ=Europe/Madrid -e MYSQL_USER=balance-us -e MYSQL_ROOT_PASSWORD=balance-pw-root -e MYSQL_PASSWORD=balance-pw -e MYSQL_DATABASE=balance-db mysql

### Backend (Micronaut)

- cd backend
- mvn package -Dpackaging=docker
- docker run -d --name balance-backend-container -p 8080:8080 -e DB-HOST=[YOUR-LOCAL-IP] balance-backend

### Frontend (ReactJS)

- cd fronted
- docker build -t balance-frontend .
- docker run -d --name balance-frontend-container -p 3000:3000 balance-frontend

## Quality oriented (ATDD)

### Backend
![Imgur Image](https://imgur.com/AqxXG5r.jpg)
![Imgur Image](https://imgur.com/pbZRT76.jpg)

### Frontend
![Imgur Image](https://imgur.com/Ovh6nVg.jpg)

## Examples

### Members
![Imgur Image](https://imgur.com/kcsaU1B.jpg)

### Expenses
![Imgur Image](https://imgur.com/rhJSJ2L.jpg)

### Home
![Imgur Image](https://imgur.com/PsPJzq9.jpg)
