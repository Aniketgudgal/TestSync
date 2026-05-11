package com.TestSync.Service;

import com.TestSync.Model.AdminModel;
import com.TestSync.Repositry.AdminRepository;
import com.TestSync.Repositry.AdminRepositoryImpl;

public class AdminServiceImpl implements AdminService{ 
	AdminRepositoryImpl adminRepositoryImpl = new AdminRepositoryImpl();
	@Override
	public boolean validateAdmin(AdminModel model) {
		 
		return adminRepositoryImpl.isValidateAdmin(model);
	}
	

}
