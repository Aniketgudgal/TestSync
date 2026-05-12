package com.TestSync.Repositry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.TestSync.Model.AdminModel;
import com.TestSync.Model.ExamModel;
import com.TestSync.Model.SubjectModel;

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

	@Override
	public boolean addSubject(SubjectModel m) {
		try
		{
			pst = conn.prepareStatement("insert into subject values('0', ?)");
			pst.setString(1, m.getName());
			return pst.executeUpdate() > 0 ? true: false;
		}
		catch(SQLException ex)
		{
			System.out.println("Problem to add subject: "+ex);
			return false;
		}
	}

	@Override
	public Optional<List<SubjectModel>> getSubject() {
		try
		{
			pst = conn.prepareStatement("select * from subject");
			rs = pst.executeQuery();
			List<SubjectModel> al = new ArrayList<>();
			while(rs.next())
			{
				al.add(new SubjectModel(rs.getInt(1), rs.getString(2)));
			}
			return Optional.of(al);
		}catch(SQLException ex)
		{
			System.out.println("Exception of get subject: "+ex);
			return Optional.empty();
		}
	}

	@Override
	public boolean deleteSubject(int id) {
		try
		{
			pst = conn.prepareStatement("delete from subject where subject_id = ?");
			pst.setInt(1, id);
			return pst.executeUpdate() > 0 ?  true : false;
		}catch(SQLException ex)
		{
			System.out.println("Problem to delete subject: "+ex);
			return false;
		}
		
	}

	@Override
	public boolean addExam(ExamModel m) {
		try
		{
			pst = conn.prepareStatement("insert into exam values('0', ?, ?, ?, ?, ?)");
			pst.setString(1, m.getExamName());
			pst.setInt(2, m.getSubjectId());
			pst.setInt(3, m.getTotalQuestions());
			pst.setInt(4, m.getTotalMarks());
			pst.setInt(5, m.getExamDuration());
			return pst.executeUpdate() > 0 ? true:false;
			
		}catch(SQLException ex)
		{
			System.out.println("Exception to add exam");
			return false;
		}
	}

	@Override
	public Optional<List<ExamModel>> getExam() {
		try
		{
			pst = conn.prepareStatement("select * from exam");
			rs = pst.executeQuery();
			List<ExamModel> m = new ArrayList<>();
			while(rs.next())
			{
				m.add(new  ExamModel(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
			}
			return Optional.of(m);
		}catch(SQLException ex)
		{
			System.out.println("Exception to get exam details: "+ex);
			return Optional.empty();
		}
	}

}
