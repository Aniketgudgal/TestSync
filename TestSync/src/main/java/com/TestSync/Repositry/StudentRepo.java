package com.TestSync.Repositry;

import java.util.List;

import java.util.Optional;

import com.TestSync.Model.ExamModel;
import com.TestSync.Model.ExamScheduleModel;
import com.TestSync.Model.QuestionModel;
import com.TestSync.Model.ResultModel;
import com.TestSync.Model.StudentModel;

public interface StudentRepo {
	int isRegister(StudentModel ul);
	
	Optional<List<Object[]>> getExamScheduleInfoPending(int id);
	Optional<List<Object[]>> getExamScheduleInfoCompleted(int id);	
	Optional<String> getStartTime(int id);	
	void updateAttempt();
	public boolean registerStudent(StudentModel model);	
	Optional<List<QuestionModel>>	getQuestions(int start, int recordPage, int es_id);
 
	Optional<List<Object[]>> getStudentById(int id);
	public boolean isUpdatedStudentProfile(StudentModel model);
 
	
	Optional<ExamScheduleModel> getExamSchedule(int id);
	Optional<ExamModel> getExam(int id);
	
	boolean addResult(ResultModel m);
	boolean updateExamScheduleAttemp(int id); 
	Optional<List<Object[]>> getResult(int id);
}
