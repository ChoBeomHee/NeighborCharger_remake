package project.charger.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
public class ChargerStepConfig {
    public Step testStep(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("testStep",jobRepository)
                .tasklet(testTasklet(),transactionManager)
                .build();
    }

    public Tasklet testTasklet(){
        return ((contribution, chunkContext) -> {
            System.out.println("***** hello batch! *****");
            // 원하는 비지니스 로직 작성
            return RepeatStatus.FINISHED;
        });
    }
}
