package com.project.easywork.report.domain.bundle;

import com.project.easywork.report.domain.client.ClientDataD;
import com.project.easywork.report.domain.client.PreDataD;
import com.project.easywork.report.domain.equipment.EquipmentDataD;
import com.project.easywork.report.domain.sheet.SheetDataD;
import com.project.easywork.report.domain.stack.StackDataD;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DataBundle {
  PreDataD preData;
  ClientDataD clientData;
  StackDataD stackData;
  EquipmentDataD equipmentData;
  List<SheetDataD> sheetDataList;
}
