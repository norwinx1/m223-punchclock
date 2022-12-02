package ch.zli.m223.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.ApplicationUser;

@ApplicationScoped
public class UserService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public ApplicationUser createApplicationUser(ApplicationUser user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public void deleteApplicationUser(Long id) {
        entityManager.remove(entityManager.find(ApplicationUser.class, id));
    }

    public List<ApplicationUser> findAll() {
        var query = entityManager.createQuery("FROM ApplicationUser", ApplicationUser.class);
        return query.getResultList();
    }

    public ApplicationUser find(long id) {
        return entityManager.find(ApplicationUser.class, id);
    }

    @Transactional
    public ApplicationUser update(ApplicationUser user) {
        return entityManager.merge(user);
    }

    public Optional<ApplicationUser> findByUsername(String username) {
        return entityManager
                .createQuery("SELECT u FROM ApplicationUser u WHERE u.username = :username", ApplicationUser.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }
}
