# ⚙️ Concurrent and Distributed Programming 
## 📌 Description

This repository contains all the practical assignments developed for the subject *Concurrent and Distributed Programming*. Each practice addresses a different concurrency problem using Java, implementing concepts such as synchronization, semaphores, locks, thread pools, RMI, and CSP via JCSP.

## 📁 Contents

### 🔹 `Practica4_PCD` – Stack Synchronization with wait/notify  
Implements a synchronized stack (PilaLenta) with bounded capacity. Threads must wait when the stack is full/empty and retry a limited number of times before aborting. Demonstrates `wait()` / `notify()` use for condition-based thread coordination.

### 🔹 `Practica5_PCD` – Physiotherapy Center with Condition Synchronization  
Models clients requesting massages or rehabilitation. Synchronization ensures therapists are available, dressing room access is controlled, and rehabilitation clients have priority. Implemented using classic Java synchronization primitives (`wait`, `notify`, `notifyAll`).

### 🔹 `Practica6_PCD` – Congress Hall Coffee Simulation using Semaphores  
Simulates a congress scenario where participants prepare coffee (cut or macchiato) using shared coffee and milk machines with limited doses. Access is controlled using semaphores, with a thread-safe trash can and periodic refill logic by a bartender thread.

### 🔹 `Practica7_PCD` – Animal Shelter with ReentrantLock and Conditions  
An animal shelter with a feeder for dogs and cats. A constraint prevents situations like 3 cats and 1 dog together. Access to the feeder is synchronized using `ReentrantLock` and multiple `Condition` variables (no `signalAll` allowed).

### 🔹 `Practica8_PCD` – Payment Simulation with ExecutorService Thread Pools  
Models a supermarket payment system with cash/card clients and 4 checkout machines. Thread pools (one for each type of client) simulate access control and concurrency. Measures waiting times and ensures no more than 10 concurrent threads of each type.

### 🔹 `Practica9_PCD` – Distributed Payment System using Java RMI  
Extends the previous simulation to a distributed model using Java RMI. The payment logic is exposed as a remote object. Two external applications ("Efectivo" and "Tarjeta") invoke payment methods remotely and visualize activity on the server canvas.

### 🔹 `Practica10_PCD` – JCSP: Fair Animal Feeder with Message Passing  
Solves the same feeder problem as in practice 7, but using **JCSP** and CSP-style message passing. Includes sender threads for dogs and cats, a `Controlador` that regulates access via mailboxes, and a `CanvasComedero` for graphical output.

---

## 🛠 Technologies Used

- Java 17+
- Java concurrency primitives: `synchronized`, `wait`, `notify`, `ReentrantLock`, `Condition`
- `ExecutorService` and Thread Pools
- `java.util.concurrent.Semaphore`
- Java RMI (Remote Method Invocation)
- JCSP (Communicating Sequential Processes for Java)
- Graphical interface via `Frame` and `Canvas`

---

Each practice demonstrates a real-world synchronization scenario, gradually introducing more advanced concurrency and distributed system concepts.
