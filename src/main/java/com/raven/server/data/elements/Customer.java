package com.raven.server.data.elements;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;

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

    @JsonView(Views.MainView.class)
    private String fullName;

    @JsonView(Views.MainView.class)
    private String email;

    @JsonView(Views.MainView.class)
    private String phone;

    @Column(updatable = false)
    private long created;

    private long updated;

    private boolean isActive;
}
