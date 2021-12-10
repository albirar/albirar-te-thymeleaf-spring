/*
 * This file is part of "albirar albirar-template-engine".
 * 
 * "albirar albirar-template-engine" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * "albirar albirar-template-engine" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with "albirar albirar-template-engine" source code.  If not, see <https://www.gnu.org/licenses/gpl-3.0.html>.
 *
 * Copyright (C) 2020 Octavi Fornés
 */
package cat.albirar.template.engine.thymeleaf.spring.service.impl;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateSpec;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import cat.albirar.template.engine.EContentType;
import cat.albirar.template.engine.models.TemplateInstanceBean;
import cat.albirar.template.engine.service.ITemplateEngine;
import cat.albirar.template.engine.service.ITemplateEngineRegistry;

/**
 * The default {@link ITemplateEngine} implementation backed with {@link SpringTemplateEngine}, that supports https://www.thymeleaf.org/[thymeleaf] 
 * {@code markup language} with https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-view-thymeleaf[spring MVC] enhancements.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 1.0.0
 */
@Component
public class ThymeleafSpringTemplateEngineImpl implements ITemplateEngine, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThymeleafSpringTemplateEngineImpl.class);
    
    /**
     * The template language identifier for this engine.
     */
    public static final String TEMPLATE_LANGUAGE = "thymeleaf-spring";
    
    @Autowired
    private SpringResourceTemplateResolver templateResolver;
    
    @Autowired 
    private ITemplateEngineRegistry registry;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        registry.register(this);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getTemplateLanguage() {
        return TEMPLATE_LANGUAGE;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String renderTemplate(TemplateInstanceBean template) {
        SpringTemplateEngine templateEngine;
        TemplateSpec tspec;
    
        LOGGER.debug("Render template {}", template);
        templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setMessageSource(template.getMessages());
        tspec = new TemplateSpec(template.getTemplate(), Collections.emptySet(), getTemplateMode(template.getContentType())
                , template.getVariables());
        return templateEngine.process(tspec, new ThymeleafTemplateEngineContext(template));
    }

    /**
     * Get the {@link TemplateMode} for the indicated {@code contentType}.
     * @param contentType The content type to get template mode
     * @return the {@link TemplateMode} related to this element.
     */
    private TemplateMode getTemplateMode(EContentType contentType) {
        if(contentType == EContentType.HTML) {
            return TemplateMode.HTML;
        }
        return TemplateMode.TEXT;
    }
}
