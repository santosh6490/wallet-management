#Wallet Management Application

## Build Instruction
* mvn clean install

## Services
### Account Service
  * Create account POST /account
  * Update account PUT /account
  * Retrieve account GET /account/{id}
  
  
### Fund Service
  * Add Balance POST /accounts/{id}/funds/{amount}
  * Use Balance DELETE /accounts/{id}/funds/{amount}
  
### Statement Service
  * Retrieve Statement GET /accounts/{id}/statements/
  * Retrieve Statement GET /accounts/{id}/statements/{fromDate}/{toDate}
  
