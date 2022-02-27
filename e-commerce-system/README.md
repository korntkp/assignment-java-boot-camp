## Table of Contents

1. [Commands](#commands)
2. [User Story](#user-story)
3. [API Endpoint](#api-endpoint)
4. [Database](#database)
5. [Tasks](#tasks)
6. [Improvement](#improvement)
7. [Git Branch Convention](#git-branch-convention)

---

## Commands

```bash
# macOS
./mvnw clean test
./mvnw compile
./mvnw spring-boot:run
```

---

## User Story

- Customer หำการค้นหา Product ด้วยชื่อ ได้ List ของ Product ออกมา
  - Search product
- Customer หำการดูรายละเอียดของ Product นั้นๆ
  - Get product detail
- Customer หำการดู, เพิ่ม และแก้ไข Product ใน Cart ได้
  - New cart for customer
  - Update product into cart
  - Get cart detail
- Customer สามารถ Checkout cart ได้
  - Checkout
- Customer สามารถดู, เพิ่ม และแก้ไข shipping address (ที่อยู่จัดส่ง) ได้
  - Add shipping address
  - View shipping address
  - Update shipping address
  - Delete shipping address
- Customer สามารถเลือก shipping address สำหรับ order นั้นๆได้
  - Select shipping address
- Customer สามารถดูและเลือก shipping method (วิธีการจัดส่ง) ได้
  - View shipping method
  - Select shipping method
- Customer สามารถดูและเลือก payment method (วิธีการชำระเงิน) ได้
  - View payment method
  - Select payment method
- Customer สามารถ Confirm order เพื่อทำการชำระเงินได้
  - Confirm order
- ระบบสามารถขอ token จาก payment gateway ได้
  - Get payment gateway token (เรียก payment gateway)
- ระบบสามารถส่งข้อมูลเพิ่อทำการชำระเงิน ไปที่ payment gateway ได้
  - Create payment (เรียก payment gateway)
- Customer สามารถดูสรุป order และ order detail หลังทำการชำระเงินแล้วได้
  - View summary order
  - View order detail

---

## API Endpoint

### 1. Search product

- GET <http://localhost:8080/products/:name>
- Response
```json
[
   {
      "id": 1,
      "name": "Samsung S9",
      "fullName": "Samsung Galaxy S9",
      "description": "This is Android mobile phone.",
      "price": 20000
   },
   {
      "id": 3,
      "name": "Samsung Note 10",
      "fullName": "Samsung Galaxy Note10",
      "description": "This is Android mobile phone with pencil.",
      "price": 4000
   }
]
```

### 2. Get product detail

- GET <http://localhost:8080/product/:id>
- Response
 ```json
{
   "id": 1,
   "name": "Samsung S9",
   "fullName": "Samsung Galaxy S9",
   "description": "This is Android mobile phone.",
   "price": 20000
}
 ```

### 3. New cart for customer

- POST <http://localhost:8080/cart>
- Request
```json
{
  "customerId": 9
}
```
- Response
```json
{
  "cartId": 32,
  "customerId": 9,
  "status": "NEW",
  "totalPrice": 0,
  "productItems": []
}
```

### 4. Update product into cart

- PATCH <http://localhost:8080/cart/:cartId>
- Request
```json
{
  "productId": 1,
  "newAmount": 1
}
```
- Response
```json
{
  "cartId": 32,
  "customerId": 9,
  "status": "NEW",
  "totalPrice": 20000,
  "productItems": [
    {
      "id": 1,
      "productId": 1,
      "amount": 1,
      "product": {
        "name": "Samsung S9",
        "fullName": "Samsung Galaxy S9",
        "description": "This is Android mobile phone.",
        "price": 20000
      },
      "totalPrice": 20000
    }
  ]
}
```

### 5. Get cart detail

- GET <http://localhost:8080/cart/:cartId>
- Response
```json
{
   "cartId": 32,
   "customerId": 9,
   "status": "NEW",
   "totalPrice": 50000,
   "productItems": [
      {
         "id": 1,
         "productId": 1,
         "amount": 1,
         "product": {
            "name": "Samsung S9",
            "fullName": "Samsung Galaxy S9",
            "description": "This is Android mobile phone.",
            "price": 20000
         },
         "totalPrice": 20000
      },
      {
         "id": 2,
         "productId": 3,
         "amount": 1,
         "product": {
            "id": 3,
            "name": "Samsung Note 10",
            "fullName": "Samsung Galaxy Note10",
            "description": "This is Android mobile phone with pencil.",
            "price": 30000
         },
         "totalPrice": 30000
      }
   ]
}
```

### 5. Checkout

- POST <http://localhost:8080/cart/:cartId/checkout>
- Request: -
- Response

```json
{
  "orderId": 50,
  "cartId": 32,
  "customerId": 9,
  "status": "NEW",
  "totalPrice": 50000,
  "shippingAddress": null,
  "shippingMethod": null,
  "paymentMethod": null
}
```

### 6. Add shipping address

- POST <http://localhost:8080/customer/:customerId/shipping-address>
- Request
```json

```
- Response
```json

```

### 7. View shipping address

- GET <http://localhost:8080/customer/:customerId/shipping-address>

### 8. Update shipping address

- PATCH <http://localhost:8080/customer/:customerId/shipping-address/:shippingAddressId>

### 9. Delete shipping address

- DELETE <http://localhost:8080/customer/:customerId/shipping-address/:shippingAddressId>

### 10. Select shipping address, shipping method, payment method

- PATCH <http://localhost:8080/order/:orderId>

### 11. View shipping method

- GET <http://localhost:8080/shipping-method>

### 12. View payment method

- GET <http://localhost:8080/payment-method>

### 13. Confirm order

- POST <http://localhost:8080/order/:orderId/confirm>

### 14. Get payment gateway token  (เรียก payment gateway)

- POST <http://localhost:8080/payment/token>

### 15. Create payment  (เรียก payment gateway)

- POST <http://localhost:8080/payment/detail>

### 16. View summary order

- GET <http://localhost:8080/order/:orderId/summarry>

### 17. View order detail

- GET <http://localhost:8080/order/:orderId>

---

## Database

1. Product
    1. id: string
    2. name: string
    3. fullName: string
    4. description: string
    5. price: number
2. Customer 
3. ShippingAddress 
4. Cart 
5. ProductItems
6. Order
7. ShippingMethod
8. PaymentMethod

---

## Tasks

[https://github.com/korntkp/assignment-java-boot-camp/projects/2](https://github.com/korntkp/assignment-java-boot-camp/projects/2)

---

## Improvement

- Pagination
- Search criteria
- API Versioning เช่น localhost:8080/api/v1/cart
- Authentication and Authorization
- Research Payment gateway API

---

## Git Branch Convention 

- master
- develop
- feature/22is001-<feature-name>
  - 22 => 2022
  - is => issue
  - 001 => issue's number (running number)

---
