package com.firstproject.firstproject.repository;

import com.firstproject.firstproject.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
}
