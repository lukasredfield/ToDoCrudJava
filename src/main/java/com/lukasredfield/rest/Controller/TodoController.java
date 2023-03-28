package com.lukasredfield.rest.Controller;

import com.lukasredfield.rest.Model.Tarea;
import com.lukasredfield.rest.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;
    @GetMapping(value = "/")
    public String holamundo(){
        return "Hola Mundo!";
    }

    @GetMapping(value= "/tareas")
    public List<Tarea> getTareas(){
        return todoRepository.findAll();
    }

    @PostMapping(value="/guardartarea")
    public String guardarTarea(@RequestBody Tarea tarea){
        todoRepository.save(tarea);
        return "Saved task";
    }

    @PutMapping(value="/update/{id}")
    public String updateTarea(@PathVariable long id, @RequestBody Tarea tarea){
        Tarea updatedTarea = todoRepository.findById(id).get();
        updatedTarea.setTitulo(tarea.getTitulo());
        updatedTarea.setDescripcion(tarea.getDescripcion());
        todoRepository.save(updatedTarea);
        return "Tarea actualizada";
    }

    @DeleteMapping(value="borrar/{id}")
    public String borrarTarea(@PathVariable long id){
        Tarea borrarTarea = todoRepository.findById(id).get();
        todoRepository.delete(borrarTarea);
        return "Tarea borrada";
    }



}


