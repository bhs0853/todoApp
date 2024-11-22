package com.bhs.todoapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public ResponseEntity<List<Todo>> getTodoList(){
        return ResponseEntity.ok(todoRepository.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Todo>> searchTodos(searchTodo searchTodo){
        return ResponseEntity.ok(todoRepository.findBy(searchTodo.id,searchTodo.user_id,searchTodo.title));
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Todo>> getCompletedTodos(){
        return ResponseEntity.ok(todoRepository.findCompleted());
    }

    @PostMapping
    public ResponseEntity<?> addNewTodo(@RequestBody Todo newTodo){
        System.out.println(newTodo);
        todoRepository.save(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateTodoTitle(@PathVariable("id") int id,@RequestBody Todo updatetodo){
        todoRepository.updateTitle(id,updatetodo.getTitle());
        todoRepository.updateStatus(id,updatetodo.isCompleted());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") int id){
        if(todoRepository.existsById(id)){
            todoRepository.deleteById(id);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}