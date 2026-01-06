package com.project.easywork.user.domain.entity;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.user.domain.Active;
import com.project.easywork.common.domain.BaseEntity;
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
  
  /* =========================
   * Relations
   * ========================= */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id")
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @ToString.Exclude
  private Team team;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  @Builder.Default
  private Set<Role> roles = new HashSet<>();
  
  /* =========================
   * Columns
   * ========================= */
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
  
  @Column(nullable = false, unique = true, length = 11)
  private String phoneNumber;
  
  @Column(nullable = false)
  private LocalDate birthDate;
  
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10)
  private Active active = Active.ACTIVE;
  
  /* =========================
   * Relation Logic
   * ========================= */
  private void attachTeam(Team team) {
    if (this.team != null) {
      this.team.getMembers().remove(this);
    }
    this.team = team;
    if (team != null) {
      team.getMembers().add(this);
    }
  }
  
  public void changeTeam(Team team) {
    attachTeam(team);
  }
  
  /* =========================
   * Profile Logic
   * ========================= */
  public void updateProfile(
      String name,
      String email,
      String department,
      String grade,
      String phoneNumber
  ) {
    if (name != null) this.name = name;
    if (email != null) this.email = email;
    if (department != null) this.department = department;
    if (grade != null) this.grade = grade;
    if (phoneNumber != null) this.phoneNumber = phoneNumber;
  }
  
  public void changePassword(String encodedPassword) {
    this.password = encodedPassword;
  }
  
  /* =========================
   * Role Logic
   * ========================= */
  public void addRole(Role role) {
    if (role == null) return;
    this.roles.add(role);
  }
  
  public void removeRole(Role role) {
    if (role == null) return;
    this.roles.remove(role);
  }
}