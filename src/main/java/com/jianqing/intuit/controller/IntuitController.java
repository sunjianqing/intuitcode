package com.jianqing.intuit.controller;

import com.jianqing.intuit.model.Note;
import com.jianqing.intuit.model.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * Created by jianqingsun on 12/13/17.
 */
@RestController
public class IntuitController {

    @Autowired
    DataSource dataSource;

    @Autowired
    NoteRepository noteRepository;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping(value = "/test")
    public String test(){
        for (Note note : noteRepository.findAll()) {
            System.out.println(note.getNoteId());
        }

        return " hello world";
    }

    @RequestMapping("/")
    public String index() {

        return "Greetings from Spring Boot!";
    }

}
