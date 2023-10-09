package jackynote.pro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    public static void main(String... args) {
        SpringApplication.run(MainApplication.class);
    }

    @Bean
    CommandLineRunner init(BulkAdminUser bulkAdminUser) {
        return args -> bulkAdminUser.initRootAdmin();
    }

}
