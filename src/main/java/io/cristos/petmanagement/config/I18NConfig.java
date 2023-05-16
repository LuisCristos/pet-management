package io.cristos.petmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class I18NConfig {

    @Bean
    public AcceptHeaderLocaleResolver localeResolver() {

        final AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.US);
        // Setting System default Locale to US
        Locale.setDefault(Locale.US);

        return resolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {

        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }
}
