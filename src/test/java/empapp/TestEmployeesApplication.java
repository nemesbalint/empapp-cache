package empapp;

import org.springframework.boot.SpringApplication;

public class TestEmployeesApplication {

    public static void main(String[] args) {
        SpringApplication.from(EmployeesApplication::main).with(TestcontainersConfiguration.class).run(args);
    }
}
