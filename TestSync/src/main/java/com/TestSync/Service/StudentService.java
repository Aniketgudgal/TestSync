package com.TestSync.Service;

import java.util.List;

import java.util.Optional;

import com.TestSync.Controller.UserLoginController;
import com.TestSync.Model.ExamModel;
import com.TestSync.Model.ExamScheduleModel;
import com.TestSync.Model.QuestionModel;
import com.TestSync.Model.ResultModel;
import com.TestSync.Model.StudentModel;

public interface StudentService {
	public int isRegister(StudentModel ul);
	
	Optional<List<Object[]>> getExamScheduleInfoPending(int id);
	Optional<List<Object[]>> getExamScheduleInfoCompleted(int id);
	
	Optional<String> getStartTime(int id);
	
	void updateAttempt();
	public boolean idAddedRecord(StudentModel model);
	
	Optional<List<QuestionModel>> getQuestions(int start, int recordPage, int es_id);
 
	
	Optional<List<Object[]>> getStudentById(int id);
	
	public boolean updateStudentProfile(StudentModel model);
	
 

	public boolean addResult(ResultModel m);
	
	Optional<ExamScheduleModel> getExamSchedule(int id);
	
	Optional<ExamModel> getExam(int id);
	boolean updateExamScheduleAttemp(int id); 
}
