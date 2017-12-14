package com.jianqing.intuit.controller;


import com.jianqing.intuit.model.Note;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by jianqingsun on 12/13/17.
 */
@Repository
@Transactional
public class NoteService {
    @PersistenceContext
    private EntityManager entityManager;

    public long insert(Note note) {
        entityManager.persist(note);
        return note.getNoteId();
    }

    public void reset() {
        String tableName = "Note";
        entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
        return;
    }

    public Note find(long id) {
        return entityManager.find(Note.class, id);
    }

    public List<Note> findAll() {
        Query query = entityManager.createQuery("select n from Note n");
        List<Note> resultList = query.getResultList();
        return resultList;
    }

    public List<Note> findWithUserId(long userId) {
        return entityManager.createQuery(
                "SELECT n FROM Note n WHERE n.userId  = :userId")
                .setParameter("userId", userId)
                .getResultList();
    }

    public void update(Note note) {
        entityManager.merge(note);
        return;
    }


}
