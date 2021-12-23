package com.kb.kiwibugback.role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)

    private ERole name;

    public Role() {

    }

    public Role(final ERole name) {
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return this.name;
    }

    public void setName(final ERole name) {
        this.name = name;
    }
}