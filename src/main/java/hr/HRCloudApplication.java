package hr;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.data.organization.DepartmentRepository;
import hr.data.wage.PayScaleRepository;
import hr.domain.organization.Department;
import hr.domain.wage.PayScale;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@SpringBootApplication
public class HRCloudApplication {

    @org.springframework.beans.factory.annotation.Value("${hr-cloud.launch.filename}")
    private String hrConfigFilename = "";

    public static void main(String[] args) {
        SpringApplication.run(HRCloudApplication.class, args);
    }

    @Bean
    public CommandLineRunner payScaleLoader(PayScaleRepository payScaleRepo, DepartmentRepository departmentRepo) {
        return args -> {
            ClassPathResource resource = new ClassPathResource(hrConfigFilename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            ObjectMapper mapper = new ObjectMapper();

            HRConfig hrConfig;
            try {
                hrConfig = mapper.readValue(reader, HRConfig.class);

            } catch (Exception e) {
                log.error("Failed to read configuration from \"" + hrConfigFilename + "\":" + e.getMessage());
                hrConfig = null;
            }

            if(departmentRepo.findTopByOrderByIdAsc() == null) {
                if (hrConfig != null) {
                    hrConfig.getHead().setPrevious("/");
                    departmentRepo.save(hrConfig.getHead());
                } else {
                    throw new Exception("Head office doesn't exist. Couldn't read configuration file.");
                }
            }

            if (payScaleRepo.count() == 0) {
                if (hrConfig != null) {
                    payScaleRepo.saveAll(hrConfig.getPayScales());
                } else {
                    throw new Exception("Head office doesn't exist. Couldn't read configuration file.");
                }
            }

        };
    }

    @Data
    private static class HRConfig {
        private List<PayScale> payScales;
        private Department head;
    }


}
