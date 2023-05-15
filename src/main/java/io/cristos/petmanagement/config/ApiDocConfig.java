package io.cristos.petmanagement.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(info =
@Info(
        title = "pet-management",
        version = "0.01",
        description = "Documentation for the pet-management.",
        license = @License(name = "No License needed", url = "http://placeholder.com"),
        contact = @Contact(url = "no website", name = "me", email = "me@me.com")
))
public class ApiDocConfig {
}
