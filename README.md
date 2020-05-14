# Flash Sale API

An API to manage a flash sale on a e-commerce platform

## Getting Started

### Prerequesites

* Java 8
* Maven
* MySQL

### Usage

1. Import as existing Maven project and set configuration in "application.properties" file e.g Database name or MySQL configuration. Create a local database *flashsale* (or choose any name).

2. Run as spring boot app. The tables will be created automatically by spring.

Data Model

![Flash Sale data model](/ER-diagram.png?raw=true)

Tables:

**user** - Stores info about all users.

**product** - Stores the product information

**inventory** - Stores the product count

**flash_sale** - Stores the flash sale info

**sale_registration** - Store the user registered for the particular flash sale.

**sale_order** - Stores the order related information


3. Open swagger UI (http://localhost:8080/swagger-ui.html#/) to see list of available APIs.

4. List of available APIs-

Order Management:

```
POST    /order       - Places a new order
PUT    /order/{id}   - Updates the order status
```

Product management:

```
POST    /product      - Create new product
PUT    /product/{id}  - Updates the product info
GET    /product/{id}  - Gets product by id
DELETE /product/{id}  - Deletes product by id
```
Inventory management:

```
POST    /inventory      - Creates new product inventory
PUT    /inventory/{id}  - Updates count of inventory
```
Flash Sale management:

```
POST    /sale          - Creates a new flash sale
PUT    /sale/{id}      - Sale can be started and closed, or registration can be closed using this API. The API calls can be scheduled via cron jobs.
GET    /sale/{id}      - Gets sale by id
```
User management:
```
POST    /user          - Creates a new user
```

Regsiteration for the sale:

```
POST    /register     - Regsiters user for the flash sale
```

### Logic and constraints

1. A user can only place an order if it is registered for the flash sale.
2. Order can only be placed when the sale status is ONGOING.
3. A user can place only one order, unless previous order is CANCELLED.


