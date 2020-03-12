package tekup.mail;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public @interface Scheduled {


	String Cron() default "";
	String zone() default "";
	long fixedDelay() default -1;  
    String fixedDelayString() default "";
    long fixedRate() default -1;
    String fixedRateString() default "";
    long initialDelay() default -1;
    String initialDelayString() default "";
}
