package tyut.practice.onlineDisk.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
//所有数据库访问类的基类
public class Dao {
	//保存与数据库访问的相关操作
	public static String url;  
    public static String user;  
    public static String password;  
    public static String driver; 
    
    public Dao(){
    	String f = this.getClass().getClassLoader().getResource("").getPath() + "db.properties";
    	InputStream in = null;
		try {
			in = new FileInputStream(f);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		Properties prop = new Properties();
		try {
			prop.load(in);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		user = prop.getProperty("user");
		password = prop.getProperty("password");
		
    }
}
