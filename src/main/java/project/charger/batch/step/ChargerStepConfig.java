package project.charger.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import project.charger.domain.charger.service.PublicChargerService;

@Component
public class ChargerStepConfig {
    final private PublicChargerService publicChargerService;

    public ChargerStepConfig(PublicChargerService publicChargerService) {
        this.publicChargerService = publicChargerService;
    }

    public Step updatePublicChargeStep(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("updatePublicCharger",jobRepository)
                .tasklet(updatePublicCharger(),transactionManager)
                .build();
    }

    public Tasklet updatePublicCharger(){
        return ((contribution, chunkContext) -> {
            if (publicChargerService.updatePublicChargerList()){
                System.out.println("API 호출 성공");
            }
            return RepeatStatus.FINISHED;
        });
    }
}
