package com.TestSync.Repositry;

import com.TestSync.Controller.UserLoginController;

public class StudentRepoImp extends DBConfig implements StudentRepo{

	@Override
	public int isRegister(UserLoginController ul) {
		try
		{
			pst = conn.prepareStatement("select * from student where username = ? and password = ? ");
		}catch(Exception ex)
		{
			System.out.println("Exception in DB");
		}
		return 0;
	}

}
