package com.tientt.todoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tientt.todoapp.entity.Task;

import jakarta.persistence.EntityResult;
import jakarta.persistence.FieldResult;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.transaction.Transactional;

@SqlResultSetMapping(name = "TaskMapping", entities = @EntityResult(entityClass = Task.class, fields = {
                @FieldResult(name = "id", column = "id"),
                @FieldResult(name = "name", column = "name"),
                @FieldResult(name = "description", column = "description"),
                @FieldResult(name = "status", column = "status"),
                @FieldResult(name = "user", column = "user_id"),
                @FieldResult(name = "tags", column = "tag_id"),
                @FieldResult(name = "startDate", column = "start_date"),
                @FieldResult(name = "dueDate", column = "due_date"),
                @FieldResult(name = "createdAt", column = "created_at"),
                @FieldResult(name = "modifiedAt", column = "modified_at")
}))

public interface TaskRepository extends JpaRepository<Task, Long> {
        @Query(name = "TaskMapping", nativeQuery = true, value = "SELECT t.id, t.name, t.description, t.status, t.user_id, tt.tag_id, "
                        +
                        "t.start_date, t.due_date, t.created_at, t.modified_at " +
                        "FROM task t " +
                        "JOIN task_tag tt ON t.id = tt.task_id " +
                        "WHERE t.user_id = :id")
        public List<Task> getTasksByUserId(@Param("id") Long id);

        @Transactional
        @Modifying
        @Query(value = "DELETE FROM task_tag WHERE task_id = :id", nativeQuery = true)
        void deleteTaskTagsByTaskId(@Param("id") Long id);
}
