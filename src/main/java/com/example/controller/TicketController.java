package com.example.controller;

import com.example.dao.*;
import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class TicketController {

    @Autowired
    LineDao lineDao;
    @Autowired
    PlaneDao planeDao;
    @Autowired
    TicketOrderDao ticketOrderDao;
    @Autowired
    CustomerDao customerDao;

    @RequestMapping("/listTicket")
    public String orderList(HttpServletRequest request, Model model){
        List<Ticket> tickets = new ArrayList<>();
        Object loginUser = request.getSession().getAttribute("loginUser");
        Customer customer = new Customer();
        for (Customer customer1 : customerDao.findAll()){
            if(customer1.getId().equals(loginUser)){
                customer = customer1;
            }
        }
        for(TicketOrder ticketOrder : ticketOrderDao.findAll()) {
            Ticket ticket = new Ticket();
            if (ticketOrder.getId().equals(customer.getId())) {
                ticket.setTicketId(ticketOrder.getOrderid());
                ticket.setId(customer.getId());
                ticket.setLineId(ticketOrder.getLineid());
                ticket.setPlaneId(ticketOrder.getPlaneid());
                //showDindan.setTime(planeDao.getOne(dindan.getPlaneid()).getTime());
                ticket.setBeginTime(planeDao.getOne(ticketOrder.getPlaneid()).getBegintime());
                ticket.setArriveTime(planeDao.getOne(ticketOrder.getPlaneid()).getArrivetime());
                ticket.setPrice(planeDao.getOne(ticketOrder.getPlaneid()).getPrice());
                ticket.setBeginPlace(lineDao.getOne(ticketOrder.getLineid()).getBeginplace());
                ticket.setEndPlace(lineDao.getOne(ticketOrder.getLineid()).getEndplace());
                tickets.add(ticket);
            }
        }
        model.addAttribute("tickets", tickets);
        return "/ticket/t-list";
    }

    @GetMapping("/schedule/{id}")
    public String schedule(@PathVariable("id")String id,HttpServletRequest request){
        Object loginUser = request.getSession().getAttribute("loginUser");
        TicketOrder ticketOrder = new TicketOrder();
        Random random=new Random();
        ticketOrder.setOrderid(String.valueOf((int)(random.nextDouble()*(99999-10000 + 1))+ 10000));
        ticketOrder.setId((String) loginUser);
        ticketOrder.setLineid(planeDao.getOne(id).getLineid());
        ticketOrder.setPlaneid(id);
        planeDao.getOne(id).setSeat(planeDao.getOne(id).getSeat()-1);
        ticketOrderDao.save(ticketOrder);
        return "redirect:/menuList";
    }

    @GetMapping("/deleteTicket/{id}")
    public String delete(@PathVariable("id")String id){
        planeDao.getOne(ticketOrderDao.getOne(id).getPlaneid()).setSeat(planeDao.getOne(ticketOrderDao.getOne(id).getPlaneid()).getSeat()+1);
        ticketOrderDao.deleteById(id);
        return "redirect:/listTicket";
    }
}
