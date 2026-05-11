package com.TestSync.Repositry;

import java.sql.*;


import com.TestSync.Model.AdminModel;

public class AdminRepositoryImpl extends DBConfig implements AdminRepository{
	@Override
	public boolean isValidateAdmin(AdminModel model) { 
		try {
			pst = conn.prepareStatement("select * from admin where email = ? AND password = ?");
			pst.setString(1, model.getEmail());
			pst.setString(2, model.getPassword());
			
			rs = pst.executeQuery();
			if(rs.next())
				return true;
			
			
		}
		catch (SQLException e) { 
			System.out.println("Erro is "+e);
		}
		return false;
	}

}
