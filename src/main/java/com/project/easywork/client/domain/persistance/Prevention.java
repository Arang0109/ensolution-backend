
package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.dto.prevention.PreventionUpdateD;
import com.project.easywork.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(name = "prevention")
public class Prevention extends BaseEntity {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stack_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Stack stack;
  
  @Column(nullable = false, length = 100)
  private String name;
  
  @Column(columnDefinition = "LONGTEXT")
  private String remark;
  
  @Builder.Default
  @OneToMany(mappedBy = "prevention", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Target> targets = new ArrayList<>();
  
  @Builder.Default
  @OneToMany(mappedBy = "prevention", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Facility> facilities = new ArrayList<>();
  
  public void attachStack(Stack stack) {
    this.stack = stack;
    stack.getPreventions().add(this);
  }
  
  public void update(PreventionUpdateD dto) {
    PreventionBuilder<?, ?> builder = this.toBuilder();
    
    Optional.ofNullable(dto.getName())
        .ifPresent(v -> this.name = v);
    
    Optional.ofNullable(dto.getRemark())
        .ifPresent(v -> this.remark = v);
    
    apply(builder.build());
  }
  
  private void apply(Prevention prevention) {
    this.name = prevention.getName();
    this.remark = prevention.getRemark();
  }
}