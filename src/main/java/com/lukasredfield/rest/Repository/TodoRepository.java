package com.lukasredfield.rest.Repository;

import com.lukasredfield.rest.Model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Tarea, Long> {
}

