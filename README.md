# EasyTravel RESTful API Services

## Introduction
This application provide RESTful API Services to manage seats on a plane. The system assumes there is only 1 flight and thus do not model that would allow managing other flights than the current one.

---
## Is the project running anywhere?
**Yes!** The project is deployed on Heroku. You can access its Swagger documentation on https://easytravel-api.herokuapp.com/

All documentation can be seen on above URL, although the "Try it!" feature is unavailable due to the fact that Heroku uses AWS, which custom Authorizers do not support returning headers, hence causing an issue of not displaying the response correctly within the Swagger page (it shows TypeError: Failed to fetch).

### How to test the API then?

I suggest using [Postman for Google Chrome](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop), although you can use any other tool of your choice.

As you can see on Swagger, there are 4 operations under Seat services.

By sending a GET HTTP request to http://easytravel-api.herokuapp.com/api/easytravel/seats, you will get the list of all available seats as a response.

Then you can check one specific seat. Let's say we want to know the status of the seat of number '1a'. We can send a GET HTTP request to http://easytravel-api.herokuapp.com/api/easytravel/seats/1a and it will give us a response with its status, which currently should be Available, for the seat wasn't booked yet.

Then, if we want to book seat '1a' to some user like John Doe, we can send a PUT request to http://easytravel-api.herokuapp.com/api/easytravel/seats/book?seatNumber=1a&userName=johndoe and it will return the seat information after its booking.

You can then check again the seat status and note that it's Unavailable this time. Also, if you send a GET HTTP request to http://easytravel-api.herokuapp.com/api/easytravel/seats you may notice the seat 1a is no longer listed, for it is no longer available for booking.

But don't panick! We can cancel the booking by sending another PUT HTTP request to http://easytravel-api.herokuapp.com/api/easytravel/seats/cancel?seatNumber=1a and it will return the seat information after its booking is cancelled.

Then you can check the seat again to see its status back to Available, and also check the seats list and notice the seat 1a is being listed back again.

---
## Running locally

If you want to use all Swagger features you can also run this project on any local machine by following the below steps:

1. Make sure you have Java 8 and Maven installed
2. Download the zip or clone this repository
3. Under the project folder run ```mvn spring-boot:run```
4. Access ```http://localhost:8080``` to enter Swagger documentation

This way you are going to run the system locally and use a remote database hosted on Heroku. If on the other hand you want to run your own local database, continue following the below steps:

1. Install PostgreSQL (9.6+)
2. Create a database
3. Run the script under ```/src/main/resources/sql-scripts``` to create Tables, Sequences and data
4. Modify the properties on ```/src/main/resources/application.properties``` to point to your own database URL, username and password
5. Run ```mvn spring-boot:run``` again
6. Access ```http://localhost:8080``` to enter Swagger documentation

---
## Issues

If you have any issues to report feel free to use the issues tool here on GitHub. :)
