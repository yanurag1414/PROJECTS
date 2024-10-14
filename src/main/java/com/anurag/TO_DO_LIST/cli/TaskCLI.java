package com.anurag.TO_DO_LIST.cli;

import com.anurag.TO_DO_LIST.model.Task;
import com.anurag.TO_DO_LIST.service.TaskService;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import java.util.Scanner;

@Component
public class TaskCLI implements CommandLineRunner {
    private final TaskService taskService;

    public TaskCLI(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        String action = args.length > 0 ? args[0] : "";

        switch (action) {
            case "add":
                System.out.print("Enter task title: ");
                String title = scanner.nextLine();
                int newId = taskService.getAllTasks().size() + 1;
                taskService.addTask(new Task(newId, title, "not-done"));
                System.out.println("Task added successfully.");
                break;

            case "update":
                System.out.print("Enter task ID to update: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter new status (not-done, in-progress, done): ");
                String status = scanner.nextLine();
                taskService.updateTaskStatus(id, status);
                System.out.println("Task updated successfully.");
                break;

            case "delete":
                System.out.print("Enter task ID to delete: ");
                int taskId = scanner.nextInt();
                taskService.deleteTask(taskId);
                System.out.println("Task deleted successfully.");
                break;

            case "list":
                System.out.println("All tasks:");
                taskService.getAllTasks().forEach(System.out::println);
                break;

            case "list-done":
                System.out.println("Completed tasks:");
                taskService.getTasksByStatus("done").forEach(System.out::println);
                break;

            case "list-not-done":
                System.out.println("Pending tasks:");
                taskService.getTasksByStatus("not-done").forEach(System.out::println);
                break;

            case "list-in-progress":
                System.out.println("Tasks in progress:");
                taskService.getTasksByStatus("in-progress").forEach(System.out::println);
                break;

            default:
                System.out.println("Invalid action. Use add, update, delete, list, list-done, list-not-done, or list-in-progress.");
                break;
        }
    }
}
