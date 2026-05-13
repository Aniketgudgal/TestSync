package com.TestSync.Service;

import java.util.List;
import java.util.Optional;


import com.TestSync.Model.AdminModel;
import com.TestSync.Model.ExamModel;
import com.TestSync.Model.QuestionModel;
import com.TestSync.Model.SubjectModel;

public interface AdminService {
	public AdminModel validateAdmin(AdminModel model);
	 
	public boolean addSubject(SubjectModel m);
	Optional<List<SubjectModel>> getSubject();
	boolean deleteSubject(int id);
	
	boolean addExam(ExamModel m);
	Optional<List<ExamModel>> getExam();
	Optional<List<Object[]>> getExamWithSubject();
<<<<<<< HEAD

	public Optional<List<Object[]>> getAllStudents();
=======
	
	boolean addQuestion(QuestionModel model);
	Optional<List<Object[]>> getQuestion();
>>>>>>> 5be21af387e95bc1f611da4a4d7d02ff5580e63a
}
