package com.db.bex.dbTrainingEnroll.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false, length = 50)
    private String mail;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType type;

    @JsonIgnore
    @Column(nullable = false)
    private boolean enabled;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserGenderType gender;

    @JsonIgnore
    @ManyToOne(cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name="manager_id")
    private User manager;

    @JsonIgnore
    @OneToMany(mappedBy="manager" , fetch = FetchType.EAGER)
    private Set<User> subordinates;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Enrollment> enrollments;

    @JsonIgnore
    @Column(name="last_login_date")
    private Date lastLoginDate;

    @JsonIgnore
    @Column(name="current_login_date")
    private Date currentLoginDate;
}
