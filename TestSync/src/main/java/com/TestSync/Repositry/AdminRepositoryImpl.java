package com.TestSync.Repositry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.TestSync.Model.AdminModel;
import com.TestSync.Model.ExamModel;
import com.TestSync.Model.QuestionModel;
import com.TestSync.Model.SubjectModel;

public class AdminRepositoryImpl extends DBConfig implements AdminRepository{
	private List<Object[]> list;
	@Override
	public AdminModel isValidateAdmin(AdminModel model) { 
		try {
			pst = conn.prepareStatement("select * from admin where email = ? AND password = ?");
			pst.setString(1, model.getEmail());
			pst.setString(2, model.getPassword());
			
			rs = pst.executeQuery();
			if(rs.next())
			{
				AdminModel adminModel = new AdminModel();
				 adminModel.setId(rs.getInt(1));
				 adminModel.setName(rs.getString(2));
				 adminModel.setEmail(rs.getString(3));
				 adminModel.setPassword(rs.getString(4));
				 
				 return adminModel;
			}
				 
			
			
		}
		catch (SQLException e) { 
			System.out.println("Erro is "+e);
		}
		return null;
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

	@Override
	public Optional<List<Object[]>> getExamWithSubject() {
		try
		{
			pst = conn.prepareStatement("select e.exam_name, s.subject_name, e.total_questions, e.total_marks, e.exam_duration from exam e inner join subject s on e.subject_id = s.subject_id");
			List<Object[]> al = new ArrayList<>();
			rs = pst.executeQuery();
			while(rs.next())
			{
				al.add(new Object[] {rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)});				
			}
			return Optional.of(al);
		}catch(SQLException ex)
		{
			System.out.println("Problem to get Data: "+ex);
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<List<Object[]>> getAllStudents() {
		 try {
			 list = new ArrayList<>();
			 pst = conn.prepareStatement("select st.student_name,st.email,st.username,sub.subject_name,st.mobile from student st Left join subject sub on st.course_id = sub.subject_id");
			 rs = pst.executeQuery();
			 
			 while(rs.next())
			 { 
				 Object[] obj = new Object[5];
				 
				 	obj[0] = rs.getString(1); // name
		            obj[1] = rs.getString(2); // email
		            obj[2] = rs.getString(3); // username
		            obj[3] = rs.getString(4); // subject name
		            obj[4] = rs.getString(5); // mobile
				 list.add(obj);
			 }
			 return Optional.of(list);
			 
		 } catch(SQLException e)
		 {
			 System.out.println("Repository erro  "+e);
			 return Optional.empty();
		 }
		
	}

	@Override
	public boolean addQuestion(QuestionModel model) {
		try
		{
			pst = conn.prepareStatement("insert into questions value('0', ? , ?, ?, ?, ?, ?, ?)");
			pst.setInt(1, model.getExamId());
			pst.setString(2, model.getQuestionText());
			pst.setString(3, model.getOp1());
			pst.setString(4, model.getOp2());
			pst.setString(5, model.getOp3());
			pst.setString(6, model.getOp4());
			pst.setString(7, model.getCorrectOp());
			return pst.executeUpdate() > 0 ? true : false;
		}catch(SQLException ex)
		{
			System.out.println("Problem to add question: "+ex);
			return false;
		}
	}

	@Override
	public Optional<List<Object[]>> getQuestion() {
		try
		{
			pst = conn.prepareStatement("select e.exam_name, q.question_text, q.option1, q.option2, q.option3, q.option4, q.correct_answer from questions q inner join exam e on e.exam_id = q.exam_id");
			rs = pst.executeQuery();
			List<Object[]> al = new ArrayList<>();
			
			while(rs.next())
			{
				al.add(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
			}
			return Optional.of(al);
		}catch(SQLException ex)
		{
			System.out.println("Problem to get Data of question: "+ex);
			return Optional.empty();
		}
	}

}
