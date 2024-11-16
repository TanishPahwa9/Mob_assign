# Mobiux_developer_assignment
A Java program for analyzing ice cream parlor sales data, including monthly revenue summaries, top-selling products, highest revenue-generating items, and order metrics.

## Table of Contents
1. [Setup-Instructions](#setup-instructions)
2. [Features](#features)
3. [Code Overview](#code-overview)
4. [Dependencies](#dependencies)
5. [Sample Output](#sample-output)

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/TanishPahwa9/Mob_assign.git
2. Navigate to the project directory:
   ````bash
   cd Mob_assign
3. Compile the java code 
   ````bash
   javac Main.java
4. Run the program
   ````bash
   java SalesReport
5 ### Input Data File:
Ensure data.txt (containing the sales data) is present in the project directory. Format:

## Features
1. **Overall Revenue Calculation**: Computes the total revenue across all transactions.
2. **Monthly Revenue Breakdown:**: Displays total revenue generated for each month.
3. **Top-Selling Products:**:Identifies the most frequently sold product in each month..
4. **Highest Revenue-Generating Products:**: Determines the product with the highest revenue in each month..
5. **Order Statistics**: Provides detailed order statistics for top-selling items, including:
Minimum order size.
Maximum order size.
Average order size.

## Code Overview
**loadData(String filePath):**
Reads data from a CSV file and converts it into Transaction objects.

**calculateTotalRevenue(List<Transaction>):**
Calculates the total store revenue.

**calculateMonthlyRevenue(List<Transaction>):**
Summarizes revenue for each month.

**findTopSellingItems(List<Transaction>):**
Identifies the top-selling product by quantity for each month.

**findTopRevenueItems(List<Transaction>):**
Finds the product that generated the most revenue in each month.

**displayOrderStatistics(List<Transaction>, Map<String, String>):**
Analyzes order data for top-selling items.

## Dependencies
This program uses the following C++ libraries:

- import java.io.*;
- import java.nio.file.*;
- import java.util.*;
-Java Development Kit (JDK): Ensure you have JDK 8 or later installed.
-Input File: A CSV file (e.g., data.txt) with the following format:
plaintext
Copy code


## Sample Output

   ````bash
Total Store Revenue: 5000.00

Monthly Revenue Breakdown:
2024-10: 1500.50
2024-11: 3500.20

Most Popular Items Each Month:
2024-10: Product: ICR123
2024-11: Product: ICR456

Highest Revenue-Generating Items Each Month:
2024-10: Product: ICR123
2024-11: Product: ICR456

Order Statistics for Top-Selling Items:
2024-10 ICR123, Min Order: 5, Max Order: 20, Avg Order: 12.33
