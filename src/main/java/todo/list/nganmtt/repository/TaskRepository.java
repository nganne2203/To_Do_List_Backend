package todo.list.nganmtt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todo.list.nganmtt.model.Task;
import todo.list.nganmtt.model.User;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    Task getTaskById(String id);
    Task getTaskByUser(User user);
    List<Task> findAllByUserId(String userId);
    void deleteTaskById(String id);
}
