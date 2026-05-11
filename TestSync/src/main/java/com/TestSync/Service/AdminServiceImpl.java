package com.TestSync.Service;

import com.TestSync.Model.AdminModel;
import com.TestSync.Repositry.AdminRepository;

public class AdminServiceImpl implements AdminService{
	AdminRepository adminRepository;
	@Override
	public boolean validateAdmin(AdminModel model) {
		// TODO Auto-generated method stub
		return adminRepository.isValidateAdmin(model);
	}
	

}
