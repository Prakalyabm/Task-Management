package com.Todo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(length = 100)
    private String username;

    private String email;
    private String password;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Task> tasks = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public List<Task> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(List<Task> tasks) {
//        this.tasks = tasks;
//    }
//
//    public void addTask(Task task) {
//        tasks.add(task);
//        task.setUser(this); // Fix the method name to setUser
//    }
//
//    public void removeTask(Task task) {
//        tasks.remove(task);
//        task.setUser(null); // Fix the method name to setUser
    
    
    @Override
   	public String toString() {
   		return "User [username=" + username + ", email=" + email + ", password=" + password + "]";
   	}
    }

