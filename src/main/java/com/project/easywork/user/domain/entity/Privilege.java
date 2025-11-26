package com.project.easywork.user.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "privilege")
public class Privilege {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  private String name;
  
  @ManyToMany(mappedBy = "privileges")
  private Collection<Role> roles;
  
  @Override
  public String toString() {
    return "Privilege [name=" + name + "]" + "[id=" + id + "]";
  }
}