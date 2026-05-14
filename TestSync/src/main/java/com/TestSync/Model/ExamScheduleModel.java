package com.TestSync.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamScheduleModel {
	private int id;
	private int examId;
	private int subjectId;
	private boolean isAttempt;
	private String startTime;
	private String endTime;
	private String date;
	private int courseId;
}
