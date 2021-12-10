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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import cat.albirar.template.engine.models.TemplateInstanceBean;

/**
 * Factory to instantiate {@link ThymeleafTemplateEngineContext}.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 1.0.0
 */
@Component
@Validated
public class ThymeleafTemplateEngineContextFactory  {
    /**
     * Unique factory member to instantiate a {@link ThymeleafTemplateEngineContext} bean
     * @param t The template instance to associate with the IContext
     * @return
     */
    public ThymeleafTemplateEngineContext instantiateContext(@NotNull @Valid TemplateInstanceBean t) {
        return new ThymeleafTemplateEngineContext(t);
    }
}
