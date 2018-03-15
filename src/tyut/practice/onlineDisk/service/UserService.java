package tyut.practice.onlineDisk.service;

import javax.servlet.RequestDispatcher;

import tyut.practice.onlineDisk.dao.UserDao;

public class UserService {
	private UserDao dao = new UserDao();
	/**
	 * 根据传入的用户名(name)和密码(password)校验用户是否合法
	 * @param  String name，String password
	 * @return boolean
	 */
	public boolean verify(String name, String password){
		String pwd = dao.getPassWordByName(name);
		if(pwd == null){
			return false;
		}
		if(pwd.equals(password)){
			return true;
		}
		return false;
	}
	public String getNameByEmail(String email){
		String name=dao.getNameByEmail(email);
		return name;
	}
	/**
	 * 根据传入的用户名(name),密码(password),邮箱(email)进行注册
	 * @param  String name,String password,String email
	 * @return boolean
	 */
	public boolean register(String name, String password, String email){
		if(dao.getPassWordByName(name) != null){
			return false;
		}
		return dao.addUser(name, password, email);
	}
	/**
	 * 根据传入的用户名(name),原密码(passwordB),新密码(password)修改用户的密码
	 * @param  String name,String passwordB,String password
	 * @return boolean
	 */
	public String UpdatePsd(String name, String passwordB, String password)
	{
		System.out.println(dao.getPassWordByName(name));
		if(!dao.getPassWordByName(name).equals(passwordB)){
			return "原密码错误";
		}
		if(dao.updatePasswordByName(name, password)==true)
			return "success";
		else 
			{
				return "fail";
			}
	}
}
