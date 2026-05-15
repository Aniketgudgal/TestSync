package com.TestSync.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultModel {
	private int id;
	private int esId;
	private int studentId;
	private int obtainMarks;
	private float percentage;
	private boolean isStatus;
}
