package com.example.controller;

import com.example.dao.LineDao;
import com.example.dao.PlaneDao;
import com.example.model.Line;
import com.example.model.Menu;
import com.example.model.Plane;
import com.example.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {

    @Autowired
    PlaneDao planeDao;
    @Autowired
    LineDao lineDao;

    @RequestMapping("/menuList")
    public String list(Model model) {
        List<Menu> menus = new ArrayList<>();
        Line line = new Line();
        for (Plane plane : planeDao.findAll()) {
            Menu menu = new Menu();
            for (Line line1 : lineDao.findAll()) {
                if ((plane.getLineid()).equals(line1.getLineid())) {
                    line = line1;
                    break;
                }
            }
            menu.setLineId(line.getLineid());
            menu.setPlaneId(plane.getPlaneid());
            menu.setBeginPlace(line.getBeginplace());
            menu.setEndPlace(line.getEndplace());
            menu.setCompany(plane.getCompany());
            menu.setSeat(plane.getSeat());
            menu.setBeginTime(plane.getBegintime());
            menu.setArriveTime(plane.getArrivetime());
            menu.setPrice(plane.getPrice());
            menus.add(menu);
        }
        model.addAttribute("Menus", menus);
        return "/menu/m-list";
    }

    @RequestMapping("/searchMenu")
    public String search(HttpServletRequest request, Model model) {
        List<Menu> menus = new ArrayList<>();
        for (Line line1 : lineDao.findAll()) {
            if (line1.getBeginplace().equals(request.getParameter("beginPlace")) && line1.getEndplace().equals(request.getParameter("endPlace"))) {
                for (Plane plane : planeDao.findAll()) {
                    Menu menu = new Menu();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String date = dateFormat.format(plane.getBegintime());
                    if ((plane.getLineid()).equals(line1.getLineid()) && date.equals(request.getParameter("date"))) {
                        menu.setLineId(line1.getLineid());
                        menu.setPlaneId(plane.getPlaneid());
                        menu.setBeginPlace(line1.getBeginplace());
                        menu.setEndPlace(line1.getEndplace());
                        menu.setCompany(plane.getCompany());
                        menu.setSeat(plane.getSeat());
                        menu.setBeginTime(plane.getBegintime());
                        menu.setArriveTime(plane.getArrivetime());
                        menu.setPrice(plane.getPrice());
                        menus.add(menu);
                    }

                }
                break;
            }

        }
        model.addAttribute("Menus", menus);
        return "/menu/m-list";
    }

}
