package com.project.easywork.user.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "Role")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false, unique = true, length = 50)
  private String name;
  
  @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
  @Builder.Default
  private Set<User> users = new HashSet<>();
  
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "roles_privileges",
      joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
  )
  @Builder.Default
  private Set<Privilege> privileges = new HashSet<>();
  
  public Role(String name) {
    this.name = name;
  }
  
  @Override
  public String toString() {
    return "Role [name=" + name + "]" + "[id=" + id + "]";
  }
}
