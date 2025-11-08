# ⚡ Multithreaded Web Server

A **high-performance multithreaded web server** built entirely in **Java**, capable of handling **100,000+ requests per second (RPS)**.  
This project demonstrates advanced concepts like **concurrency**, **thread pooling**, and **non-blocking I/O**, delivering a lightweight yet powerful HTTP server from scratch.

---

## 🚀 Features

- 🧵 **Multithreaded Architecture** – Handles thousands of concurrent requests using a thread pool  
- ⚙️ **ExecutorService-Based Thread Pool** – Efficient resource management for maximum throughput  
- ⚡ **High-Performance I/O** – Optimized socket handling and minimal latency  
- 🧩 **Custom HTTP Request Parser** – Processes basic GET requests and static file responses  
- 🪵 **Request Logging** – Tracks all incoming connections and server responses  
- 💡 **Scalable Design** – Easily configurable for different workloads and system resources  

---

## 🧠 Project Overview

This server listens for HTTP requests, delegates them to a pool of worker threads, and sends back responses efficiently.  
It’s designed to emulate how real-world web servers like **Nginx** or **Apache** manage concurrency, but with a simplified implementation for educational and performance testing purposes.

---

## 🧱 Tech Stack

| Component | Technology |
|------------|-------------|
| **Language** | Java |
| **Concurrency Model** | Multithreading (ExecutorService) |
| **Core APIs** | java.net, java.io, java.util.concurrent |
| **IDE** | IntelliJ IDEA / VS Code |
| **Test Tool** | ApacheBench (ab) |
---

## ⚡ How It Works

1. The **server** starts and listens on a configurable port (default `8080`).  
2. When a client sends a request, it’s handed off to a **worker thread** from the pool.  
3. The **ClientHandler** parses the HTTP request, prepares a response, and sends it back.  
4. After completing the task, the thread is **returned to the pool** for reuse.  
5. Logs record the request details and response time.

This architecture enables the server to efficiently handle **massive concurrency** with **minimal overhead**.

---

## 🧪 Performance Benchmark

| Test Type | Requests per Second | Avg Latency | Threads | Machine |
|------------|--------------------:|-------------:|---------:|----------|
| Localhost Benchmark | **100,000+ RPS** | ~1.5ms | 100 | 8-core CPU, 16GB RAM |

> Load tested using ApacheBench (ab) and JMeter on Java 21.

---

## 🛠️ Setup & Run

### 1. Clone the Repository
```bash
git clone https://github.com/ShashvatYadav/multithreaded-web-server.git
cd multithreaded-web-server
