package com.TestSync.Repositry;

import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.plugins.tiff.ExifGPSTagSet;

import com.TestSync.Model.CourseModel;

public class CourseRepoImpl extends DBConfig implements CourseRepo{
	List<CourseModel> courseList;
	@Override
	public Optional<List<CourseModel>> getAllCourses() {
		 
		try {
			courseList = new ArrayList<>();
			pst = conn.prepareStatement("select*from subject");
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				CourseModel model = new CourseModel();
				model.setCourseId(rs.getInt(1));
				model.setCourseName(rs.getString(2));
				courseList.add(model);
			}
			return Optional.empty();
			
		}
		catch(SQLException e)
		{
			System.out.println("error is "+e);
		}
		return null;
	}

}
