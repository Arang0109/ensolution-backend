package com.project.easywork.client.controller;

import com.project.easywork.common.util.ApiResponseMessage;
import com.project.easywork.common.validator.ValidationUtils;
import com.project.easywork.client.dto.WorkplaceDto;
import com.project.easywork.client.dto.list.WorkplaceProfileDto;
import com.project.easywork.client.dto.view.WorkplaceDetailDto;
import com.project.easywork.client.dto.update.WorkplaceUpdateDto;
import com.project.easywork.client.service.WorkplaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/workplaces")
@RequiredArgsConstructor
public class WorkplaceController {
  
  private final WorkplaceService workplaceService;
  
  @Operation(summary = "측정대상 사업장 등록", description = "새로운 사업장 정보를 데이터베이스에 저장")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "생성 성공"),
      @ApiResponse(responseCode = "400", description = "요청 형식 오류")
  })
  @PostMapping("/register")
  public ResponseEntity<ApiResponseMessage<WorkplaceDto>> registerWorkplace
      (
          @Valid @RequestBody WorkplaceDto request,
          BindingResult bindingResult
      ) {
    
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    
    workplaceService.registerWorkplace(request);
    
    ApiResponseMessage<WorkplaceDto> registerSuccess = new ApiResponseMessage<>(true, "의뢰업체 성공", null);
    
    return ResponseEntity.status(HttpStatus.CREATED).body(registerSuccess);
  }
  
  @Operation(summary = "측정대상 사업장 전체 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "조회 성공"),
      @ApiResponse(responseCode = "401", description = "인증 실패"),
      @ApiResponse(responseCode = "404", description = "측정대상 사업장이 존재하지 않음")
  })
  @GetMapping()
  public ResponseEntity<ApiResponseMessage<List<WorkplaceProfileDto>>> getWorkplaces() {
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "조회 성공", workplaceService.getWorkplaces())
    );
  }
  
  @Operation(summary = "측정대상 사업장 단건 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "조회 성공"),
      @ApiResponse(responseCode = "401", description = "인증 실패"),
      @ApiResponse(responseCode = "404", description = "측정대상 사업장이 존재하지 않음")
  })
  @GetMapping("/{workplaceId}")
  public ResponseEntity<ApiResponseMessage<WorkplaceDetailDto>> getWorkplace(@PathVariable Long workplaceId) {
    WorkplaceDetailDto response = workplaceService.getWorkplace(workplaceId);
    return ResponseEntity.ok(new ApiResponseMessage<>(true, "단건 조회 성공", response));
  }
  
  @Operation(summary = "측정대상 사업장 수정")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "수정 성공"),
      @ApiResponse(responseCode = "401", description = "인증 실패"),
      @ApiResponse(responseCode = "404", description = "측정대상 사업장이 존재하지 않음")
  })
  @PatchMapping("/{workplaceId}")
  public ResponseEntity<ApiResponseMessage<WorkplaceUpdateDto>> updateWorkplace
      (
          @PathVariable Long workplaceId,
          @Valid @RequestBody WorkplaceUpdateDto request
      ) {
    WorkplaceUpdateDto response = workplaceService.updateWorkplaceProfile(workplaceId, request);
    
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "수정 성공", response)
    );
  }
  
  @Operation(summary = "측정대상 사업장 삭제")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "삭제 성공"),
      @ApiResponse(responseCode = "401", description = "인증 실패"),
      @ApiResponse(responseCode = "404", description = "측정대상 사업장이 존재하지 않음")
  })
  @DeleteMapping("/{workplaceId}")
  public ResponseEntity<ApiResponseMessage<String>> removeWorkplace(@PathVariable Long workplaceId) {
    workplaceService.removeWorkplace(workplaceId);
    
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "삭제 성공", "success")
    );
  }
}