package com.example.ti.controller;

import com.example.ti.model.Tools;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SM", value=("/SM"))
public class SM extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        ServletContext context = getServletContext();
        PrintWriter out = response.getWriter();

        String strona = request.getParameter("strona");

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
