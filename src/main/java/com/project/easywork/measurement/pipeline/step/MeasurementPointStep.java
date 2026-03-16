package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.measurement.pipeline.context.MeasurementContext;

import java.math.BigDecimal;

public class MeasurementPointStep implements MeasurementStep {
  
  private static final BigDecimal TWO = BigDecimal.valueOf(2);
  
  @Override
  public void execute(MeasurementContext context) {
//    Measurement domain = context.getDomain();
//    MeasurementDoc measurement = domain.getMeasurement();
//
//    Shape stackShape = measurement.getClient().getStack().getShape();
//    BigDecimal diameter = measurement.getClient().getStack().getHorizontalLength();
//    BigDecimal diameter2   = measurement.getClient().getStack().getVerticalLength();
//
//    boolean simplifiedMeasurement = measurement.getPreInfo().isSimplifiedMeasurement();
//
//    MeasurePointStrategy strategy;
//
//    switch (stackShape) {
//      case RECTANGULAR -> {
//        strategy = MeasurePointStrategyFactory.of("rectangular");
//
//        MeasurementDoc updatedMeasurement = measurement.toBuilder()
//          .measureData(
//            measurement.getMeasureData().toBuilder()
//              .measurementPointCnt(strategy.calculate(diameter, diameter2))
//              .build()
//          )
//          .build();
//
//        domain.updateMeasurement(updatedMeasurement);
//      }
//
//      case CIRCULAR -> {
//        strategy = MeasurePointStrategyFactory.of("circular");
//        int pointCount = strategy.calculate(diameter);
//
//        int n = simplifiedMeasurement
//            ? (pointCount == 1 ? 1 : pointCount / 4)
//            : pointCount;
//
//        List<BigDecimal> coords = new ArrayList<>();
//        BigDecimal r = diameter.divide(TWO, 6, RoundingMode.HALF_UP);
//
//        for (int i = 0; i < n; i++) {
//          // (2i + 1) / (2n)
//          BigDecimal numerator = BigDecimal.valueOf(2L * i + 1);
//          BigDecimal denominator = BigDecimal.valueOf(2L * n);
//
//          BigDecimal ratio = numerator.divide(denominator, 10, RoundingMode.HALF_UP);
//
//          // sqrt는 double로 계산 → BigDecimal 복귀
//          BigDecimal k = r.multiply(
//              BigDecimal.valueOf(Math.sqrt(ratio.doubleValue()))
//          );
//
//          BigDecimal coord = r.subtract(k);
//          coords.add(coord);
//        }
//
//        MeasurementDoc updatedMeasurement = measurement.toBuilder()
//            .measureData(
//                measurement.getMeasureData().toBuilder()
//                    .measurementPointCnt(strategy.calculate(diameter, diameter2))
//                    .build()
//            )
//            .build();
//
//        domain.updateMeasurement(updatedMeasurement);
//      }
//
//      default -> throw new CustomException(ErrorCode.BAD_REQUEST, "Unsupported stack shape" + stackShape);
//    }
  }
}