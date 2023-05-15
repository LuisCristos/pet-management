package io.cristos.petmanagement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@RequiredArgsConstructor
public class ValidatorConfig {

    private final I18NConfig i18NConfig;

    @Bean
    LocalValidatorFactoryBean getValidator() {

        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(i18NConfig.messageSource());

        return validatorFactoryBean;
    }
}
