public class TaskManagementSystem {
    static class Task {
        int taskId;
        String taskName;
        String status;
        Task next;

        Task(int taskId, String taskName, String status) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.status = status;
        }
    }

    static Task head;

    static void addTask(int taskId, String taskName, String status) {
        Task newTask = new Task(taskId, taskName, status);
        if (head == null) {
            head = newTask;
            return;
        }
        Task current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newTask;
    }

    static Task searchTask(int taskId) {
        Task current = head;
        while (current != null) {
            if (current.taskId == taskId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    static void traverseTasks() {
        Task current = head;
        while (current != null) {
            System.out.println(current.taskId + " " + current.taskName + " " + current.status);
            current = current.next;
        }
    }

    static void deleteTask(int taskId) {
        if (head == null) {
            return;
        }
        if (head.taskId == taskId) {
            head = head.next;
            return;
        }
        Task current = head;
        while (current.next != null && current.next.taskId != taskId) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public static void main(String[] args) {
        addTask(1, "Design landing page", "Pending");
        addTask(2, "Fix login bug", "In Progress");
        addTask(3, "Write API docs", "Pending");
        traverseTasks();
        Task found = searchTask(2);
        System.out.println("Found task: " + (found != null ? found.taskName : "not found"));
        deleteTask(1);
        System.out.println("After deleting task 1:");
        traverseTasks();
    }
}
