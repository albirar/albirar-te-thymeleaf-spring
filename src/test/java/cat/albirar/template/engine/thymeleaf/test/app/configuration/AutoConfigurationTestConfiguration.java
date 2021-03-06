/*
 * This file is part of "albirar-template-engine".
 * 
 * "albirar-template-engine" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * "albirar-template-engine" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with "albirar-template-engine" source code.  If not, see <https://www.gnu.org/licenses/gpl-3.0.html>.
 *
 * Copyright (C) 2021 Octavi Fornés
 */
package cat.albirar.template.engine.thymeleaf.test.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import cat.albirar.template.engine.models.ConfigurationPropertiesBean;
import cat.albirar.template.engine.thymeleaf.spring.configuration.AutoconfigureThymeleafTemplateEngine;

/**
 * Testing configuration.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 2.0.2
 */
@SpringBootConfiguration
@AutoconfigureThymeleafTemplateEngine
public class AutoConfigurationTestConfiguration {
    static SpringTemplateEngine templateEngine;
    static SpringResourceTemplateResolver templateResolver;
    /**
     * The {@link SpringTemplateEngine} to use on rendering.
     * @param resolver The resolver for resources
     * @return The {@link SpringTemplateEngine} configured to use them
     */
    @Bean
    public SpringTemplateEngine templateEngine(@Autowired SpringResourceTemplateResolver resolver) {
        templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        return templateEngine;
    }
    /**
     * The template resolver for thymeleaf
     * @param configurationProperties The properties to configure the resolver.
     * @return The template resolver
     */
    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(@Autowired ConfigurationPropertiesBean configurationProperties) {
        templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setCharacterEncoding(configurationProperties.getCharset().name());
        return templateResolver;
    }
}
