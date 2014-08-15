Transactions (mis)management: how to kill your app
===================================


This project has been created as part of a tutorial: [Transactions (mis)management: how to kill your app]

It is a simple Java web application which demonstrates how creating a new transaction with propagation REQUIRES_NEW under existing transaction could deadlock your database layer and in essence make your application unresponsive to any calls requiring database access.

**About the app:**
- JSON endpoints build on Spring MVC; H2 as in-memory DB
- All endpoints return JSON response
- Application populated with sample data on startup - 'quotes' with IDs in a range {1..5} and relevant 'quoteStats'
- Configured to run on port 9090

**Running the application:**
- either import the app into your favourite IDE (i.e. Eclipse). Once you do that, start the application by running Jetty.java from src/test/java under package .uk.co.resilientdatasystems.thtkya.web.jetty
- or run mvn jetty:run from command line which will start the app on port 9090


**Calling endpoints:**
- All endpoints respond to GET requests
- Call via curl, web browser, Postman (Chrome plugin) or via provided scripts (see below)


**Available endpoints:**
- /good/quite/{id} - returns quote of given id; id = {1..5}
- /good/quite/stats/{id} - returns stats of a quote;id = {1..5}
- /good/quite/buy/{id} - 'buys' quoted product; id = {1..5}
- /bad/quite/{id} - same as above, transactions declared differently
- /bad/quite/stats/{id} - same as above, transactions declared differently
- /bad/quite/buy/{id} - same as above, transactions declared differently

**Availavle scripts:**
- In *scripts* directory there are several scripts you may find useful for testing the application
             - getQuote.sh - the script takes one argument: quote id {1..5} and returns quote in JSON format
             - getStats.sh - the script takes one argument: quote id {1..5} and returns quote stats in JSON format
             - concurrentRequests.py - script takes three arguments: number of concurrent threads requesting resource, number of requests each thread will request, endpoint which should be called by script

**Sample usage:**
```sh
git clone git@github.com:resilient-data-systems/transactions-how-to-kill-your-app.git
cd transactions-how-to-kill-your-app
mvn clean install jetty:run
#in another terminal window...
cd transactions-how-to-kill-your-app/scripts
./getQuote.sh 1
./getStats.sh 1
python concurrentRequests.py 20 50 http://localhost:9090/good/quote/1
./getStats.sh 1
#and example which will get deadlock due to connection pool saturation caused by nested REQUIRES_NEW transaction:
python concurrentRequests.py 20 50 http://localhost:9090/bad/quote/1
#If you want to stop that stalled execution, just kill the Jetty (Ctrl+C)
```



[Transactions (mis)management: how to kill your app]:http://www.resilientdatasystems.co.uk/java/transactions-mis-management-how-to-kill-app