package com.jianqing.intuit.controller;

import com.jianqing.intuit.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by jianqingsun on 12/13/17.
 */
@RestController
public class IntuitController {

    @Autowired
    DataSource dataSource;

    @Autowired
    private NoteService noteService;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping(value = "/init")
    public String test(){
        Note note = new Note();
        note.setNoteId(1l);
        note.setUserId(1l);


        Note note2 = new Note();
        note2.setNoteId(2l);
        note2.setUserId(1l);

        noteService.insert(note);
        noteService.insert(note2);
        return " hello world";
    }

    @RequestMapping(value = "/find", method = GET)
    public String findByUserId(@RequestParam("uid") long uid){

        List<Note> notes = noteService.findWithUserId(uid);
        for(Note n : notes){
            System.out.println(n.getNoteId());
        }
        return " hello world";
    }

    @RequestMapping(value = "/update", method = GET)
    public String updateByUserNote(@RequestParam("nid") long nid, @RequestParam("text") String text){

        Note note = noteService.find(nid);
        note.setText(text);
        noteService.update(note);
        return " hello world";
    }


    @RequestMapping("/")
    public String index() {

        return "Greetings from Spring Boot!";
    }

}
