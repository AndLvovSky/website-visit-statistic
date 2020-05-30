# Website visit statistic system
## Backend
`$ cd backend`
### Build
`$ ./gradlew clean build -x test`
### Run
`$ ./gradlew bootRun`
## Frontend
`$ cd frontend`
### Build
`$ npm ci`
### Run
`$ npm run dev`

The app will be running on port `3535`
## Launch database in Docker
`$ docker run -p 5435:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=wvs -d postgres:9.6.1`
## System Requirements
- JDK 1.8
- NPM 6.14.5
- Docker 19.03.8
