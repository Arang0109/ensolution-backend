package com.project.easywork.user.domain.entity;

import com.project.easywork.agency.entity.Team;
import com.project.easywork.common.constant.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", unique = true)
  private Long userId;
  
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
  
  @Column(name = "birth_date", nullable = false, length = 8)
  private String birthDate;
  
  @Column(nullable = false, unique = true)
  private String ci;
  
  @Column(nullable = false)
  private String di;
  
  
  @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
  private LocalDateTime createdAt;
  
  @Column(name = "updated_at", nullable = false, updatable = false, insertable = false)
  private LocalDateTime updatedAt;
  
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10)
  private Status status = Status.ACTIVE;
  
  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }
  
  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
  
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
  
  public void changeTeam(Team newTeam) {
    this.team = newTeam;
  }
  
  public void changeStatus(Status newStatus) {
    this.status = newStatus;
  }
}