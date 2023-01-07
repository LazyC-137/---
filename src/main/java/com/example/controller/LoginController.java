package com.example.controller;

import com.example.dao.CustomerDao;
import com.example.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Arrays;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    CustomerDao customerDao;

    @RequestMapping("/toRegister")
    public String toRegister(HttpSession session){
        String username = "default";
        session.setAttribute("loginUser", username);
        return "/register";
    }

    @RequestMapping("/register")
    public String register(@RequestParam("id")String id,
                           @RequestParam("password")String password,
                           @RequestParam("name")String name,
                           @RequestParam("phone")String phone,
                           @RequestParam("sfz")String sfz){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setPsw(password);
        customer.setName(name);
        customer.setPhone(phone);
        customer.setId_card(sfz);
        customerDao.save(customer);
        return "redirect:/index.html";
    }

    @RequestMapping("/user/login")
    //@ResponseBody
    public String login(@RequestParam("id")String id,
                        @RequestParam("password")String password,
                        Model model, HttpSession session){
        List<Customer> customers = customerDao.findAll();
        //管理员登录
        if ("admin".equals(id) && "admin".equals(password)) {
            session.setAttribute("loginUser", id);
            return "redirect:/a-main.html";
        }
        //客户登录
        for(Customer customer : customers) {
            if (customer.getId().equals(id) && customer.getPsw().equals(password)) {
                session.setAttribute("loginUser", id);
                return "redirect:/c-main.html";
            }
        }
            //告知用户登录失败
            model.addAttribute("msg","用户名或密码错误!");
            return "index";
//        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
//            session.setAttribute("loginUser",username);
//            return "redirect:/c-main.html";
//        }else {
//            //告知用户登录失败
//            model.addAttribute("msg","用户名或密码错误!");
//            return "index";
//        }
    }

    @RequestMapping("/user/login_face")
    public String faceRecognizer(Model model, HttpSession session){
        try {
            String[] args = new String[] { "python", "D:\\Work\\PyCharm Community Edition 2022.3.1\\pythonProject\\training\\10.人脸识别.py"};
            Process proc = Runtime.getRuntime().exec(args);// 执行py文件
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while( ( line = in.readLine() ) != null ) {
                for (Customer customer : customerDao.findAll()){
                    if(customer.getId().equals(line)){
                        System.out.println("11111111111");
                        proc.destroy();
                        session.setAttribute("loginUser", customer.getId());
                        return "redirect:/c-main.html";
                    }
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //告知用户登录失败
        model.addAttribute("msg","用户名或密码错误!");
        return "redirect:/index";
    }

    @RequestMapping("/faceIn")
    public String faceIn(Model model, HttpSession session){
        String loginUser = (String) session.getAttribute("loginUser");
        try {
            String[] args = new String[] { "python", "D:\\Work\\PyCharm Community Edition 2022.3.1\\pythonProject\\training\\8.人脸录入.py",loginUser};
            String[] args1 = new String[] { "python", "D:\\Work\\PyCharm Community Edition 2022.3.1\\pythonProject\\training\\9.训练数据.py"};
            Process proc = Runtime.getRuntime().exec(args);// 执行py文件
            //Runtime.getRuntime().exec(args1);// 执行py文件
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while( ( line = in.readLine() ) != null ) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //告知用户登录失败
        model.addAttribute("msg","用户名或密码错误!");
        return "redirect:/index";
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }
}
