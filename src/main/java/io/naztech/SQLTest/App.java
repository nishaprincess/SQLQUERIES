package io.naztech.SQLTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver").newInstance();
		Connection con = DriverManager.getConnection("jdbc:h2:" + "./Database/my", "root", "mp");

		String sql = "CREATE TABLE IF NOT EXISTS Customer (\n" + "	id integer PRIMARY KEY,\n"
				+ "	name text NOT NULL,\n" + "	salary real\n" + ");";
		Statement stmt = con.createStatement();
		String sql3 = "CREATE TABLE IF NOT EXISTS Person (\n" + "	pid integer PRIMARY KEY,\n"
				+ "	ppname text NOT NULL,\n" + "	psalary real\n" + ");";
		stmt.execute(sql3);
		stmt.execute(sql);
		String sql22 = "Delete from Customer";
		stmt.execute(sql22);
		String ins1 = "INSERT INTO Customer(id,name,salary) VALUES(1,'nisha',30000)";
		stmt.execute(ins1);
		String ins2 = "INSERT INTO Customer(id,name,salary) VALUES(2,'sumaiya',40000)";
		stmt.execute(ins2);
		String ins3 = "INSERT INTO Customer(id,name,salary) VALUES(3,'kona',4000)";
		stmt.execute(ins3);
		String ins4 = "INSERT INTO Customer(id,name,salary) VALUES(4,'lipi',45400)";
		stmt.execute(ins4);
		PreparedStatement statement = con.prepareStatement("SELECT * FROM Customer");
		Statement stmt2 = con.createStatement();
		String query = "select * from Customer ;";

		ResultSet rs = stmt2.executeQuery(query);
		while (rs.next()) {
			String id = rs.getObject(1).toString();
			String name = rs.getObject(2).toString();
			System.out.println("id " + id + " name is" + name);

		}
		PreparedStatement statement2 = con.prepareStatement("SELECT MAX(salary) FROM Customer");
		Statement stmt3 = con.createStatement();
		String query3 = "SELECT * FROM Customer WHERE salary=(SELECT MAX(salary) FROM Customer) ;";

		ResultSet rs2 = stmt3.executeQuery(query3);
		while (rs2.next()) {
			String id = rs2.getString(1);
			String name = rs2.getString(2);
			String sal = rs2.getString(3);

			System.out.println("id" + id + " name" + name + " sal " + sal);
		}

		String query4 = "SELECT * FROM Customer WHERE salary=(SELECT MAX(salary) FROM Customer WHERE salary < (SELECT MAX(salary) FROM Customer));";

		ResultSet rs3 = stmt3.executeQuery(query4);
		while (rs3.next()) {
			String id = rs3.getString(1);
			String name = rs3.getString(2);
			String sal = rs3.getString(3);

			System.out.println("id" + id + " name" + name + " sal " + sal);
		}

		String query5 = "SELECT AVG(salary) FROM Customer;";

		ResultSet rs4 = stmt3.executeQuery(query5);
		while (rs4.next()) {
			String sal = rs4.getString(1);

			System.out.println("sal " + sal);
		}
		String query6 = "SELECT * FROM Customer WHERE salary=(SELECT MIN(salary) FROM Customer);";

		ResultSet rs5 = stmt3.executeQuery(query6);
		while (rs5.next()) {
			String id = rs5.getString(1);
			String name = rs5.getString(2);
			String sal = rs5.getString(3);

			System.out.println("id" + id + " name " + name + " sal " + sal);
		}

		String query7 = "SELECT TOP 3 * FROM Customer ORDER BY salary desc";

		ResultSet rs6 = stmt3.executeQuery(query7);
		while (rs6.next()) {
			String id = rs6.getString(1);
			String name = rs6.getString(2);
			String sal = rs6.getString(3);

			System.out.println("id" + id + " name " + name + " sal " + sal);
		}

	}
}
