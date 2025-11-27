package com.project.easywork.user.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "Role")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @ManyToMany(mappedBy = "roles")
  private Collection<User> users;
  
  @ManyToMany
  @JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
  private Collection<Privilege> privileges;
  
  private String name;
  
  public Role(final String name) {
    super();
    this.name = name;
  }
  
  @Override
  public String toString() {
    return "Role [name=" + name + "]" + "[id=" + id + "]";
  }
}
