package com.lukasredfield.rest.Controller;

import com.lukasredfield.rest.Model.Tarea;
import com.lukasredfield.rest.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    @Autowired                                 //TE INSTANCIA EL REPOSITORIO DENTRO DE LA CLASE
    private TodoRepository todoRepository;

    @Controller
    public class HomeController {

        @RequestMapping("/")
        public String home() {
            return "redirect:/swagger-ui/index.html";
        }
    }

    @GetMapping(value= "/tareas")
    public List<Tarea> getTareas(){

        return todoRepository.findAll();
    }

    @PostMapping(value="/guardartarea")
    public String guardarTarea(@RequestBody Tarea tarea){
        todoRepository.save(tarea);
        return "Tarea guardada";
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
        if (borrarTarea.getId() <= 3){
            return "No se puede borrar este usuario";
        }else {
            todoRepository.delete(borrarTarea);
            return "Tarea borrada";
        }
    }

}


