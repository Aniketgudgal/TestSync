package com.TestSync.Repositry;

import com.TestSync.Model.StudentModel;

public class StudentRepoImp extends DBConfig implements StudentRepo{

	@Override
	public int isRegister(StudentModel ul) {
		try
		{
			pst = conn.prepareStatement("select * from student where username = ? and password = ? ");
 
			pst.setString(1, ul.getUserName());
			pst.setString(2, ul.getPassword());
			rs = pst.executeQuery();
			if(rs.next())
			{
				return rs.getInt(1);
			}
			else
			{
				return -1;
			}
		}catch(Exception ex)
		{
			System.out.println("Exception in DB");
		}
		return -1;
	}

}
