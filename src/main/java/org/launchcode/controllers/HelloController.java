package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.launchcode.models.Greeting;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by laura on 3/7/2017.
 */

@Controller
public class HelloController {

    @RequestMapping(value="")
    @ResponseBody
    public String index(HttpServletRequest request){
        String name = request.getParameter("name");

        if (name==null) {
            name = "World!";
        }

        return "Hello " + name;
    }

    @RequestMapping(value="hello", method=RequestMethod.GET)
    @ResponseBody
    public String helloForm(){
        String html1 = "<form method='post'>" +
                "<input type='text' name='name' />";
        String html2 = "<input type='submit' value='Greet Me!' />" +
                "</form>";

        String langForm = html1 + Greeting.getLanguages() + html2;

        return langForm;
    }

    @RequestMapping(value="hello/{name}")
    @ResponseBody
    public String helloUrlSegment(@PathVariable String name){
        return "Hello " + name;
    }

    @RequestMapping(value="hello", method=RequestMethod.POST)
    @ResponseBody
    public String helloPost(@RequestParam String name,
                            @RequestParam String language,
                            HttpServletResponse response,
                            HttpServletRequest request){

        //read greeting model to find the greeting for a language
        String greeting = Greeting.createMessage(language);

        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        int count = 0;

        if (cookies != null){
            for (int i=0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(name)) {
                    count = Integer.valueOf(cookies[i].getValue()) + 1;
                    Cookie newCookie = new Cookie(name, Integer.toString(count));
                    response.addCookie(newCookie);
                } else {
                    count = 1;
                    Cookie newCookie = new Cookie(name, "1");
                    response.addCookie(newCookie);
                }
            }
        } else {
            count = 1;
            Cookie newCookie = new Cookie(name, "1");
            response.addCookie(newCookie);

        }
        return greeting + name + " " + count;
    }

    @RequestMapping(value="goodbye")
    public String goodbye(){
        return "redirect:/";
    }
}
