package com.database.implementation.teacherRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.database.implementation.TeacherEntity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
