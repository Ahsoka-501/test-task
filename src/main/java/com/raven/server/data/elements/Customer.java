package com.raven.server.data.elements;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table
public class Customer {
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.MainView.class)
    private Long id;

    @JsonView(Views.MainView.class)
    private String fullName;

    @JsonView(Views.MainView.class)
    private String email;

    @JsonView(Views.MainView.class)
    private String phone;

    @Column(updatable = false)
    private Long created;

    private Long updated;

    private Boolean isActive;

    public Customer() {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public Long getCreated() {
        return created;
    }

    public Long getUpdated() {
        return updated;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
