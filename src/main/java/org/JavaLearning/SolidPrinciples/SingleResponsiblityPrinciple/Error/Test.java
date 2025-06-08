package org.JavaLearning.SolidPrinciples.SingleResponsiblityPrinciple.Error;

public class Test {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.saveToDatabase();
        employee.generateReport();

        // If i am unable to save to DB than generate report will also be not wroking  #One Bug breaks everything
        // Evertime for generating reports i have to crate a employee of no use..
        // Multiple devs editing the same class
        // 	One logic change affects the whole class
    }
}
