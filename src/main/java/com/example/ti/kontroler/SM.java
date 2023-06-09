package com.example.ti.kontroler;

import com.example.ti.model.Narzedzia;
import com.example.ti.model.SMuzytkownik;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SM", value = "/SM")
public class SM extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Integer wartosc = 0;
        Cookie[] ciastka = request.getCookies();
        Cookie licznikCiastko = new Cookie("licznik", "0");
        for (Cookie Ciastko : ciastka) {
            if (Ciastko.getName().equals("licznik"))
                licznikCiastko = Ciastko;
        }
        try {
            wartosc = Integer.parseInt(licznikCiastko.getValue());
        } catch (NumberFormatException e) {
            wartosc = 0;
        }
        wartosc++;

        Cookie licznik = new Cookie("licznik", wartosc.toString());
        licznik.setMaxAge(60 * 60 * 24);
        response.addCookie(licznik);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        ServletContext context = getServletContext();
        PrintWriter out = response.getWriter();

        HttpSession sesja = request.getSession();

        String atrybut1 = (String) sesja.getAttribute("atrybut1");
        Integer atrybut2 = (Integer) sesja.getAttribute("atrybut2");

        if (atrybut1 == null)
            atrybut1 = "";

        if (atrybut2 == null)
            atrybut2 = 0;

        SMuzytkownik uzytkownik = (SMuzytkownik) sesja.getAttribute("uzytkownik");
        if (uzytkownik == null) {
            uzytkownik = new SMuzytkownik();
            sesja.setAttribute("uzytkownik", uzytkownik);
        }

        uzytkownik.setLogin("user");
        uzytkownik.setUprawnienia(1);

        String strona = request.getParameter("strona");
        if (uzytkownik.getUprawnienia() > 0)
            strona = Narzedzia.parsujStrone(strona, "glowna;kwadratowe;trzecia;ustawienia");
        else
            strona = Narzedzia.parsujStrone(strona, "glowna;kwadratowe;trzecia");

        String szablon = Narzedzia.pobierzSzablon("index.html", context);

        szablon = Narzedzia.skrypty(szablon, "skrypt;kwadratowe");
        szablon = Narzedzia.funkcje(szablon, "podpiecie");

        szablon = Narzedzia.uzupelnij(szablon, "NAGLOWEK", "naglowek.html", context);
        szablon = Narzedzia.uzupelnij(szablon, "MENU", "menu.html", context);
        szablon = Narzedzia.uzupelnij(szablon, "TRESC", strona + ".html", context);
        szablon = Narzedzia.uzupelnij(szablon, "STOPKA", "stopka.html", context);

        out.println("licznik: " + wartosc);
        out.println(uzytkownik);
        out.println(szablon);
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
    }
}
