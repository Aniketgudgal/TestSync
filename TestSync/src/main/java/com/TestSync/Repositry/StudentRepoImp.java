package com.TestSync.Repositry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Override
	public boolean registerStudent(StudentModel model) {
		try {
			pst = conn.prepareStatement("insert into Student values('0',?,?,?,?,?,?)");
			pst.setString(1, model.getName());
			pst.setString(2, model.getEmail());
			pst.setString(3, model.getUserName());
			pst.setString(4, model.getPassword());
			pst.setInt(5, model.getCourseId());
			pst.setString(6, model.getMobile());
			
			return pst.executeUpdate() > 0 ? true : false;
			
		} catch(SQLException e)
		{
			System.out.println("Error in Repository "+e);
			return false;
			
		}
		
		
	}

	

}
