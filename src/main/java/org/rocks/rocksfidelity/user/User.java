package org.rocks.rocksfidelity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.rocks.rocksfidelity.security.Credential;
import org.rocks.rocksfidelity.security.Role;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private String firstName;

    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    private Credential credential;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Credential getCredential() {
        return credential;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
