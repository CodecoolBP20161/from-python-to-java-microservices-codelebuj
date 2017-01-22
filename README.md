
#CODE LE BUJ - TOP5 MICROSERVICE

####Welcome to our new microservice which keeps track of Your shop's most wanted products!

###How to use it?

1.) ****Configure**** Your IntelliJ to a PostgresSQL database(View -> Tool windows -> Database)

2.) Configure the login.json

3.) ****Run**** the *init_db.sql* file (PATH: /from-python-to-java-microservices-codelebuj/src/main/resources/sql/init_db.sql)

3.5) Run data.sql for test products

4.) Get Your ****API KEY**** by running the APIKey class in the top5 folder

5.) ****Implement**** a HTML file/modulo where you can display your products

6.) Implement ****2 endpoints**** which communicates with the API and where Your APIKEY appears in the URL as a parameter

7.) **Enjoy!**


###How it Works?

Our API has 3 endpoints and runs on PORT 60001.

On *"/api/:apikey/addproduct"* (method: POST) You can send the recently bought product's details in a JSON file.
pay attention to include only the following details: Product ID, Quantity, API Key.
The details saved into our DB where we keeping track of each product's demand in the last 30 days.
Products appear in the paid_products table with the shops identifier.

You should post a json to this route when a visitor checked out from the shop (eg. clicked on Payment button).


On *"/api/:apikey/gettop5"* (GET) You can get back your shop's most popular products.
This route returns a JSON file with the top5 products IDs & Quantities. 


The "/status" returns OK if the server running.