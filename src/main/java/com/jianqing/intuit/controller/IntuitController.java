package com.jianqing.intuit.controller;

import com.jianqing.intuit.model.Note;
import com.jianqing.intuit.view.AndriodView;
import com.jianqing.intuit.view.BrowserView;
import com.jianqing.intuit.view.ClientType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
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

    @RequestMapping(value = "/init", produces = "application/json; charset=UTF-8")
    public String init(){
        List<Long> noteIds = new ArrayList<>();

        Note note = new Note();
        note.setNoteId(1l);
        note.setUserId(1l);
        note.setTitle("note 1 title");
        note.setText("note 1 text");

        Note note2 = new Note();
        note2.setNoteId(2l);
        note2.setUserId(1l);
        note2.setTitle("note 2 title");
        note2.setText("note 2 text");

        Note note3 = new Note();
        note3.setNoteId(3l);
        note3.setUserId(2l);
        note3.setTitle("note 3 title");
        note3.setText("note 3 text");

        noteIds.add(noteService.insert(note));
        noteIds.add(noteService.insert(note2));
        noteIds.add(noteService.insert(note3));

        return buildResponse(ClientType.Default, StringUtils.join(noteIds, "\n"));
    }

    @RequestMapping(value = "/reset")
    public String reset(){
        noteService.reset();
        return "reset";
    }

    @RequestMapping(value = "/findByNoteId", method = GET, produces = "application/json; charset=UTF-8")
    public Note findByNoteId(@RequestParam("nid") long uid){
        Note note = noteService.find(uid);
        return note;
    }

    @RequestMapping(value = "/findByUserId", method = GET, produces = "application/json; charset=UTF-8")
    public List<Note> findByUserId(@RequestParam("uid") long uid){
        List<Note> notes = noteService.findWithUserId(uid);
        return notes;
    }

    @RequestMapping(value = "/update", method = GET)
    public String updateByUserNote(@RequestParam("nid") long nid, @RequestParam("text") String text) throws UnsupportedEncodingException {
        Note note = noteService.find(nid);
        note.setText(text);
        note.setLastupdateTime(Calendar.getInstance().getTime());

        noteService.update(note);
        return text;
    }

    @RequestMapping(value = "/web", method = GET)
    public String web(@RequestParam("text") String text ) {
        return buildResponse(ClientType.WEB, text);
    }

    @RequestMapping(value = "/android", method = GET)
    public String android(@RequestParam("text") String text ) {
        return buildResponse(ClientType.Android, text);
    }

    @RequestMapping("/")
    public String index() {
        return "Hello INTUIT!";
    }

    private String buildResponse(ClientType type, String response){
        switch (type){
            case WEB:
                return new BrowserView().display(response);
            case Android:
                return new AndriodView().display(response);
            default:
                return response;
        }
    }

}
