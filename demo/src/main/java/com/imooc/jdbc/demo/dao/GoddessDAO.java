package com.imooc.jdbc.demo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.imooc.jdbc.demo.model.Goddess;
import com.imooc.jdbc.demo.util.DBUtil;

public class GoddessDAO {
	
	public List<Goddess> query() throws Exception{
		List<Goddess> gs=new ArrayList<Goddess>();
		
		Connection conn=DBUtil.getConnection();
		StringBuffer sb = new StringBuffer();
		sb.append("select id,user_name,age from imooc_goddess");
		
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ResultSet rs = ptmt.executeQuery();
		
		Goddess g = null;
		while(rs.next()){
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			gs.add(g);
		}
		return gs;
	}
	
	//根据姓名 手机号 邮箱来查询
	public List<Goddess> query(String user_name, String mobile, String email) throws Exception{
		List<Goddess> gs=new ArrayList<Goddess>();
		
		Connection conn=DBUtil.getConnection();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from imooc_goddess ");
		sb.append("where user_name like ? and mobile like ? and email like ?");
		
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ptmt.setString(1, '%'+user_name+'%');
		ptmt.setString(2, '%'+mobile+'%');
		ptmt.setString(3, '%'+email+'%');
		ResultSet rs = ptmt.executeQuery();
		
		Goddess g=null;
		while(rs.next()){
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setSex(rs.getInt("sex"));
			g.setAge(rs.getInt("age"));
			g.setBirthday(rs.getDate("birthday"));
			g.setEmail(rs.getString("email"));
			g.setMobile(rs.getString("mobile"));
			g.setCreate_user(rs.getString("create_user"));
			g.setCreate_date(rs.getDate("create_date"));
			g.setUpdate_date(rs.getDate("update_date"));
			g.setUpdate_user(rs.getString("update_user"));
			g.setIsdel(rs.getInt("isdel"));
			gs.add(g);
		}
		return gs;
	}
	
	public void addGoddess(Goddess g) throws Exception{
		Connection conn = DBUtil.getConnection();
		String sql = "insert into imooc_goddess"
				+ "(user_name,sex,age,birthday,email,mobile,"
				+ "create_user,create_date,update_user,update_date,isdel)"
				+ "values(?,?,?,?,?,?,?,current_date(),?,current_date,?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, g.getUser_name());
		ptmt.setInt(2, g.getSex());
		ptmt.setInt(3, g.getAge());
		ptmt.setDate(4, new  Date(g.getBirthday().getTime()));
		ptmt.setString(5,g.getEmail());
		ptmt.setString(6, g.getMobile());
		ptmt.setString(7, g.getCreate_user());
		ptmt.setDate(8, new Date(g.getCreate_date().getTime()));
		ptmt.setString(9, g.getUpdate_user());
		ptmt.setDate(10, new Date(g.getUpdate_date().getTime()));
		ptmt.setInt(11, g.getIsdel());
		ptmt.execute();
	}
	
	public void updateGoddess(Goddess g) throws SQLException{
		Connection conn=DBUtil.getConnection();
		String sql="" +
				" update imooc_goddess " +
				" set user_name=?,sex=?,age=?,birthday=?,email=?,mobile=?, " +
				" update_user=?,update_date=current_date(),isdel=? " +
				" where id=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setString(1, g.getUser_name());
		ptmt.setInt(2, g.getSex());
		ptmt.setInt(3, g.getAge());
		ptmt.setDate(4, new Date(g.getBirthday().getTime()));
		ptmt.setString(5, g.getEmail());
		ptmt.setString(6, g.getMobile());
		ptmt.setString(7, g.getUpdate_user());
		ptmt.setInt(8, g.getIsdel());
		ptmt.setInt(9, g.getId());
		ptmt.execute();
	}
	
	public void delGoddess(Integer id) throws SQLException{
		Connection conn=DBUtil.getConnection();
		String sql="" +
				" delete from imooc_goddess " +
				" where id=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setInt(1, id);
		ptmt.execute();
	}
	
	public Goddess get(Integer id) throws SQLException{
		Goddess g=null;
		Connection conn=DBUtil.getConnection();
		String sql="" +
				" select * from imooc_goddess " +
				" where id=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setInt(1, id);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			g=new Goddess();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			g.setSex(rs.getInt("sex"));
			g.setBirthday(rs.getDate("birthday"));
			g.setEmail(rs.getString("email"));
			g.setMobile(rs.getString("mobile"));
			g.setCreate_date(rs.getDate("create_date"));
			g.setCreate_user(rs.getString("create_user"));
			g.setUpdate_date(rs.getDate("update_date"));
			g.setUpdate_user(rs.getString("update_user"));
			g.setIsdel(rs.getInt("isdel"));
		}
		return g;
	}
}
