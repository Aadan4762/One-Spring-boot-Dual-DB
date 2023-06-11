package com.database.implementation.controller;

import java.util.List;

import com.database.implementation.teacherRepository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.database.implementation.builder.ObjectBuilder;
import com.database.implementation.StudentEntity.Student;
import com.database.implementation.StudentEntity.StudentDTO;
import com.database.implementation.TeacherEntity.Teacher;
import com.database.implementation.TeacherEntity.TeacherDTO;
import com.database.implementation.studentRepository.StudentRepository;

@RestController
public class StudentTeacherRestController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@PostMapping("/saveStudent")
	public Student saveStudent(@RequestBody StudentDTO studentDTO) {
		Student student = ObjectBuilder.createStudentFromStudentDTO(studentDTO);
		return studentRepository.save(student);
	}
	
	@PostMapping("/saveTeacher")
	public Teacher saveTeacher(@RequestBody TeacherDTO teacherDTO) {
		Teacher teacher = ObjectBuilder.createTeacherFromTeacherDTO(teacherDTO);
		return teacherRepository.save(teacher);
	}
	
	@GetMapping("/getAllStudents")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	@GetMapping("/getAllTeachers")
	public List<Teacher> getAllTeachers(){
		return teacherRepository.findAll();
	}
}
