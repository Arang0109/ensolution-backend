
package com.project.easywork.client.domain.persistance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "prevention")
public class Prevention {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "prevention_id")
  private Long preventionId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stack_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ToString.Exclude
  private Stack stack;
  
  @Column(name = "prevention_name", nullable = false, length = 100)
  private String preventionName;
  
  @Column(columnDefinition = "LONGTEXT")
  private String remark;
  
  @OneToMany(mappedBy = "prevention", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<Target> targets = new ArrayList<>();
  
  @OneToMany(mappedBy = "prevention", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<Facility> facilities = new ArrayList<>();
}