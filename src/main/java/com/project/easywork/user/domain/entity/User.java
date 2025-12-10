package com.project.easywork.user.domain.entity;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.user.domain.Active;
import com.project.easywork.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(name = "users")
public class User extends BaseEntity {
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id")
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @ToString.Exclude
  private Team team;
  
  @Column(nullable = false, unique = true, length = 20)
  private String username;
  
  @Column(nullable = false)
  @ToString.Exclude
  private String password;
  
  @Column(length = 100)
  private String grade;
  
  @Column(length = 100)
  private String department;
  
  @Column(nullable = false, length = 20)
  private String name;
  
  @Column(nullable = false)
  private String email;
  
  @Column(name = "phone_number", nullable = false, unique = true, length = 11)
  private String phoneNumber;
  
  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;
  
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10)
  private Active active = Active.ACTIVE;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  @Builder.Default
  private Set<Role> roles = new HashSet<>();
  
  public void updateProfile(String name, String email, String department, String grade, String phoneNumber) {
    if (name != null) this.name = name;
    if (email != null) this.email = email;
    if (department != null) this.department = department;
    if (grade != null) this.grade = grade;
    if (phoneNumber != null) this.phoneNumber = phoneNumber;
  }
  
  public void changePassword(String encodedPassword) {
    this.password = encodedPassword;
  }
}