package org.JavaLearning.SolidPrinciples.SingleResponsiblityPrinciple.Solution;

public class Test {

    // Learning :-
//            | Problem Before               | Solved By                           |
//            | ---------------------------- | ----------------------------------- |
//            | One bug affects all          | Split into 3 small classes          |
//            | Hard to test report          | Only test `EmployeeReportGenerator` |
//            | Risk of side effects         | Classes don’t interfere             |
//            | Merge conflicts between devs | Devs can work on different classes  |
//            | Hard to reuse                | Just use the class you need         |

    public static void main(String[] args) {
        Employee emp = new Employee("Dikshant", 75000);

        // Save employee to DB
        EmployeeRepo repo = new EmployeeRepo();
        repo.saveToDatabase(emp);

        // Generate report
        EmpReportGenerator reportGen = new EmpReportGenerator();
        String report = reportGen.generateReport();
        System.out.println(report);
    }
}
