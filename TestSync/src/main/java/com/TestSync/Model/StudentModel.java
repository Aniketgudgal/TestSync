package com.TestSync.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {
	private int id;
	private String name;
	private String email;
	private String userName;
	private String password;
	private int courseId;
	private String mobile;
}
