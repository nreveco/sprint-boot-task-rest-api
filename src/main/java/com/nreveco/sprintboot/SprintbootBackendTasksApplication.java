package com.nreveco.sprintboot;

import com.nreveco.sprintboot.model.Tasks;
import com.nreveco.sprintboot.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SprintbootBackendTasksApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SprintbootBackendTasksApplication.class, args);
	}

	@Autowired
	private TasksRepository tasksRepository;

	@Override
	public void run(String... args) throws Exception {

	}

}
