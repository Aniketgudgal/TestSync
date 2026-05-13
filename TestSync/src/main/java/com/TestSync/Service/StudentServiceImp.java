package com.TestSync.Service;

import java.util.List;
import java.util.Optional;

import com.TestSync.Model.StudentModel;
import com.TestSync.Repositry.StudentRepo;
import com.TestSync.Repositry.StudentRepoImp;

public class StudentServiceImp  implements StudentService{
	StudentRepo sr = new StudentRepoImp();
	@Override
	public int isRegister(StudentModel ul) {
		return sr.isRegister(ul);
	}
	
	
}
