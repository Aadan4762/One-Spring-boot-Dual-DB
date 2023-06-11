package com.database.implementation.builder;

import com.database.implementation.StudentEntity.Student;
import com.database.implementation.StudentEntity.StudentDTO;
import com.database.implementation.TeacherEntity.Teacher;
import com.database.implementation.TeacherEntity.TeacherDTO;

public class ObjectBuilder {
	
	
	public  static Student createStudentFromStudentDTO(StudentDTO studentDTO) {
		Student student = new Student();
		student.setName(studentDTO.getName());
		student.setSchoolName(studentDTO.getSchoolName());
		student.setStandard(studentDTO.getStandard());
		return student;
	}

	
	public static  Teacher createTeacherFromTeacherDTO(TeacherDTO teacherDTO) {
		
		Teacher teacher = new Teacher();
		teacher.setName(teacherDTO.getName());
		teacher.setSubject(teacherDTO.getSubject());
		return teacher;
	}
}
