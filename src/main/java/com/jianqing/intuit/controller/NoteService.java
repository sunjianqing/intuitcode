package com.jianqing.intuit.controller;


import com.jianqing.intuit.model.Note;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jianqingsun on 12/13/17.
 */
@Repository
@Transactional
public class NoteService {

    Map<Long, Note> cache = new HashMap<>();

    @PersistenceContext
    private EntityManager entityManager;

    public long insert(Note note) {
        entityManager.persist(note);
        return note.getNoteId();
    }

    public void reset() {
        String tableName = "Note";
        entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
        cache.clear();
        return;
    }

    public Note find(long id) {
        // check cache
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
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
        // update cache
        int v = note.getVersion();
        note.setVersion(v + 1);
        if (cache.containsKey(note.getNoteId())) {
            cache.put(note.getNoteId(), note);
        }

        entityManager.merge(note);
        return;
    }

}
