package com.project.easywork.client.controller;

import com.project.easywork.client.domain.dto.company.CompanyCreateD;
import com.project.easywork.client.domain.dto.company.CompanyDetailD;
import com.project.easywork.client.domain.dto.company.CompanyD;
import com.project.easywork.client.domain.dto.company.CompanyUpdateD;
import com.project.easywork.client.service.ICompanyService;
import com.project.easywork.common.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<ApiResponse<CompanyD>> register(
      @Valid @RequestBody CompanyCreateD request
  ) {
    return ResponseEntity.ok().body(ApiResponse.success(companyService.registerCompany(request)));
  }
  
  @Operation(summary = "의뢰업체 목록 조회 API", description = "전체 의뢰업체 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<CompanyD>>> getList() {
    return ResponseEntity.ok().body(ApiResponse.success(companyService.getCompanies()));
  }
  
  @Operation(summary = "의뢰업체 조회 API", description = "해당 의뢰업체의 상세정보를 조회합니다.")
  @GetMapping("/{companyId}")
  public ResponseEntity<ApiResponse<CompanyDetailD>> get(@PathVariable Long companyId) {
    return ResponseEntity.ok().body(ApiResponse.success(companyService.getCompany(companyId)));
  }
  
  @Operation(summary = "의뢰업체 수정 API", description = "해당 의뢰업체의 상세정보를 수정합니다.")
  @PatchMapping("/{companyId}")
  public ResponseEntity<ApiResponse<CompanyD>> update
      (
          @PathVariable Long companyId,
          @Valid @RequestBody CompanyUpdateD request
      ) {
    return ResponseEntity.ok(ApiResponse.success(companyService.updateCompany(companyId, request)));
  }
  
  @Operation(summary = "의뢰업체 삭제 API", description = "의뢰업체 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{companyId}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long companyId) {
    companyService.removeCompany(companyId);
    return ResponseEntity.ok(ApiResponse.success());
  }
}
