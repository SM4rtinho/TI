package com.example.ti.controller;

import com.example.ti.model.SMuser;
import com.example.ti.model.Tools;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

@WebServlet(name = "SM", value=("/SM"))
public class SM extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        ServletContext context = getServletContext();
        PrintWriter out = response.getWriter();


        Integer value = 0;
        Cookie[] cookies = request.getCookies();
        Cookie counterCookie = new Cookie("licznik","0");
        for (Cookie cookie: cookies) {
            if(cookie.getName().equals("licznik"))
                counterCookie = cookie;
        }
        try{
            value = Integer.parseInt(counterCookie.getValue());
        } catch (NumberFormatException e){
            value = 0;
        }
        value ++;

        Cookie counter = new Cookie("licznik",value.toString());
        counter.setMaxAge(60*60*24);
        response.addCookie(counter);
        out.println("licznik: " + value);

        HttpSession session = request.getSession();

        String attribute1 = (String) session.getAttribute("atrybut1");
        Integer attribute2 = (Integer) session.getAttribute("atrybut2");

        if (attribute1 == null)
            attribute1 = "";

        if (attribute2 == null)
            attribute2 = 0;

        SMuser user = (SMuser) session.getAttribute("uzytkownik");
        if (user == null){
            user = new SMuser();
            session.setAttribute("uzytkownik",user);
        }
        String strona = request.getParameter("strona");
        if(user.getAuthotization() > 0)
            strona = Tools.parsujStrone(strona, "glowna;kwadratowe;trzecia,ustawienia");
        else
            strona = Tools.parsujStrone(strona, "glowna;kwadratowe;trzecia");

        strona = Tools.parsujStrone(strona, "glowna;kwadratowe;trzecia");

        user.setLogin("user");
        user.setAuthotization(0);
        out.println(user);

        String pattern = Tools.downloadPattern("index.html",context);
        pattern = Tools.fill(pattern,"NAGLOWEK","naglowek.html",context);
        pattern = Tools.fill(pattern,"MENU","menu.html",context);
        pattern = Tools.fill(pattern,"TRESC","tresc.html",context);
        pattern = Tools.fill(pattern,"STOPKA","stopka.html",context);
        out.println(pattern);
        out.close();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{


    }

}
