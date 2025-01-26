package com.firstproject.firstproject.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity(
        name = "Student"
)
@Table(
        name = "students"
)
public class Student {
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @GeneratedValue(
            generator = "uuid"
    )
    @Column(
            name = "id"
    )
    @Id
    private String id;
    @Column(
            name = "student_name"
    )
    private String name;
    @Column(
            name = "student_course"
    )
    private String course;

    public Student(){

    }
    public Student(String name,String course){
        this.name = name;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
