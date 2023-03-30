package com.example.ti.model;

import jakarta.servlet.ServletContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Tools {

    public static String downloadPattern(String file, ServletContext context) throws IOException
    {
        StringBuffer out = new StringBuffer("");

        String text = "";
        InputStream is = context.getResourceAsStream("/WEB-INF/widok/"+file);
        if(is != null)
        {
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            while((text = reader.readLine()) != null){
                out.append(text).append("\n");
            }
        }
        else out.append("Brak pliku "+file);
        return out.toString();
    }
    public static String fill(String pattern, String marker,
                              String file, ServletContext context) throws  IOException{
        StringBuffer out = new StringBuffer("");
        String text = "";
        InputStream is = context.getResourceAsStream("/WEB-INF/widok/"+file);
        if(is != null)
        {
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            while((text = reader.readLine()) != null){
                out.append(text).append("\n");
            }
        }
        else out.append("Brak pliku "+file);

        return pattern.replace("[["+marker+"]]", out.toString());
    }

}
