package com.project.easywork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class EasyworkApplication {
  private static final org.slf4j.Logger log =
      org.slf4j.LoggerFactory.getLogger(EasyworkApplication.class);
  
  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(EasyworkApplication.class);
    app.setBannerMode(org.springframework.boot.Banner.Mode.OFF);
    app.run(args);
  }
  
  @EventListener(ApplicationReadyEvent.class)
  public void ready() {
    log.info("Easywork 프로그램이 실행되었습니다.");
  }
}