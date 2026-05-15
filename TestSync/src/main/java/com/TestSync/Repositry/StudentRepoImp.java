package com.TestSync.Repositry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.TestSync.Model.ExamModel;
import com.TestSync.Model.ExamScheduleModel;
import com.TestSync.Model.QuestionModel;
import com.TestSync.Model.ResultModel;
import com.TestSync.Model.StudentModel;

public class StudentRepoImp extends DBConfig implements StudentRepo {
	private List<Object[]> list;
	@Override
	public int isRegister(StudentModel ul) {
		try {
			pst = conn.prepareStatement("select * from student where username = ? and password = ? ");

			pst.setString(1, ul.getUserName());
			pst.setString(2, ul.getPassword());
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}
		} catch (Exception ex) {
			System.out.println("Exception in DB");
		}
		return -1;
	}

	@Override
	public Optional<List<Object[]>> getExamScheduleInfoPending(int id) {
		try {
			pst = conn.prepareStatement(
					"select c.course_name, e.exam_name, sub.subject_name, TIME_FORMAT(es.start_time, '%h:%i %p'), TIME_FORMAT(es.end_time, '%h:%i %p'), DATE_FORMAT(es.date, '%d/%m/%Y'), e.total_questions, e.total_marks, es.attempted, es.es_id from student s inner join course c on c.course_id = s.course_id inner join examschedule es on es.course_id = c.course_id inner join subject sub on sub.subject_id = es.subject_id inner join exam e on e.exam_id = es.exam_id where es.attempted = 0 AND s.student_id = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			List<Object[]> al = new ArrayList<>();
			while (rs.next()) {
				al.add(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8),
						rs.getInt(9) == 0 ? "Pending" : "Expired", rs.getInt(10)});
			}
			return Optional.of(al);
		} catch (SQLException ex) {
			System.out.println("Problem to access data from student schedule: " + ex);
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<Object[]>> getExamScheduleInfoCompleted(int id) {
		try {
			pst = conn.prepareStatement(
					"select c.course_name, e.exam_name, sub.subject_name, TIME_FORMAT(es.start_time, '%h:%i %p'), TIME_FORMAT(es.end_time, '%h:%i %p'), DATE_FORMAT(es.date, '%d/%m/%Y'), e.total_questions, e.total_marks, es.attempted, es.es_id from student s inner join course c on c.course_id = s.course_id inner join examschedule es on es.course_id = c.course_id inner join subject sub on sub.subject_id = es.subject_id inner join exam e on e.exam_id = es.exam_id inner join result r on r.student_id = s.student_id where es.attempted = 1 AND s.student_id = ? AND r.student_id = ?");
			pst.setInt(1, id);
			pst.setInt(2, id);
			rs = pst.executeQuery();
			List<Object[]> al = new ArrayList<>();
			while (rs.next()) {
				al.add(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8),
						rs.getInt(9) == 0 ? "Pending" : "Completed", rs.getInt(10) });
			}
			return Optional.of(al);
		} catch (SQLException ex) {
			System.out.println("Problem to access data from student schedule: " + ex);
		}
		return Optional.empty();
	}

	@Override
	public Optional<String> getStartTime(int id) {
		try {
			pst = conn.prepareStatement("select TIME_FORMAT(start_time, '%H:%i:%s') from examschedule where es_id = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				return Optional.of(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("Problem to fetch time: " + ex);
		}
		return Optional.empty();
	}

	@Override
	public void updateAttempt() {
		try {

			pst = conn.prepareStatement("UPDATE examschedule SET attempted = 1  WHERE date = CURDATE() AND end_time <= CURTIME()");
			pst.executeUpdate();

		} catch (SQLException ex) {

			System.out.println(ex);

		}
	}
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

	@Override
	public Optional<List<QuestionModel>> getQuestions(int start, int recordPage, int es_id) {
		try
		{
			List<QuestionModel> al = new ArrayList<>();
			pst = conn.prepareStatement("select * from questions LIMIT ? , ?");
			pst.setInt(1, start);
			pst.setInt(2, recordPage);
			rs = pst.executeQuery();
			while(rs.next())
			{
				al.add(new QuestionModel(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
			return Optional.of(al);
		}catch(SQLException ex)
		{
			System.out.println("Problem to get data: "+ex);
		}
		return Optional.empty();
	}

	@Override
 
	public Optional<List<Object[]>> getStudentById(int id) {
		 try {
			 list = new ArrayList<>();
			 pst = conn.prepareStatement("select st.student_name,st.email,st.username,sub.subject_name,st.mobile from student st Left join subject sub on st.course_id = sub.subject_id where st.student_id =?");
			 pst.setInt(1, id);
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
	public boolean isUpdatedStudentProfile(StudentModel model) {
		 
		try {
			pst = conn.prepareStatement("update student set student_name=?,email=?,username=?,mobile=? where student_id=?");
			pst.setString(1, model.getName());
			pst.setString(2, model.getEmail());
			pst.setString(3, model.getUserName());
			pst.setString(4, model.getMobile());
			pst.setInt(5,model.getId());			
			
			
			return pst.executeUpdate()>0? true:false;
			
		}
		catch(SQLException e)
		{
			System.out.println("error in Repository "+e);
			return false;
			
		} 
		
	}
 
	public boolean addResult(ResultModel m) {
		try
		{
			pst = conn.prepareStatement("insert into result value('0',?,?,?,?,?)");
			pst.setInt(1, m.getEsId());
			pst.setInt(2, m.getStudentId());
			pst.setInt(3, m.getObtainMarks());
			pst.setFloat(4, m.getPercentage());
			pst.setBoolean(5, m.isStatus());
			return pst.executeUpdate() > 0 ? true:false;
		}catch(SQLException ex)
		{
			System.out.println("Problem to add result: "+ex);
		}
		return false;
	}

	@Override
	public Optional<ExamScheduleModel> getExamSchedule(int id) {
			try
			{
				pst = conn.prepareStatement("select * from examschedule where es_id = ?");
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if(rs.next())
				{
					return Optional.ofNullable(new ExamScheduleModel(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
				}
				else
				{
					return Optional.empty();
				}
			}catch(SQLException ex)
			{
				System.out.println("Problem to get Schedule: "+ex);
			}
		return Optional.empty();
	}

	@Override
	public Optional<ExamModel> getExam(int id) {
		try
		{
			pst = conn.prepareStatement("select * from exam where exam_id = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next())
			{
				return Optional.of(new ExamModel(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
			}
			else
			{
				return Optional.empty();
			}
		}catch(SQLException ex)
		{
			System.out.println("Problem to access  exam: "+ex);
		}
		return Optional.empty();
	}

	@Override
	public boolean updateExamScheduleAttemp(int id) {
		try
		{
			pst = conn.prepareStatement("update examschedule set attempted = 1 where es_id = ?");
			pst.setInt(1, id);
			return pst.executeUpdate() > 0 ? true: false;
		}catch(SQLException ex)
		{
			System.out.println("Problem to update attempt: "+ex);
		}
		return false; 
	}

	@Override
	public Optional<List<Object[]>> getResult(int id) {
		try
		{
			pst = conn.prepareStatement("select s.subject_name, e.exam_name, DATE_FORMAT(es.date,'%d/%m/%Y'), e.total_questions, e.total_marks, r.obtain_marks, r.percentage, r.status from result r inner join examschedule es on r.es_id = es.es_id inner join exam e on e.exam_id = es.exam_id inner join subject s on s.subject_id = es.subject_id where r.student_id = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			List<Object[]> al = new ArrayList<>();
			while(rs.next())
			{
				al.add(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),rs.getFloat(7), rs.getInt(8) == 1 ? "Pass":"Fail"});
			}
			return Optional.of(al);
		}catch(SQLException ex)
		{
			System.out.println("Problem to get result: "+ex);
		}
		return Optional.empty();
	}


}
