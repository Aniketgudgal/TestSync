package com.TestSync.Repositry;

import java.util.List;

import java.util.Optional;

import com.TestSync.Model.StudentModel;

public interface StudentRepo {
	int isRegister(StudentModel ul);
	
	Optional<List<Object[]>> getExamScheduleInfoPending(int id);
	Optional<List<Object[]>> getExamScheduleInfoCompleted(int id);
	
	Optional<String> getStartTime(int id);
	
	void updateAttempt();

	public boolean registerStudent(StudentModel model);
	
}
