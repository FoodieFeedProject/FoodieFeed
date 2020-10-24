package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mie.model.Student;
import com.mie.util.DbUtil;

public class StudentDao {
	/**
	 * This class handles all of the Student-related methods
	 * (add/update/delete/get).
	 */

	private Connection connection;

	public StudentDao() {
		/**
		 * Get the database connection.
		 */
		connection = DbUtil.getConnection();
	}

	public void addStudent(Student student) {
		/**
		 * This method adds a new student to the database.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into students(firstname,lastname,dob,email) values (?, ?, ?, ? )");
			// Parameters start with 1
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.setDate(3, new java.sql.Date(student.getDob()
					.getTime()));
			preparedStatement.setString(4, student.getEmail());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteStudent(int studentid) {
		/**
		 * This method deletes a student from the database.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from students where studentid=?");
			// Parameters start with 1
			preparedStatement.setInt(1, studentid);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateStudent(Student student) {
		/**
		 * This method updates a student's information into the database.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update students set firstname=?, lastname=?, dob=?, email=?"
							+ " where studentid=?");
			// Parameters start with 1
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.setDate(3, new java.sql.Date(student.getDob()
					.getTime()));
			preparedStatement.setString(4, student.getEmail());
			preparedStatement.setInt(5, student.getStudentid());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Student> getAllStudents() {
		/**
		 * This method returns the list of all students in the form of a List
		 * object.
		 */
		List<Student> students = new ArrayList<Student>();
		try {
			Statement statement = connection.createStatement();
			// System.out.println("getting students from table");
			ResultSet rs = statement.executeQuery("select * from students");
			while (rs.next()) {
				Student student = new Student();
				student.setStudentid(rs.getInt("studentid"));
				student.setFirstName(rs.getString("firstname"));
				student.setLastName(rs.getString("lastname"));
				student.setDob(rs.getDate("dob"));
				student.setEmail(rs.getString("email"));
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

	public Student getStudentById(int studentid) {
		/**
		 * This method retrieves a student by their StudentID number.
		 * 
		 * Currently not used in the sample web app, but code is left here for
		 * your review.
		 */
		Student student = new Student();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from students where studentid=?");
			preparedStatement.setInt(1, studentid);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				student.setStudentid(rs.getInt("studentid"));
				student.setFirstName(rs.getString("firstname"));
				student.setLastName(rs.getString("lastname"));
				student.setDob(rs.getDate("dob"));
				student.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	public List<Student> getStudentByKeyword(String keyword) {
		/**
		 * This method retrieves a list of students that matches the keyword
		 * entered by the user.
		 */
		List<Student> students = new ArrayList<Student>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from students where firstname LIKE ? OR lastname LIKE ? OR email LIKE ? OR dob LIKE ?");

			preparedStatement.setString(1, "%" + keyword + "%");
			preparedStatement.setString(2, "%" + keyword + "%");
			preparedStatement.setString(3, "%" + keyword + "%");
			preparedStatement.setString(4, "%" + keyword + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setStudentid(rs.getInt("studentid"));
				student.setFirstName(rs.getString("firstname"));
				student.setLastName(rs.getString("lastname"));
				student.setDob(rs.getDate("dob"));
				student.setEmail(rs.getString("email"));
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

}