package com.TestSync.Service;

import java.util.List;
import java.util.Optional;

import com.TestSync.Controller.UserLoginController;
import com.TestSync.Model.StudentModel;

public interface StudentService {
	public int isRegister(StudentModel ul);
	
	Optional<List<Object[]>> getExamScheduleInfoPending(int id);
	Optional<List<Object[]>> getExamScheduleInfoCompleted(int id);
	
	Optional<String> getStartTime(int id);
	
	void updateAttempt();
}
