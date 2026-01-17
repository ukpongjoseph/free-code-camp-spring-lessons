package dev.joseph_free_code_camp.freecodecamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class StudentProfile {

    @Id
    @GeneratedValue
    private Integer Id;

    private String bio;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
    
        
    public StudentProfile(String bio) {
        this.bio = bio;
    }
 
    
    public StudentProfile() {
    }

    
    public Integer getId() {
        return Id;
    }

    
    public void setId(Integer id) {
        Id = id;
    }

    
    public String getBio() {
        return bio;
    }

    
    public void setBio(String bio) {
        this.bio = bio;
    }
    
}
