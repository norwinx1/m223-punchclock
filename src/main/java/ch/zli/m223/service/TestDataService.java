package ch.zli.m223.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Category;
import ch.zli.m223.model.Entry;
import ch.zli.m223.model.Tag;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;

@IfBuildProfile("dev")
@ApplicationScoped
public class TestDataService {
    @Inject
    EntityManager entityManager;

    @Transactional
    void generateTestData(@Observes StartupEvent event) {
        entityManager.createNativeQuery("DELETE FROM entry_tags").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM entry").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM category").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM tag").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM applicationuser").executeUpdate();

        // Categories
        var projectACategory = new Category();
        projectACategory.setTitle("Project A");
        entityManager.persist(projectACategory);

        var projectBCategory = new Category();
        projectBCategory.setTitle("Project B");
        entityManager.persist(projectBCategory);

        var projectCCategory = new Category();
        projectCCategory.setTitle("Project C");
        entityManager.persist(projectCCategory);

        // User
        var user = new ApplicationUser("User 1", "password");
        entityManager.persist(user);

        // Tags
        var programmingTag = new Tag();
        programmingTag.setTitle("Programming");
        entityManager.persist(programmingTag);

        var debuggingTag = new Tag();
        debuggingTag.setTitle("Debugging");
        entityManager.persist(debuggingTag);

        var meetingTag = new Tag();
        meetingTag.setTitle("Meeting");
        entityManager.persist(meetingTag);

        // Entries
        var firstEntry = new Entry();
        firstEntry.setCategory(projectACategory);
        firstEntry.setTags(new HashSet<>(Arrays.asList(programmingTag, debuggingTag)));
        firstEntry.setCheckIn(LocalDateTime.now().minusHours(3));
        firstEntry.setCheckOut(LocalDateTime.now().minusHours(2));
        firstEntry.setUser(user);
        entityManager.persist(firstEntry);

        var secondEntry = new Entry();
        secondEntry.setCategory(projectACategory);
        secondEntry.setTags(new HashSet<>(Arrays.asList(meetingTag)));
        secondEntry.setCheckIn(LocalDateTime.now().minusHours(2));
        secondEntry.setCheckOut(LocalDateTime.now().minusHours(1));
        secondEntry.setUser(user);
        entityManager.persist(secondEntry);

        var thirdEntry = new Entry();
        thirdEntry.setCategory(projectBCategory);
        thirdEntry.setTags(new HashSet<>(Arrays.asList(programmingTag)));
        thirdEntry.setCheckIn(LocalDateTime.now().minusHours(1));
        thirdEntry.setCheckOut(LocalDateTime.now());
        thirdEntry.setUser(user);
        entityManager.persist(thirdEntry);
    }

}
