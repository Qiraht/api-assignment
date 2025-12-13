# API Assignment
This repository is using [git-flow](https://github.com/gittower/git-flow-next/) with the following branches:
- main: Production releases
- develop: Development

## How To Run
1. Modify database config on ```application.properties```
2. Run ```mvn spring-boot:run```

## API Endpoint
- **POST /api/users/register**
    - Request Body: 
        - username: String
        - password: String
        - email: String
        - address: String
- **POST /api/users/login**
    - Request Body: 
        - email: String
        - password: String
- **GET /api/users/{id}**
    - Request Path: 
        - id: Integer