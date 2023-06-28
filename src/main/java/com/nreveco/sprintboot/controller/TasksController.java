package com.nreveco.sprintboot.controller;

import com.nreveco.sprintboot.exception.ResourceNotFoundException;
import com.nreveco.sprintboot.model.Tasks;
import com.nreveco.sprintboot.repository.TasksRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/tasks")
public class TasksController {

    @Autowired
    private TasksRepository tasksRepository;


    @GetMapping
    public List<Tasks> getAllTasks(){
        return tasksRepository.findAll();
    }

    @PostMapping
    public Tasks createTasks(@RequestBody Tasks tasks){
        return tasksRepository.save(tasks);
    }

    @Operation(summary = "Obtener tareas mediante Identificador", description = "Obtiene el objeto tasks filtrando por identificador. La respuesta del objeto tarea contiene identifier, description, creationDate, Active = estado vigente.", tags = { "Obtener tarea mediante identificador" })
    @ApiResponses({@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Tasks.class), mediaType = "application/json") }), @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }), @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("{identifier}")
    public ResponseEntity<Tasks> getTasksById(@PathVariable int identifier){
        Tasks task = tasksRepository.findById(identifier)
                .orElseThrow(()-> new ResourceNotFoundException("Task not exist with id:" + identifier));
        return ResponseEntity.ok(task);
    }

    @PutMapping("{identifier}")
    public ResponseEntity<Tasks> updateTasks(@PathVariable int identifier,@RequestBody Tasks taskDetails){
        Tasks task = tasksRepository.findById(identifier)
                .orElseThrow(()-> new ResourceNotFoundException("Task not exist with id:" + identifier));
        task.setDescription(taskDetails.getDescription());
        task.setCreationDate(taskDetails.getCreationDate());
        task.setActive(taskDetails.getActive());
        tasksRepository.save(task);

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("{identifier}")
    public ResponseEntity<Tasks> deleteTasks(@PathVariable int identifier){
        Tasks task = tasksRepository.findById(identifier)
                .orElseThrow(()-> new ResourceNotFoundException("Task not exist with id:" + identifier));

        tasksRepository.delete(task);

        return ResponseEntity.ok(task);
    }

}
