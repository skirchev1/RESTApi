# RESTApi

Clone this repository
Make sure you are using JDK 19 and Language level 17
You can build the project.

The service is just a simple player REST service. It uses an in-memory database (MariaDB) to store the data. You can also do with a relational database like MySQL.

Here are some endpoints you can call:

Create a player resource:

POST /api/player
Accept: application/json
Content-Type: application/json

{
    "name": "David Beckham",
    "country": "Bulgaria",
    "birthDate": "1975-05-03",
    "position": "MIDFIELDER"
}

responce : Status 200 OK

{
    "id": "573cd7f0-dd38-45eb-8b6a-962e4d1ddc34",
    "name": "David Beckham",
    "country": "Bulgaria",
    "birthDate": "1975-05-03T21:00:00.000+00:00",
    "position": "MIDFIELDER",
    "createdAt": "2022-12-01T19:16:07.051+00:00"
}

Update a player resource:

PUT /api/player

Accept: application/json
Content-Type: application/json

{
    "id": "573cd7f0-dd38-45eb-8b6a-962e4d1ddc34",
    "name": "David Beckham",
    "country": "Romania",
    "birthDate": "1975-05-03",
    "position": "GOALKEEPER"
}

responce: Status 200 OK

{
    "id": "573cd7f0-dd38-45eb-8b6a-962e4d1ddc34",
    "name": "David Beckham",
    "country": "Romania",
    "birthDate": "1975-05-03",
    "position": "GOALKEEPER",
    "createdAt": "2022-12-01T19:16:07.051+00:00"
}


Get all players with paginating
page size = 3

GET /api/player
responce: Status 200 OK
with rensponce body json

Get all players with paginating and filter country
page size = 3

GET /api/player
PARAMS key - country, value - value of the country
responce: Status 200 OK
with rensponce body json

Get all players with paginating and filter position
page size = 3

GET /api/player
PARAMS key - position, value - value of the position
responce: Status 200 OK
with rensponce body json



