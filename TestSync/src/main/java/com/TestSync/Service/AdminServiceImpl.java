package com.TestSync.Service;

import java.util.List;
import java.util.Optional;

import com.TestSync.Model.AdminModel;
import com.TestSync.Model.ExamModel;
import com.TestSync.Model.SubjectModel;
import com.TestSync.Repositry.AdminRepository;
import com.TestSync.Repositry.AdminRepositoryImpl;

public class AdminServiceImpl implements AdminService{ 
	AdminRepositoryImpl adminRepositoryImpl = new AdminRepositoryImpl();
	@Override
	public AdminModel validateAdmin(AdminModel model) {
		 
		return adminRepositoryImpl.isValidateAdmin(model);
	}
	@Override
	public boolean addSubject(SubjectModel m) {
		return adminRepositoryImpl.addSubject(m);
	}
	@Override
	public Optional<List<SubjectModel>> getSubject() {
		return adminRepositoryImpl.getSubject();
	}
	@Override
	public boolean deleteSubject(int id) {
		return adminRepositoryImpl.deleteSubject(id);
	}
	@Override
	public boolean addExam(ExamModel m) {
		return adminRepositoryImpl.addExam(m);
	}
	@Override
	public Optional<List<ExamModel>> getExam() {
		
		return adminRepositoryImpl.getExam();
	}
	@Override
	public Optional<List<Object[]>> getExamWithSubject() {
		return adminRepositoryImpl.getExamWithSubject();
	}
	

}
