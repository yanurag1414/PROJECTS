package com.anurag.TO_DO_LIST.service;

import com.anurag.TO_DO_LIST.model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    //giving the location where the tasks are saved
    private final String filePath = "src/main/resources/tasks.json";

    //Initializing list of tasks
    private List<Task> tasks;

    public TaskService(){
        loadTasks();
    }

    private void loadTasks() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filePath);
        if (file.exists() && file.length() != 0) { // Check if the file exists and is not empty
            try {
                tasks = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Task.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            tasks = new ArrayList<>(); // Initialize with an empty list if the file doesn't exist or is empty
        }
    }


    private void saveTasks(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(filePath),tasks);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks(){
        return tasks;
    }
    public void addTask(Task task){
        tasks.add(task);
        saveTasks();
    }

    public void updateTaskStatus(int id, String status){
        for(Task task:tasks){
            if(task.getId()==id){
                task.setStatus(status);
                saveTasks();
                break;
            }
        }
    }

    public void deleteTask(int id){
        tasks.removeIf(task->task.getId()==id);
        saveTasks();
    }

    public List<Task> getTasksByStatus(String status){
        List<Task> filteredTasks = new ArrayList<>();

        for(Task task:tasks){
            if(task.getStatus().equals(status)){
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}
