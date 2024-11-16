import java.io.*;
import java.nio.file.*;
import java.util.*;

class Transaction {
    String transactionDate;
    String productCode;
    double unitPrice;
    int unitsSold;

    public Transaction(String transactionDate, String productCode, double unitPrice, int unitsSold) {
        this.transactionDate = transactionDate;
        this.productCode = productCode;
        this.unitPrice = unitPrice;
        this.unitsSold = unitsSold;
    }
}

public class Main {

    public static List<Transaction> loadData(String filePath) {
        List<Transaction> records = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (int i = 1; i < lines.size(); i++) {
                String[] columns = lines.get(i).split(",");
                String date = columns[0];
                String code = columns[1];
                double price = Double.parseDouble(columns[2]);
                int quantity = Integer.parseInt(columns[3]);
                records.add(new Transaction(date, code, price, quantity));
            }
        } catch (IOException e) {
            System.out.println("Error while reading the file: " + e.getMessage());
        }
        return records;


}
    public static double calculateTotalRevenue(List<Transaction> records) {
        double totalRevenue = 0;
        for (Transaction record : records) {
            totalRevenue += record.unitsSold * record.unitPrice;
        }
        return totalRevenue;
    }

    public static Map<String, Double> calculateMonthlyRevenue(List<Transaction> records) {
        Map<String, Double> revenueByMonth = new HashMap<>();
        for (Transaction record : records) {
            String month = record.transactionDate.substring(0, 7);
            double saleAmount = record.unitsSold * record.unitPrice;
            revenueByMonth.put(month, revenueByMonth.getOrDefault(month, 0.0) + saleAmount);
        }
        return revenueByMonth;
    }
    public static Map<String, String> findTopSellingItems(List<Transaction> records) {
        Map<String, Map<String, Integer>> itemQuantitiesByMonth = new HashMap<>();
        for (Transaction record : records) {
            String month = record.transactionDate.substring(0, 7);
            itemQuantitiesByMonth.computeIfAbsent(month, k -> new HashMap<>())
                    .put(record.productCode, itemQuantitiesByMonth.get(month).getOrDefault(record.productCode, 0) + record.unitsSold);
        }

        Map<String, String> topItemsByMonth = new HashMap<>();
        for (Map.Entry<String, Map<String, Integer>> monthData : itemQuantitiesByMonth.entrySet()) {
            String month = monthData.getKey();
            String topItem = "";
            int maxQuantity = 0;
            for (Map.Entry<String, Integer> itemData : monthData.getValue().entrySet()) {
                if (itemData.getValue() > maxQuantity) {
                    maxQuantity = itemData.getValue();
                    topItem = itemData.getKey();
                }
            }
            topItemsByMonth.put(month, topItem);
        }
        
        return topItemsByMonth;
    }
    public static Map<String, String> findTopRevenueItems(List<Transaction> records) {
        Map<String, Map<String, Double>> revenueByProductByMonth = new HashMap<>();
        for (Transaction record : records) {
            String month = record.transactionDate.substring(0, 7);
            double revenue = record.unitsSold * record.unitPrice;
            revenueByProductByMonth.computeIfAbsent(month, k -> new HashMap<>())
                    .put(record.productCode, revenueByProductByMonth.get(month).getOrDefault(record.productCode, 0.0) + revenue);
        }

        Map<String, String> topRevenueItemsByMonth = new HashMap<>();
        for (Map.Entry<String, Map<String, Double>> monthData : revenueByProductByMonth.entrySet()) {
            String month = monthData.getKey();
            String topItem = "";
            double maxRevenue = 0;
            for (Map.Entry<String, Double> itemData : monthData.getValue().entrySet()) {
                if (itemData.getValue() > maxRevenue) {
                    maxRevenue = itemData.getValue();
                    topItem = itemData.getKey();
                }
            }
            topRevenueItemsByMonth.put(month, topItem);
        }
        return topRevenueItemsByMonth;
    }
    public static void main(String[] args) {
        List<Transaction> records = loadData("data.txt");

        double totalRevenue = calculateTotalRevenue(records);
        System.out.println("Total Store Revenue: " + totalRevenue);

        Map<String, Double> monthlyRevenue = calculateMonthlyRevenue(records);
        System.out.println("\nMonthly Revenue Breakdown:");
        for (Map.Entry<String, Double> entry : monthlyRevenue.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        Map<String, String> topItems = findTopSellingItems(records);
        System.out.println("\nTop-Selling Items Each Month:");
        for (Map.Entry<String, String> entry : topItems.entrySet()) {
            System.out.println(entry.getKey() + ": Product: " + entry.getValue());
        }

    Map<String, String> topRevenueItems = findTopRevenueItems(records);
        System.out.println("\nHighest Revenue-Generating Items Each Month:");
        for (Map.Entry<String, String> entry : topRevenueItems.entrySet()) {
        System.out.println(entry.getKey() + ": Product: " + entry.getValue());
    }
}}