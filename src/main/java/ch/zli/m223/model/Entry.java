package ch.zli.m223.model;

import javax.persistence.*;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Entry {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;

  @Column(nullable = false)
  private LocalDateTime checkIn;

  @Column(nullable = false)
  private LocalDateTime checkOut;

  @ManyToOne(optional = false)
  @Fetch(FetchMode.JOIN)
  private Category category;

  @ManyToMany
  @JoinTable(name = "entry_tags", joinColumns = @JoinColumn(name = "entry_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
  @JsonIgnoreProperties("entries")
  @Fetch(FetchMode.JOIN)
  private Set<Tag> tags;

  @ManyToOne(optional = false)
  @Fetch(FetchMode.JOIN)
  private ApplicationUser applicationUser;

  public Entry() {
  }

  public Entry(LocalDateTime checkIn, LocalDateTime checkOut) {
    this.checkIn = checkIn;
    this.checkOut = checkOut;
  }

  public Entry(Long id, LocalDateTime checkIn, LocalDateTime checkOut) {
    this.id = id;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
  }

  public Entry(LocalDateTime checkIn, LocalDateTime checkOut, Category category) {
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.category = category;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(LocalDateTime checkIn) {
    this.checkIn = checkIn;
  }

  public LocalDateTime getCheckOut() {
    return checkOut;
  }

  public void setCheckOut(LocalDateTime checkOut) {
    this.checkOut = checkOut;
  }

  /**
   * @return the category
   */
  public Category getCategory() {
    return category;
  }

  /**
   * @param category the category to set
   */
  public void setCategory(Category category) {
    this.category = category;
  }

  /**
   * @return the tags
   */
  public Set<Tag> getTags() {
    return tags;
  }

  /**
   * @param tags the tags to set
   */
  public void setTags(Set<Tag> tags) {
    this.tags = tags;
  }

  /**
   * @return the user
   */
  public ApplicationUser getUser() {
    return applicationUser;
  }

  /**
   * @param user the user to set
   */
  public void setUser(ApplicationUser user) {
    this.applicationUser = user;
  }

  

}