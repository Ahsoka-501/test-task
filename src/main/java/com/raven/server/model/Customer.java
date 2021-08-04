package com.raven.server.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table
@Getter
@Setter
@ToString(exclude = {"created", "updated", "isActive"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Customer {
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.MainView.class)
    private long id;

    @Size(min = 2, max = 50)
    @JsonView(Views.MainView.class)
    private String fullName;

    @Email
    @Size(min = 2, max = 100)
    @JsonView(Views.MainView.class)
    private String email;

    @JsonView(Views.MainView.class)
    private String phone;

    @Column(updatable = false)
    private long created;

    private long updated;

    private boolean isActive;
}
