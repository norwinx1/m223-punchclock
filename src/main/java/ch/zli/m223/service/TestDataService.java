package ch.zli.m223.service;

import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

import ch.zli.m223.model.Category;
import ch.zli.m223.model.Entry;
import ch.zli.m223.model.Tag;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;

@IfBuildProfile("dev")
@ApplicationScoped
public class TestDataService {
    @Inject
    private EntryService entryService;

    @Inject
    private CategoryService categoryService;

    @Inject
    private TagService tagService;

    @Transactional
    void generateTestData(@Observes StartupEvent startupEvent) {
        Category category1 = new Category("Category 1");
        categoryService.createCategory(category1);
        categoryService.createCategory(new Category("Category 2"));

        tagService.createTag(new Tag("Tag 1"));
        tagService.createTag(new Tag("Tag 2"));
        tagService.createTag(new Tag("Tag 3"));

        entryService.createEntry(new Entry(LocalDateTime.now(), LocalDateTime.now(), category1));
    }

}
