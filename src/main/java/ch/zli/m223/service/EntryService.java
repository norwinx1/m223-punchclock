package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Entry;

@ApplicationScoped
public class EntryService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Entry createEntry(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    @Transactional
    public void deleteEntry(Long id) {
        entityManager.remove(entityManager.find(Entry.class, id));
    }

    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry", Entry.class);
        return query.getResultList();
    }

    public Entry find(long id) {
        return entityManager.find(Entry.class, id);
    }

    @Transactional
    public Entry update(Entry entry) {
        return entityManager.merge(entry);
    }
}
