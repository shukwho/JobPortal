package com.who.shuk.CompanyMS.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
   /* @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    @OneToMany(mappedBy = "company")
    private List<Review> reviews;*/
    public Company() {
    }

    public Company(Long id, String name, String description//, List<Job> jobs, List<Review> reviews
                    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        /*this.jobs = jobs;
        this.reviews = reviews;*/
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*@JsonIgnore
    public List<Job> getJobs() {
        return jobs;
    }

    @JsonProperty
    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
*/

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
               // ", jobs=" + jobs +
               // ", reviews=" + reviews +
                '}';
    }
}
