# --------------------------
# 🏗️ 1단계: Build Stage
# --------------------------
FROM gradle:8.10.2-jdk21-alpine AS builder

# Gradle 캐시 최적화
ENV GRADLE_USER_HOME=/home/gradle/.gradle

WORKDIR /app

# build.gradle, settings.gradle, gradle.properties 먼저 복사해서 dependency 캐시
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
RUN ./gradlew dependencies --no-daemon || return 0

# 나머지 소스 복사
COPY . .

# jar 빌드 (bootJar 태스크 실행)
RUN ./gradlew clean bootJar --no-daemon


# --------------------------
# 🚀 2단계: Runtime Stage
# --------------------------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# builder 스테이지에서 빌드된 jar 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 포트 노출
EXPOSE 8080

# 환경변수 (Cloud Run에서 .env나 Secret으로 덮어쓸 예정)
ENV SPRING_PROFILES_ACTIVE=prod

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
