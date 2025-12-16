# API Assignment
This repository is using [git-flow](https://github.com/gittower/git-flow-next/) with the following branches:
- main: Production releases
- develop: Development

## How To Run
1. Modify database config on ```application.properties```
2. Run ```mvn spring-boot:run```

## API Endpoint
- **GET /api/users/register**
    - Request Param: 
        - username: String
        - password: String
        - email: String
        - address (optional): String
- **GET /api/users/login**
    - Request Param: 
        - username: String
        - password: String
- **GET /api/users/{id}**
    - Request Path: 
        - id: Integer

Notes: Kalau mau versi yang sebelumnya (POST register dengan req body, dll) bisa cek branch develop