# shuffle-logger-MS-monorepo
The system contains 2 microservices. One for processing the request and the second one to log it.

Micro services:
1- service-shuffle port:8080
2- service-log port: 8081

_______________________________________________________________________
**Endpoints [service-shuffle]**
1- POST /api/shuffle 
**Description:**
Gets a number and returns a shuffled array from 1 to this number.
without duplications of numbers.
Example: for 5 – [4, 2, 1, 5, 3]
Example: for 10 – [8, 5, 1, 9, 2, 6, 7, 3, 4, 10]

**Request:**
JSON body contains "number" - number shoud be between 1 - 1000 (including)
{
 "number":10
}

**Response:** OK 200: Array of numbers [2,1,4,7,3,6,8,10,9,5]
**Other possible responses in case of error:**
- BAD REQUEST 400: validation error
- INTERNAL SERVER ERROR 500: other exceptions

_______________________________________________________________________
**Endpoints [service-log]**
1- POST /api/log  
**Description:** Gets a request object from service-shuffle and log it
**Request:** JSON body contains Log object that created in service-shuffle

**Response:** 200 OK
**Other possible responses in case of error:**
- BAD REQUEST 400: validation error
- INTERNAL SERVER ERROR 500: other exceptions
