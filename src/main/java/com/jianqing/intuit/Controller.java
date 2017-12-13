package com.jianqing.intuit;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by jianqing_sun on 12/6/17.
 */
public class Controller {

    private static Controller instance;
    private GenericDAO genericDAO;
    private Controller(){
        this.genericDAO = new GenericDAO();
    }

    public static synchronized Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }

        return instance;
    }

    public String createNote(Request request){
        // Here we assume request body is "user_id,note_id,note_title,note_text"
        String[] info = request.body.split(",");
        Note note = new Note();
        note.setUserId(info[0]);
        note.setNoteId(info[1]);
        note.setTitle(info[2]);
        note.setText(info[3]);

        genericDAO.save(note);
        return buildResponse(request.type, "Create note " + note.getNoteId());
    }

    public String updateNote(Request request) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        // Here we assume request body is "note_id,note_text"
        String[] info = request.body.split(",");
        String noteId = info[0];

        List<Entity> notes = genericDAO.findByProperty(Note.class, "NoteId", noteId);

        Note note = (Note)notes.get(0);

        note.setText(info[1]);

        genericDAO.save(note);

        return buildResponse(request.type, "Update note " + note.getNoteId());
    }

    public String getNotesByUser(Request request) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        // Here we assume request body is user id

        String userId = request.body;

        List<Entity> notes = genericDAO.findByProperty(Note.class, "UserId", userId);

        StringBuilder sb = new StringBuilder();

        for(Entity entity: notes){
            Note note = (Note)entity;
            sb.append(note.getNoteId() + ":" + note.getTitle()+ ":"+ note.getText());
            sb.append("\n");
        }
        return buildResponse(request.type, "get notes from user " + userId + "\n"+ sb.toString());
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
