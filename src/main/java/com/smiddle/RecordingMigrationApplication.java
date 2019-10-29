package com.smiddle;

import com.smiddle.core.service.MigrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com.smiddle")
@RequiredArgsConstructor
public class RecordingMigrationApplication implements CommandLineRunner {

    private final List<MigrationService> migrationServices;

    public static void main(String[] args) {
        SpringApplication.run(RecordingMigrationApplication.class, args);
    }

    @Override
    public void run(String... args) {
        migrationServices.forEach(MigrationService::migrate);
    }
}
