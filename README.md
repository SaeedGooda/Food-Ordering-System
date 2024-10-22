# Food Ordering System

A complete food ordering system that allows users to browse restaurants, order food online, manage ingredients, and manage orders. This application includes features for users, restaurants, and administrative management of food items and categories.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Contact Information](#contact-information)

## Features

- **User Registration and Authentication**: Users can create accounts, log in, and manage passwords securely.
- **Restaurant Management**: Owners can add, update, and delete their restaurants, including essential details and images.
- **Menu Management**: Restaurants can add, update, and delete food items with descriptions, prices, and ingredients.
- **Cart Management**: Users can view, add, update, and remove items from their shopping cart.
- **Order Processing**: Users can create orders from their carts and receive updates on order status from restaurants.
- **Address Management**: Users can add, update, and delete multiple delivery addresses for convenience.
- **Favourites Management**: Users can mark and unmark restaurants as favourites for easy access.
- **Search and Filter Functionality**: Users can search for and filter restaurants and food items by various criteria.

## Technologies Used

- Java
- Spring Boot
- Hibernate
- MySQL
- JPA (Java Persistence API)
- RESTful APIs
- JWT (JSON Web Tokens) for authentication
- Maven

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/SaeedGooda/Food-Ordering-System.git
   cd food-ordering-system
2. **Configure your database:**

   ``` bash
   - Create MySQL Database and name it 'food_order_db'
   - Add the credentials to application.properties file (name, password)
3. **Build the project:**

   ```bash
   mvn clean install
4. **Run the project:**

   ```bash
   mvn spring-boot:run
## Usage

1. **Accessing the Application:**
   - Open your web browser and navigate to `http://localhost:8080` to view the application interface.

2. **Interacting with the API:**
   - Use a tool like **Postman**, **Insomnia**, or **cURL** to interact with the API endpoints.
   - Make sure to include the `Authorization` header in your requests to protected endpoints. The header should look like this:
     ```
     Authorization: Bearer your_jwt_token
     ```

3. **Testing the API:**
   - Use the following example requests to test various features of the application:
   
   - **Login User:**
     - Method: `POST`
     - URL: `/api/auth/login`
     - Body:
       ```json
       {
         "email": "user@example.com",
         "password": "yourpassword"
       }
       ```
     - Response:
       ```json
       {
         "accessToken": "jwt_token",
         "tokenType": "Bearer"
       }
       ```

   - **Create a Restaurant:**
     - Method: `POST`
     - URL: `/api/restaurants`
     - Body:
       ```json
       {
         "name": "Restaurant Name",
         "description": "Description",
         "contactInformation": "Contact",
         "cuisineType": "Cuisine",
         "address": {
           "street": "Street Name",
           "city": "City",
           "state": "State",
           "zipCode": "Zip Code"
         }
       }
       ```

   - **View All Restaurants:**
     - Method: `GET`
     - URL: `/api/restaurants`

   - **Create an Order:**
     - Method: `POST`
     - URL: `/api/orders`
     - Body:
       ```json
       {
         "restaurantId": 1,
         "deliveryAddress": {
           "street": "Street Name",
           "city": "City",
           "state": "State",
           "zipCode": "Zip Code"
         }
       }
       ```

   - **View User Orders:**
     - Method: `GET`
     - URL: `/api/orders/user/{userId}`

   - Replace `{userId}` with the actual user ID you want to retrieve orders for.

### Note:
- Make sure your server is running before sending requests.
- Ensure that the database is properly configured and populated with test data as needed.

## API Documentation
1. **User Endpoints:**

   ```POST /auth/signup``` user signup

   ```POST /auth/signin user``` user login

   ```GET /api/users/profile``` user profile

2. **Restaurant Endpoints:**

   ```POST /api/admin/restaurants``` create restaurant

   ```PUT /api/admin/restaurants/{id}``` update restaurant

   ```DELETE /api/admin/restaurants/{id}``` delete restaurant

   ```PUT /api/admin/restaurants/{id}/status``` update restaurant status

   ```PUT /api/admin/restaurants/user``` get restaurant by user id

   ```GET /api/restaurants``` get all restaurants

   ```GET /api/restaurants/search``` search for restaurants

   ```GET /api/restaurants/{id}``` get restaurant by id

   ```PUT /api/restaurants/{id}/add-favourites``` add restaurant to favourites

3. **Food Endpoints:**

   ```POST /api/admin/foods``` create food

   ```DELETE /api/admin/foods``` delete food

   ```PUT /api/admin/foods/{id}``` update food availability
    
   ```GET /api/foods/search``` search for food

   ```GET /api/foods/restaurant/{restaurantId}``` get restaurant foods

4. **Cart Controller:**

   ```POST /api/cart/items``` add item to cart

   ```PUT /api/cart/items/{id}``` update cart item quantity

   ```DELETE /api/cart/items/{id}``` delete cart item
    
   ```PUT /api/cart/clear``` clear cart

5. **Order Controller:**

   ```POST /api/admin/order/restaurant/{id}``` get orders history in restaurant

   ```PUT /api/admin/order/{orderId}/{orderStatus}``` update order status

   ```POST /api/order``` create order
    
   ```GET /api/order/user``` get orders history for the user

6. **Category Controller:**

   ```POST /api/admin/category``` create category

   ```GET /api/category/restaurant``` get restaurant categories

   ```GET /api/category/{id}``` get category by id
    
7. **Ingredients Controller:**

   ```POST /api/admin/ingredients/category``` create ingredient category

   ```POST /api/admin/ingredients``` create ingredient item

   ```POST /api/admin/ingredients/restaurant/{id}``` get restaurant ingredients

   ```POST /api/admin/ingredients/restaurant/{id}/category``` get restaurant ingredient categories

## Contact Information

For any inquiries or support, please contact:

- **Email**: saeedgooda219@gmail.com
- **LinkedIn**: [Saeed Gooda](https://www.linkedin.com/in/saeed-gooda-bbaa091a3/)

Feel free to reach out with any questions, suggestions, or contributions!
