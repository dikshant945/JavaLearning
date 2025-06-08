package org.JavaLearning.SolidPrinciples.SingleResponsiblityPrinciple.Error;

public class Employee {
    private String name;
    private int salary;

    public void saveToDatabase() {
        System.out.println("Saving to database...");
        throw new RuntimeException("DB connection failed");
    }

    public String generateReport() {
        return "Report for " + name + ": $" + salary;
    }
}

