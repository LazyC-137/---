package com.example.controller;

import com.example.dao.LineDao;
import com.example.dao.PlaneDao;
import com.example.model.Line;
import com.example.model.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PlaneController {

    @Autowired
    PlaneDao planeDao;
    @Autowired
    LineDao lineDao;

    @RequestMapping("/listPlane")
    public String list(Model model){
        model.addAttribute("planes",planeDao.findAll());
        return "/plane/p-list";
    }

    @GetMapping("/toAddPlane")
    public String toAdd(Model model){
        List<Line> showLines = new ArrayList<>();
        showLines = lineDao.findAll();
        model.addAttribute("showLines",showLines);
        return "/plane/add";
    }


    @PostMapping("/addPlane")
    public String add(HttpServletRequest request) throws ParseException {
        Plane plane = new Plane();
        List<Plane> planes = planeDao.findAll();
        for(Plane plane1 : planes){
            if(plane1.getPlaneid().equals(request.getParameter("lineId"))){
                return "redirect:/listPlane";
            }
        }
        plane.setPlaneid(request.getParameter("planeId"));
        plane.setLineid(request.getParameter("lineid"));
        plane.setCompany(request.getParameter("company"));
        plane.setSeat(Integer.parseInt(request.getParameter("seat")));
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        plane.setBegintime(format.parse(request.getParameter("begintime").replace("T"," ")));
        plane.setArrivetime(format.parse(request.getParameter("arrivetime").replace("T"," ")));
        plane.setPrice(request.getParameter("price"));
        if (plane.getPlaneid()!="" || plane.getLineid()!="" || plane.getCompany()!=""){
            planeDao.save(plane);
        }
        return "redirect:/listPlane";
    }

    @GetMapping("/toUpdatePlane/{id}")
    public String toUpdate(@PathVariable("id")String id, Model model){
        List<Plane> planes = planeDao.findAll();
        for(Plane plane : planes){
            if(plane.getPlaneid().equals(id)){
                model.addAttribute("plane",plane);
            }
        }
        return "plane/update";
    }

    @PostMapping("/updatePlane/{id}")
    public String update(@PathVariable("id")String id, HttpServletRequest request) throws ParseException {
        Plane plane = new Plane();
        plane.setPlaneid(id);
        plane.setLineid(request.getParameter("lineid"));
        plane.setCompany(request.getParameter("company"));
        plane.setSeat(Integer.parseInt(request.getParameter("seat")));
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        plane.setBegintime(format.parse(request.getParameter("begintime").replace("T"," ")));
        plane.setArrivetime(format.parse(request.getParameter("arrivetime").replace("T"," ")));
        plane.setPrice(request.getParameter("price"));
        if (plane.getPlaneid()!="" || plane.getLineid()!="" || plane.getCompany()!=""){
            planeDao.save(plane);
        }
        return "redirect:/listPlane";
    }

    @GetMapping("/deletePlane/{id}")
    public String delete(@PathVariable("id")String id){
        planeDao.deleteById(id);
        return "redirect:/listPlane";
    }
}
