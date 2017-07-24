package com.imooc.jdbc.demo.action;

import java.sql.SQLException;
import java.util.List;

import com.imooc.jdbc.demo.dao.GoddessDAO;
import com.imooc.jdbc.demo.model.Goddess;

public class GoddessAction {
	public void add(Goddess goddess) throws Exception{
		GoddessDAO dao=new GoddessDAO();
		goddess.setCreate_user("ADMIN");
		goddess.setUpdate_user("ADMIN");
		goddess.setIsdel(0);
		dao.addGoddess(goddess);
	}
	
	public Goddess get(Integer id) throws SQLException{
		GoddessDAO dao=new GoddessDAO();
		return dao.get(id);
	}
	
	public void edit(Goddess goddess) throws Exception{
		GoddessDAO dao=new GoddessDAO();
		dao.updateGoddess(goddess);
	}
	public void del(Integer id) throws SQLException{
		GoddessDAO dao=new GoddessDAO();
		dao.delGoddess(id);
	}
	
	public List<Goddess>  query() throws Exception{
		GoddessDAO dao=new GoddessDAO();
		return dao.query();
	}
}
