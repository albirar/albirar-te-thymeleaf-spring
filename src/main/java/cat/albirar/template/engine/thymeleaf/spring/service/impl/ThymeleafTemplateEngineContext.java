/*
 * This file is part of "albirar-te-thymeleaf-spring".
 * 
 * "albirar-te-thymeleaf-spring" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * "albirar-te-thymeleaf-spring" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with "albirar-te-thymeleaf-spring" source code.  If not, see <https://www.gnu.org/licenses/gpl-3.0.html>.
 *
 * Copyright (C) 2021 Octavi Fornés
 */
package cat.albirar.template.engine.thymeleaf.spring.service.impl;

import java.util.Locale;
import java.util.Set;

import javax.validation.ValidationException;

import org.springframework.util.StringUtils;
import org.thymeleaf.context.IContext;

import cat.albirar.template.engine.models.TemplateInstanceBean;

/**
 * The context for template instance rendering.
 * Set the https://thymeleaf.org[Thymeleaf] context for render a {@link TemplateInstanceBean template instance}, like {@link Locale}, parameters, etc.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 1.0.0
 */
public class ThymeleafTemplateEngineContext implements IContext {
    private TemplateInstanceBean templateInstance;
    /**
     * Constructor.
     * @param t The template instance bean to work with
     */
    ThymeleafTemplateEngineContext(TemplateInstanceBean t) {
        
        this.templateInstance = t;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Locale getLocale() {
        return templateInstance.getLocale();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsVariable(String name) {
        if(!StringUtils.hasText(name)) {
            throw new ValidationException();
        }
        return templateInstance.getVariables().containsKey(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getVariableNames() {
        return templateInstance.getVariables().keySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getVariable(String name) {
        if(!StringUtils.hasText(name)) {
            throw new ValidationException();
        }
        return templateInstance.getVariables().get(name);
    }
    /**
     * The original template instance.
     * @return The template instance
     */
    public TemplateInstanceBean getTemplateInstance() {
        return templateInstance;
    }
}
