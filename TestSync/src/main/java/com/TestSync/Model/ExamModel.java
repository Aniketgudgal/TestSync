package com.TestSync.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamModel {
private int id;
private String examName;
private int subjectId;
private int totalQuestions;
private int totalMarks;
private int examDuration;
}
