package com.TestSync.Repositry;

import java.util.List;

import java.util.Optional;

import com.TestSync.Model.AdminModel;
import com.TestSync.Model.ExamModel;
import com.TestSync.Model.SubjectModel;

public interface AdminRepository {
	public AdminModel isValidateAdmin(AdminModel model);
	public boolean addSubject(SubjectModel m);
	Optional<List<SubjectModel>> getSubject();
	boolean deleteSubject(int id);
	boolean addExam(ExamModel m);
	
	Optional<List<ExamModel>> getExam();
	Optional<List<Object[]>> getExamWithSubject();
}
