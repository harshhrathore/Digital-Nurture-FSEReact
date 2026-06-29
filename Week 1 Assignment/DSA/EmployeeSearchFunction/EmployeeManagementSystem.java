public class EmployeeManagementSystem {
    static class Employee {
        int employeeId;
        String name;
        String position;
        double salary;

        Employee(int employeeId, String name, String position, double salary) {
            this.employeeId = employeeId;
            this.name = name;
            this.position = position;
            this.salary = salary;
        }
    }

    static Employee[] staffRecords = new Employee[100];
    static int staffCount = 0;

    static void addEmployee(Employee e) {
        staffRecords[staffCount] = e;
        staffCount++;
    }

    static int searchEmployee(int employeeId) {
        for (int i = 0; i < staffCount; i++) {
            if (staffRecords[i].employeeId == employeeId) {
                return i;
            }
        }
        return -1;
    }

    static void traverseEmployees() {
        for (int i = 0; i < staffCount; i++) {
            Employee e = staffRecords[i];
            System.out.println(e.employeeId + " " + e.name + " " + e.position + " " + e.salary);
        }
    }

    static void deleteEmployee(int employeeId) {
        int index = searchEmployee(employeeId);
        if (index == -1) {
            return;
        }
        for (int i = index; i < staffCount - 1; i++) {
            staffRecords[i] = staffRecords[i + 1];
        }
        staffCount--;
    }

    public static void main(String[] args) {
        addEmployee(new Employee(201, "Arjun Malhotra", "Software Engineer", 75000));
        addEmployee(new Employee(202, "Divya Pillai", "Product Manager", 95000));
        addEmployee(new Employee(203, "Rahul Kulkarni", "Data Analyst", 68000));
        traverseEmployees();
        int foundIndex = searchEmployee(202);
        System.out.println("Found Divya at index: " + foundIndex);
        deleteEmployee(201);
        System.out.println("After deleting Arjun:");
        traverseEmployees();
    }
}
