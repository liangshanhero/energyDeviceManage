package cn.edu.scau.cmi.pengjie.controller;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.scau.cmi.dao.StaffDAO;
import cn.edu.scau.cmi.domain.Staff;
import cn.edu.scau.cmi.domain.Whdevice;
import cn.edu.scau.cmi.service.StaffService;
import cn.edu.scau.cmi.service.WhdeviceService;

@Controller("LoginController")
public class LoginController {

	@Autowired
	StaffDAO staffDAO;
	@Autowired
	StaffService Staffservice;

	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		req.getSession().removeAttribute("user");
		req.getSession().removeAttribute("functions");
		return "redirect:/login.jsp";
	}

	/**
	 * 响应登录请求，验证用户的身份,并返回用户的功能集合
	 * 
	 * **/
	@SuppressWarnings("null")
//	@RequestMapping(value = "/login")
	public String login(HttpServletRequest req, HttpServletResponse res) {
		// 获取登陆的用户名
		System.out.println("到达这");
		String name = req.getParameter("username");
		System.out.println("名称：" + name);
		// 获取登陆是的密码
		String password = req.getParameter("password");
		System.out.println("password:" + password);
		Staff staff = new Staff();
		//
		if (!checkUser(name, password)) {
			req.getSession().setAttribute("staff", new Staff());
			req.getSession().setAttribute("loginError", "用户名或者密码不对！！！");
			return "redirect:/login.jsp";
		}
		// 通过用户的账户名查找到用户，因为账户名不许重复，因此，得到的这一个用户即为登录的用户
		Iterator<Staff> staffs = staffDAO.findStaffByLoginname(name).iterator();
		if (staffs.hasNext()) {
			staff = staffs.next();
		}
		// 将用户和功能放到session�?并返回到登陆成功页面,这个页面将被装饰成菜单
		req.getSession().setAttribute("staff", staff);

		return "redirect:/index.jsp";
	}

	/**
	 * 将获得的用户登录信息与数据库中的信息进行比对
	 * **/
	
	public boolean checkUser(String loginName, String password) {

		// 该方法使用的前提是用户名是唯一的！！！！
		System.out.println("查找账号");

		Iterator<Staff> Staffs = staffDAO.findStaffByLoginname(loginName)
				.iterator();

		Staff staff = new Staff();

		if (Staffs.hasNext()) {
			staff = Staffs.next();
			if (staff.getPassword().equals(password)) {
				System.out.println("找到用户");
				return true;
			} else {
				System.out.println("密码不正确");
				return false;
			}
		} else {
			System.out.println("未找到用户");
			return false;
		}
	}
}