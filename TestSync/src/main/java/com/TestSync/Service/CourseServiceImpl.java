package com.TestSync.Service;

import java.util.List;
import java.util.Optional;

import com.TestSync.Model.CourseModel;
import com.TestSync.Repositry.CourseRepoImpl;

public class CourseServiceImpl implements CourseService{
	CourseRepoImpl c = new CourseRepoImpl();
	@Override
	public Optional<List<CourseModel>> getAllCourses() {
		return c.getAllCourses();
	} 

}
