# ⚡ Electrical Store Management System

A simple **Java Swing + MySQL** desktop application to manage electrical store products with basic CRUD operations.

---

## 🚀 Features

* ➕ Add Product
* ✏️ Update Product
* 🗑️ Delete Product
* 📋 View Products in Table
* 🔄 Refresh Data
* 🔗 MySQL Database Integration

---

## 🛠️ Tech Stack

* **Java (Swing)** – UI
* **JDBC** – Database Connectivity
* **MySQL** – Database

---

## 📂 Project Structure

```
ElectricalStoreMS/   
│
├── ElectricalStoreUI.java
├── mysql-connector-j-9.6.0.jar
├── README.md
└── .gitignore
```

---

## ⚙️ Setup Instructions

### 1️⃣ Create Database

```sql
CREATE DATABASE electrical_store;
```

---

### 2️⃣ Create Table

```sql
USE electrical_store;

CREATE TABLE products (
    product_id VARCHAR(10),
    product_name VARCHAR(50),
    price INT
);
```

---

### 3️⃣ Insert Sample Data (Optional)

```sql
INSERT INTO products VALUES ('p101', 'Switch', 1200);
INSERT INTO products VALUES ('p102', 'Wire', 800);
INSERT INTO products VALUES ('p103', 'Bulb', 300);
```

---

### 4️⃣ Add MySQL Connector

Download MySQL Connector/J and place the `.jar` file in your project folder.

---

### 5️⃣ Compile

```bash
javac -cp ".;mysql-connector-j-9.6.0.jar" ElectricalStoreUI.java
```

---

### 6️⃣ Run

```bash
java -cp ".;mysql-connector-j-9.6.0.jar;." ElectricalStoreUI
```

---

## 🧪 How to Use

1. Enter **Product ID, Name, Price**
2. Click:

   * **Add** → Insert new product
   * **Update** → Modify existing product
   * **Delete** → Remove selected product
   * **Refresh** → Reload table
3. Click any row → fields auto-fill

---

## 📸 Screenshot

```
/screenshots/ui.png
```

---

## 🎯 Learning Outcomes

* Java Swing UI design
* JDBC connectivity
* MySQL CRUD operations
* Event-driven programming

---

## 👨‍💻 Author

**Giri Prasath**

---

## ⭐ Future Improvements

* 🔍 Search functionality
* 🎨 Modern UI design
* 📦 Stock management
* 🧾 Billing system

---

## 📌 Note

This is a **beginner-level project** built for learning purposes and academic submission.
