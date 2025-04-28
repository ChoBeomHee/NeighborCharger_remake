package project.charger.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import project.charger.batch.step.ChargerStepConfig;

@Configuration
public class ChargerJobConfig {
    final private ChargerStepConfig chargerStepConfig;

    public ChargerJobConfig(ChargerStepConfig chargerStepConfig) {
        this.chargerStepConfig = chargerStepConfig;
    }

    @Bean
    public Job testJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws DuplicateJobException {
        return new JobBuilder("updatePublicChargeStepJob",jobRepository)
                .start(chargerStepConfig.updatePublicChargeStep(jobRepository, transactionManager))
                .build();
    }
}
