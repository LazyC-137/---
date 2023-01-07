package com.example.controller;

import com.example.dao.LineDao;
import com.example.model.Line;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LineController {

    @Autowired
    LineDao lineDao;

    @RequestMapping("/listLine")
    public String list(Model model){
        model.addAttribute("lines",lineDao.findAll());
        return "/line/a-list";
    }

    @GetMapping("/toAddLine")
    public String toAdd(){
        return "/line/add";
    }

    @PostMapping("/addLine")
    public String add(HttpServletRequest request){
        Line line = new Line();
        List<Line> lines = lineDao.findAll();
        for(Line line1 : lines){
            if(line1.getLineid().equals(request.getParameter("lineId"))){
                return "redirect:/listLine";
            }
        }
        line.setLineid(request.getParameter("lineId"));
        line.setBeginplace(request.getParameter("beginPlace"));
        line.setEndplace(request.getParameter("endPlace"));
        if(line.getLineid()!="" || line.getBeginplace()!="" || line.getEndplace()!=""){
            lineDao.save(line);
        }
        return "redirect:/listLine";
    }

    @GetMapping("/toUpdateLine/{id}")
    public String toUpdate(@PathVariable("id")String id, Model model){
        List<Line> lines = lineDao.findAll();
        for(Line line1 : lines){
            if(line1.getLineid().equals(id)){
                model.addAttribute("line",line1);
            }
        }
        return "line/update";
    }

    @PostMapping("/updateLine/{id}")
    public String update(@PathVariable("id")String id, HttpServletRequest request){
        Line line = new Line();
        line.setLineid(id);
        line.setBeginplace(request.getParameter("beginPlace"));
        line.setEndplace(request.getParameter("endPlace"));
        lineDao.save(line);
        return "redirect:/listLine";
    }

    @GetMapping("/deleteLine/{id}")
    public String delete(@PathVariable("id")String id){
        lineDao.deleteById(id);
        return "redirect:/listLine";
    }
}
