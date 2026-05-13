package com.TestSync.Model;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionModel {
	private int questionId;
	private int examId;
	private String questionText;
	private String Op1;
	private String Op2;
	private String Op3;
	private String Op4;
	private String correctOp;
}	
