import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.org.sql2o.*;

public class Task {
  private String Description;
  private boolean Completed;
  private LocalDateTime CreatedAt;
  private int Id;

  public Task(String description) {
    this.description = description;
    completed = false;
    CreatedAt = LocalDateTime.now();
  }

  public static List<Task> all() {
    String sql = "SELECT id, description FROM tasks";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Task.class);
    }
  }

  public void save() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "INSERT INTO tasks (description) VALUES (:description)";
     this.id = (int) con.createQuery(sql, true)
       .addParameter("description", this.description)
       .executeUpdate();
       .getKey();
   }
  }

  public String getDescription() {
    return description;
  }

  public boolean isCompleted()  {
    return completed;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public int getId()  {
    return id;
  }
}
