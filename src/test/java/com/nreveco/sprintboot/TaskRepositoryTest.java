package com.nreveco.sprintboot;

import com.nreveco.sprintboot.model.Tasks;
import com.nreveco.sprintboot.repository.TasksRepository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.assertj.core.api.Assertions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Autowired
    DataSource dataSource;

    @Autowired
    private TasksRepository tasksRepository;

    @Test
    @Order(1)
    @Rollback(value = true)
    public void saveTasksTest() {
        Tasks task = Tasks.builder().description("task test creation").active(1).creationDate(new Date()).build();
        tasksRepository.save(task);

        Assertions.assertThat(task.getIdentifier()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = true)
    public void getTasksTest(){
        Tasks task = Tasks.builder().description("get task test").active(1).creationDate(new Date()).build();
        tasksRepository.save(task);

        Tasks taskFind = tasksRepository.findById(task.getIdentifier()).get();
        Assertions.assertThat(taskFind.getIdentifier()).isEqualTo(task.getIdentifier());
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void getListOfTasksTest(){
        List <Tasks> tasks = tasksRepository.findAll();

        Assertions.assertThat(tasks).asList();
        Assertions.assertThat(tasks.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = true)
    public void updateTasksTest(){
        Tasks task = Tasks.builder().description("update task test").active(1).creationDate(new Date()).build();
        tasksRepository.save(task);

        Tasks taskFind = tasksRepository.findById(task.getIdentifier()).get();

        taskFind.setActive(0);
        taskFind.setDescription("update task test update 1");
        taskFind.setCreationDate(new Date());
        Tasks taskUpdate = tasksRepository.save(taskFind);

        Assertions.assertThat(taskUpdate).isNotNull();
        Assertions.assertThat(taskUpdate.getIdentifier()).isEqualTo(taskFind.getIdentifier());
        Assertions.assertThat(taskUpdate.getDescription()).isEqualTo("update task test update 1");
    }

    @Test
    @Order(5)
    @Rollback(value = true)
    public void deleteTaskTest(){
        Tasks task = Tasks.builder().description("delete task test").active(1).creationDate(new Date()).build();
        tasksRepository.save(task);

        Tasks taskDeleteByDescription = tasksRepository.findByDescription("delete task test").get();
        tasksRepository.delete(taskDeleteByDescription);

        Optional<Tasks> optionalTasks = tasksRepository.findByDescription("delete task test");

        Tasks taskPresent = null;
        if(optionalTasks.isPresent()){
            taskPresent = optionalTasks.get();
        }
        Assertions.assertThat(taskPresent).isNull();
        Assertions.assertThat(optionalTasks).isEmpty();
    }

    //String userJson = new User("My new User", "myemail@gmail.com").toJson();
}
