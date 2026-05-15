package com.TestSync.Service;

import java.util.List;
import java.util.Optional;

import com.TestSync.Model.QuestionModel;
import com.TestSync.Model.StudentModel;
import com.TestSync.Repositry.StudentRepo;
import com.TestSync.Repositry.StudentRepoImp;

public class StudentServiceImp  implements StudentService{
	StudentRepo sr = new StudentRepoImp();
	@Override
	public int isRegister(StudentModel ul) {
		return sr.isRegister(ul);
	}
	@Override
	public Optional<List<Object[]>> getExamScheduleInfoPending(int id) {
		return sr.getExamScheduleInfoPending(id);
	}
	
	@Override
	public Optional<List<Object[]>> getExamScheduleInfoCompleted(int id) {
		return sr.getExamScheduleInfoCompleted(id);
	}
	@Override
	public Optional<String> getStartTime(int id) {
		return sr.getStartTime(id);
	}
	@Override
	public void updateAttempt() {
		sr.updateAttempt();
	}
	public boolean idAddedRecord(StudentModel model) {
		// TODO Auto-generated method stub
		return sr.registerStudent(model);
	}
	@Override
	public Optional<List<QuestionModel>> getQuestions(int start, int recordPage, int es_id) {
		return sr.getQuestions(start, recordPage, es_id);
	}
	@Override
	public Optional<List<Object[]>> getStudentById(int id) {
		// TODO Auto-generated method stub
		return sr.getStudentById(id);
	}
	@Override
	public boolean updateStudentProfile(StudentModel model) {
		// TODO Auto-generated method stub
		return sr.isUpdatedStudentProfile(model);
	}
}
