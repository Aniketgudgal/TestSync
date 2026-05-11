package com.TestSync.Repositry;

import java.sql.*;


import com.TestSync.Model.AdminModel;

public class AdminRepositoryImpl extends DBConfig implements AdminRepository{
	@Override
	public boolean isValidateAdmin(AdminModel model) { 
		try {
			pst = conn.prepareStatement("select * from admin where email = ? AND password = ?");
			rs = pst.executeQuery();
			
			
		}
		catch (SQLException e) { 
			System.out.println("Erro is "+e);
		}
		return false;
	}

}
