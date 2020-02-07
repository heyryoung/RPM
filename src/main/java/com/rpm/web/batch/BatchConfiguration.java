package com.rpm.web.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@RequiredArgsConstructor
@Configuration
public class BatchConfiguration {

        private final JobBuilderFactory jobBuilderFactory; // 생성자 DI 받음
        private final StepBuilderFactory stepBuilderFactory; // 생성자 DI 받음

        @Bean
        public Job simpleJob() {
            return jobBuilderFactory.get("simpleJob")
                    .start(simpleStep1())
                    .build();
        }

        @Bean
        public Step simpleStep1() {
            return stepBuilderFactory.get("simpleStep1")
                    .tasklet((contribution, chunkContext) -> {
                        System.out.println("Batch 실행완료!");
                        return RepeatStatus.FINISHED;
                    })
                    .build();
        }
}
