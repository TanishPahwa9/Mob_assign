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

    public static void main(String[] args) {
        List<Transaction> records = loadData("data.txt");

        double totalRevenue = calculateTotalRevenue(records);
        System.out.println("Total Store Revenue: " + totalRevenue);

        Map<String, Double> monthlyRevenue = calculateMonthlyRevenue(records);
        System.out.println("\nMonthly Revenue Breakdown:");
        for (Map.Entry<String, Double> entry : monthlyRevenue.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}