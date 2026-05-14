package com.TestSync.Repositry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.TestSync.Model.StudentModel;

public class StudentRepoImp extends DBConfig implements StudentRepo {

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
						rs.getInt(9) == 0 ? "Pending" : "Completed", rs.getInt(10) });
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
					"select c.course_name, e.exam_name, sub.subject_name, TIME_FORMAT(es.start_time, '%h:%i %p'), TIME_FORMAT(es.end_time, '%h:%i %p'), DATE_FORMAT(es.date, '%d/%m/%Y'), e.total_questions, e.total_marks, es.attempted, es.es_id from student s inner join course c on c.course_id = s.course_id inner join examschedule es on es.course_id = c.course_id inner join subject sub on sub.subject_id = es.subject_id inner join exam e on e.exam_id = es.exam_id where es.attempted = 1 AND s.student_id = ?");
			pst.setInt(1, id);
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

}
