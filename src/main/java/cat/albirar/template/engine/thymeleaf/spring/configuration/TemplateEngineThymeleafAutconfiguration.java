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
package cat.albirar.template.engine.thymeleaf.spring.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Import;

import cat.albirar.template.engine.configuration.TemplateEngineAutoconfiguration;

/**
 * Autoconfiguration for Thymeleaf engine.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 1.0.0
 */
@AutoConfigureOrder(Integer.MAX_VALUE)
@Import(TemplateEngineThymeleafConfiguration.class)
@ImportAutoConfiguration(classes = {TemplateEngineAutoconfiguration.class})
public class TemplateEngineThymeleafAutconfiguration {

}
