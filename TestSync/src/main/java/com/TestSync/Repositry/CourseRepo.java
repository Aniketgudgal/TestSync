package com.TestSync.Repositry;

import java.util.List;
import java.util.Optional;

import com.TestSync.Model.CourseModel;

public interface CourseRepo {

	public Optional<List<CourseModel>> getAllCourses();
}
