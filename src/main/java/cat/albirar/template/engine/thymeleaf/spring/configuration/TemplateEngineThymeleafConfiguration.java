/*
 * This file is part of "albirar albirar-template-engine".
 * 
 * "albirar albirar-template-engine" is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * "albirar albirar-template-engine" is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with "albirar albirar-template-engine" source
 * code. If not, see <https://www.gnu.org/licenses/gpl-3.0.html>.
 *
 * Copyright (C) 2020 Octavi Fornés
 */
package cat.albirar.template.engine.thymeleaf.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import cat.albirar.template.engine.configuration.TemplateEngineConfiguration;
import cat.albirar.template.engine.models.ConfigurationPropertiesBean;
import cat.albirar.template.engine.thymeleaf.spring.service.impl.ThymeleafSpringTemplateEngineImpl;

/**
 * The configuration for thymeleaf-spring template engine.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 1.0.0
 */
@Configuration
@Import(TemplateEngineConfiguration.class)
@ComponentScan(basePackageClasses = {ThymeleafSpringTemplateEngineImpl.class})
public class TemplateEngineThymeleafConfiguration {
    /**
     * The {@link SpringTemplateEngine} to use on rendering.
     * @param resolver The resolver for resources
     * @return The {@link SpringTemplateEngine} configured to use them
     */
    @Bean
    @ConditionalOnMissingBean
    public SpringTemplateEngine templateEngine(@Autowired SpringResourceTemplateResolver resolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        return templateEngine;
    }
    /**
     * The template resolver for thymeleaf.
     * @param configurationProperties The configuration properties for thymeleaf resolver.
     * @return The resolver
     */
    @Bean
    @ConditionalOnMissingBean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(@Autowired ConfigurationPropertiesBean configurationProperties) {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setCharacterEncoding(configurationProperties.getCharset().name());
        return templateResolver;
    }
}
