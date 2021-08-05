package com.raven.server.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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

    @NotBlank
    @Length(min = 2, max = 50)
    @JsonView(Views.MainView.class)
    private String fullName;

    @Email
    @Length(min = 2, max = 100)
    @JsonView(Views.MainView.class)
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{6,14}$")
    @JsonView(Views.MainView.class)
    private String phone;

    @Column(updatable = false)
    private long created;

    private long updated;

    private boolean isActive;
}
