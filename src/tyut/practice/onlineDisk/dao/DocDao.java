package tyut.practice.onlineDisk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tyut.practice.onlineDisk.model.Doc;

public class DocDao extends Dao{

	public boolean addDoc(Doc doc){
		StringBuilder sql = new StringBuilder();
		sql.append("insert into doc(name, owner, createtime, size, directory, pid, isdir, realdirectory) values('");
		sql.append(doc.getName()).append("', '").append(doc.getOwner()).append("', '");
		sql.append(doc.getCreatetime()).append("', ").append(doc.getSize()).append(", '");
		sql.append(doc.getDirectory()).append("', ").append(doc.getPid()).append(", ");
		sql.append(doc.getIsdir()).append(", '").append(doc.getRealdirectory()).append("')");
		
		Connection conn = null;
		Statement stat = null;
		try {  
            Class.forName(driver);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            stat = conn.createStatement();
            stat.execute(sql.toString());
            return true;
            
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally{
        	try {
        		stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return false;
	}
	
	public boolean deleteDocByDir(String owner, String dir){
		String sql = "delete from doc where owner= '" + owner + "' and directory = '" + dir + "'";
		
		Connection conn = null;
		Statement stat = null;
		try {  
            Class.forName(driver);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            stat = conn.createStatement();
            stat.execute(sql);
            return true;
            
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally{
        	try {
        		stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return false;
	}
	
	public List<Doc> getAllDocsUnderDir(String owner, String dir){
		List<Doc> list = new ArrayList();
		String sql = "select * from doc where owner = '" + owner + "' and directory like '" + dir + "%'";
		
		Connection conn = null;
		Statement stat = null;
		try {  
            Class.forName(driver);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
            	Doc doc = new Doc();
            	doc.setId(rs.getInt("id"));
            	doc.setCreatetime(rs.getString("createtime"));
            	doc.setDirectory(rs.getString("directory"));
            	doc.setIsdir(rs.getInt("isdir"));
            	doc.setName(rs.getString("name"));
            	doc.setOwner(rs.getString("owner"));
            	doc.setPid(rs.getInt("pid"));
            	doc.setRealdirectory(rs.getString("realdirectory"));
            	doc.setSize(rs.getLong("size"));
            	list.add(doc);
            }
            
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally{
        	try {
        		stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return list;
	}
	
	public List<Doc> getDocUnderDir(String owner, String dir){
		List<Doc> list = new ArrayList();
		
		String sql = "select * from doc where owner = '" + owner + "' and pid = (select id from doc where directory = '" + dir + "')";
		if(dir.equals("")){
			sql = "select * from doc where owner = '" + owner + "' and pid = -1";
		}
		
		Connection conn = null;
		Statement stat = null;
		try {  
            Class.forName(driver);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
            	Doc doc = new Doc();
            	doc.setId(rs.getInt("id"));
            	doc.setCreatetime(rs.getString("createtime"));
            	doc.setDirectory(rs.getString("directory"));
            	doc.setIsdir(rs.getInt("isdir"));
            	doc.setName(rs.getString("name"));
            	doc.setOwner(rs.getString("owner"));
            	doc.setPid(rs.getInt("pid"));
            	doc.setRealdirectory(rs.getString("realdirectory"));
            	doc.setSize(rs.getLong("size"));
            	list.add(doc);
            }
            
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally{
        	try {
        		stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return list;
	}
	
	public Doc getDocById(String owner, int id){
		String sql = "select * from doc where owner = '" + owner + "' and id = " + id;
		
		Connection conn = null;
		Statement stat = null;
		try {  
            Class.forName(driver);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if(rs.next()){
            	Doc doc = new Doc();
            	doc.setId(rs.getInt("id"));
            	doc.setCreatetime(rs.getString("createtime"));
            	doc.setDirectory(rs.getString("directory"));
            	doc.setIsdir(rs.getInt("isdir"));
            	doc.setName(rs.getString("name"));
            	doc.setOwner(rs.getString("owner"));
            	doc.setPid(rs.getInt("pid"));
            	doc.setRealdirectory(rs.getString("realdirectory"));
            	doc.setSize(rs.getLong("size"));
            	return doc;
            }
            
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally{
        	try {
        		stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return null;
	}
	
	public Doc getDocByOwnerAndDirc(String owner, String dir){
		String sql = "select * from doc where owner = '" + owner + "' and directory = '" + dir + "'";
		
		Connection conn = null;
		Statement stat = null;
		try {  
            Class.forName(driver);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if(rs.next()){
            	Doc doc = new Doc();
            	doc.setId(rs.getInt("id"));
            	doc.setCreatetime(rs.getString("createtime"));
            	doc.setDirectory(rs.getString("directory"));
            	doc.setIsdir(rs.getInt("isdir"));
            	doc.setName(rs.getString("name"));
            	doc.setOwner(rs.getString("owner"));
            	doc.setPid(rs.getInt("pid"));
            	doc.setRealdirectory(rs.getString("realdirectory"));
            	doc.setSize(rs.getLong("size"));
            	return doc;
            }
            
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally{
        	try {
        		stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return null;
	}
}
