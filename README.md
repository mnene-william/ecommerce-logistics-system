# The Zodiacs – Integrated Logistics and E-Commerce Order Fulfilment System

## Project Overview

The Zodiacs Logistics System is a Java-based e-commerce order fulfilment system designed to process up to 1,000 customer orders using multithreading and concurrent programming.

It simulates the complete order fulfilment process, from payment processing to warehouse preparation and final delivery.

## Key Features

- **Multithreading:** Uses multiple threads to process orders concurrently.
- **Payment Processing:** Uses a fixed thread pool of 3 payment workers.
- **Warehouse Processing:** Uses 5 warehouse workers to prepare orders.
- **Delivery Management:** Uses 4 delivery vehicles to deliver orders.
- **BlockingQueue:** Transfers orders between payment, warehouse, and delivery stages.
- **Semaphore:** Limits the number of vehicles operating concurrently to 4.
- **Inventory Management:** Uses synchronized methods to prevent overselling.
- **Failure Handling:** Releases reserved stock when payment fails or an order is cancelled before dispatch.
- **Notifications:** Notifies customers when their orders are successfully delivered.
- **Final Report:** Displays order statistics, remaining inventory, and vehicle delivery counts.

## Technologies Used

- Java
- Java Concurrency API
- ExecutorService
- BlockingQueue
- Semaphore
- Git and GitHub

## System Workflow

1. Customer orders are received.
2. Inventory is checked and stock is reserved.
3. Successful payments are sent to the warehouse queue.
4. Warehouse workers retrieve and prepare orders.
5. Prepared orders are transferred to the delivery queue.
6. Delivery workers verify reservations before dispatch.
7. Delivery vehicles deliver the orders.
8. Customers receive delivery notifications.
9. The system generates a final report.

## How to Run

Compile the Java source files:

```bash
javac src/*.java
```

Run the main application:

```bash
java -cp src Main
```

To run the cancellation test:

```bash
java -cp src CancellationTest
```

## Inventory Configuration

| Product | Initial Stock |
|---|---:|
| Product A | 100 |
| Product B | 50 |

## Resource Configuration

| Resource | Number |
|---|---:|
| Payment Workers | 3 |
| Warehouse Workers | 5 |
| Delivery Vehicles | 4 |
| Orders Generated | 1,000 |

## Concurrency and Thread Safety

The system uses `ExecutorService` to manage worker threads and `BlockingQueue` to coordinate order processing between stages.

Synchronized inventory methods ensure that multiple threads cannot simultaneously modify stock in a way that causes overselling.

A semaphore limits concurrent vehicle access to four.
