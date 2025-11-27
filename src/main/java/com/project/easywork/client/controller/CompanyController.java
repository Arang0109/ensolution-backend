package com.project.easywork.client.controller;

import com.project.easywork.client.domain.dto.company.CompanyCreateRequestDto;
import com.project.easywork.client.domain.dto.company.CompanyResponseDto;
import com.project.easywork.client.domain.dto.company.CompanyUpdateRequestDto;
import com.project.easywork.client.service.ICompanyService;
import com.project.easywork.common.util.ApiResponse;
import com.project.easywork.common.validator.ValidationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Company", description = "측정대행 의뢰업체 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {
  private final ICompanyService companyService;
  
  @Operation(summary = "의뢰업체 등록 API", description = "새로운 의뢰업체 정보를 데이터베이스에 저장합니다.")
  @PostMapping()
  public ResponseEntity<ApiResponse<Void>> registerCompany(
      @Valid @RequestBody CompanyCreateRequestDto request,
      BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    companyService.registerCompany(request);
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
  
  @Operation(summary = "의뢰업체 목록 조회 API", description = "전체 의뢰업체 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<CompanyResponseDto>>> getCompanies() {
    System.out.println("companyService.getCompanies() : " + companyService.getCompanies());
    return ResponseEntity.ok().body(ApiResponse.ok(companyService.getCompanies()));
  }
  
  @Operation(summary = "의뢰업체 조회 API", description = "해당 의뢰업체의 상세정보를 조회합니다.")
  @GetMapping("/{companyId}")
  public ResponseEntity<ApiResponse<CompanyResponseDto>> getCompany(@PathVariable Long companyId) {
    return ResponseEntity.ok().body(ApiResponse.ok(companyService.getCompany(companyId)));
  }
  
  @Operation(summary = "의뢰업체 수정 API", description = "해당 의뢰업체의 상세정보를 수정합니다.")
  @PatchMapping("/{companyId}")
  public ResponseEntity<ApiResponse<Void>> updateWorkplace
      (
          @PathVariable Long companyId,
          @Valid @RequestBody CompanyUpdateRequestDto request
      ) {
    
    companyService.updateCompany(companyId, request);
    
    return ResponseEntity.ok(ApiResponse.ok());
  }
  
  @Operation(summary = "사업장 삭제 API", description = "사업장 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{companyId}")
  public ResponseEntity<ApiResponse<String>> removeCompany(@PathVariable Long companyId) {
    companyService.removeCompany(companyId);
    
    return ResponseEntity.ok(ApiResponse.ok());
  }
}
