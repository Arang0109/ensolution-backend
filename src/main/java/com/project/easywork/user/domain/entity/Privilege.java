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
@Table(name = "privilege")
public class Privilege {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false, unique = true, length = 50)
  private String name;
  
  @ManyToMany(mappedBy = "privileges", fetch = FetchType.LAZY)
  @Builder.Default
  private Set<Role> roles = new HashSet<>();
  
  @Override
  public String toString() {
    return "Privilege [name=" + name + "]" + "[id=" + id + "]";
  }
}