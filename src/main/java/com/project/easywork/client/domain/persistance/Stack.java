
package com.project.easywork.client.domain.persistance;

import com.project.easywork.common.constant.Size;
import com.project.easywork.common.constant.Shape;
import com.project.easywork.common.constant.StackType;
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
@Table(name = "stack")
public class Stack {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "stack_id")
  private Long stackId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workplace_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ToString.Exclude
  private Workplace workplace;
  
  @Column(name = "stack_name", nullable = false, length = 100)
  private String stackName;
  
  @Column(name = "sems_number", nullable = false, length = 10)
  private String semsNumber;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "stack_size", length = 10)
  private Size stackSize;
  
  @Column(name ="stack_height", length = 10)
  private Double stackHeight;
  
  @Column(name ="horizontal_length")
  private Double horizontalLength;
  
  @Column(name ="vertical_length")
  private Double verticalLength;
  
  @Enumerated(EnumType.STRING)
  private Shape shape;
  
  @Enumerated(EnumType.STRING)
  @Column(name ="stack_type")
  private StackType stackType;
  
  @Column(columnDefinition = "LONGTEXT")
  private String remark;
  
  @OneToMany(mappedBy = "stack", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<Prevention> preventions = new ArrayList<>();
}