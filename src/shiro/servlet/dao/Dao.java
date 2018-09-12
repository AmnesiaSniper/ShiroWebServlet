package shiro.servlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class Dao {
	// 获取密码
	public static String getPassword(String username) {

		String sql = "select password from user where name = ?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shiro?characterEncoding=UTF-8",
					"root", "root");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("password");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// 获取角色
	public static Set<String> getRoles(String username) {
		Set<String> list = new HashSet<String>();
		String sql = "select r.name from user u " + "left join user_role ur on u.id = ur.uid "
				+ "left join Role r on r.id = ur.rid " + "where u.name = ?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shiro?characterEncoding=UTF-8",
					"root", "root");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("name"));
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
//获取权限
	public static Set<String> getPermissions(String username) {
		Set<String> list = new HashSet<String>();
		String sql = "select p.name from user u "+
	            "left join user_role ru on u.id = ru.uid "+
	            "left join role r on r.id = ru.rid "+
	            "left join role_permission rp on r.id = rp.rid "+
	            "left join permission p on p.id = rp.pid "+
	            "where u.name =?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shiro?characterEncoding=UTF-8",
					"root", "root");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("name"));			
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(getPassword("zhang3"));
		System.out.println(getRoles("zhang3"));
		System.out.println(getPermissions("zhang3"));
	}
}
