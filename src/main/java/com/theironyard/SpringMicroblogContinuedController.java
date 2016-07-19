package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasantia on 7/19/16.
 */

@Controller
public class SpringMicroblogContinuedController {

    @Autowired
    MessageRepository messages;

    public static final String SESSION_USER = "userName";

    /**Create list where messages can be displayed from and find session user
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(path ="/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        List<Message>messageList;
        messageList = messages.findAll();

        String userName = (String) session.getAttribute(SESSION_USER);
        model.addAttribute("name", userName);
        model.addAttribute("messages", messageList);
        return "home";
    }

    /**Create session user
     *
     * @param session
     * @param login
     * @return
     */
    @RequestMapping(path="/login", method = RequestMethod.POST)
    public String login(HttpSession session, String login){
        session.setAttribute(SESSION_USER, login);
        return "redirect:/";
    }

    /**Terminate session
     *
     * @param session
     * @return
     */
    @RequestMapping(path="/logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    /**Creates new message
     *
     * @param text
     * @return
     */
    @RequestMapping(path="/add-message", method = RequestMethod.POST)
    public String addMessage(String text){
        Message message = new Message(text);
        messages.save(message);
        return "redirect:/";
    }

    /**Delete message
     *
     * @param id
     * @return
     */
    @RequestMapping(path="/delete-message", method = RequestMethod.POST)
    public String deleteMessage(int id){
        messages.delete(id);
        return "redirect:/";
    }

    /**Update message based on user input
     *
     * @param text
     * @param id
     * @return
     */
    @RequestMapping(path = "/edit-message", method = RequestMethod.POST)
    public String editMessage(String text, Integer id){

        messages.findOne(id);
        messages.delete(id);
        Message message = new Message(text);
        messages.save(message);
        return "redirect:/";
    }

}
