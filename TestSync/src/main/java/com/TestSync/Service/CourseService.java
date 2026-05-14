package com.TestSync.Service;

import java.util.List;
import java.util.Optional;

import com.TestSync.Model.CourseModel;

public interface CourseService {
	
	public Optional<List<CourseModel>> getAllCourses();
	
}
