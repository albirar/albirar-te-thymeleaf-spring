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
package cat.albirar.template.engine.thymeleaf.test.service;

import static java.util.Collections.synchronizedSortedSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.validation.ValidationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cat.albirar.template.engine.EContentType;
import cat.albirar.template.engine.models.TemplateDefinitionBean;
import cat.albirar.template.engine.models.TemplateInstanceBean;
import cat.albirar.template.engine.models.TemplateInstanceBean.TemplateInstanceBeanBuilder;
import cat.albirar.template.engine.thymeleaf.spring.service.impl.ThymeleafSpringTemplateEngineImpl;
import cat.albirar.template.engine.thymeleaf.spring.service.impl.ThymeleafTemplateEngineContext;
import cat.albirar.template.engine.thymeleaf.spring.service.impl.ThymeleafTemplateEngineContextFactory;
import cat.albirar.template.engine.thymeleaf.test.configuration.DefaultThymeleafTestConfiguration;

/**
 * Test for {@link ThymeleafTemplateEngineContext} class.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 1.0.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DefaultThymeleafTestConfiguration.class)
public class ThymeleafTemplateEngineContextTest extends AbstractThymeleafTest {
    @Autowired
    private ThymeleafTemplateEngineContextFactory templateEngineContextFactory;
    
    @Test public void whenInstantiateWithNull_Then_AValidationExceptionIsThrown() {
        assertThrows(ValidationException.class, () -> templateEngineContextFactory.instantiateContext(null));
    }
    
    @Test public void whenInstantiateWithInvalid_Then_AValidationExceptionIsThrown() {
        TemplateInstanceBean t;
        
        t = TemplateInstanceBean.buildInstance(invalidTemplateDefinition.toBuilder()
                    .templateEngineLanguage(ThymeleafSpringTemplateEngineImpl.TEMPLATE_LANGUAGE)
                    .charSet(Charset.forName("UTF-8"))
                    .contentType(EContentType.TEXT_PLAIN)
                .build())
                .build()
                ;
        assertThrows(ValidationException.class, () -> templateEngineContextFactory.instantiateContext(t));
    }

    @Test public void whenContainsVariableWithNull_Then_AValidationExceptionIsThrown() {
        ThymeleafTemplateEngineContext t;
        t = instantiateWithVars();
        assertThrows(ValidationException.class, () -> t.containsVariable(null));
    }
    @Test public void whenContainsVariableWithEmpty_Then_AValidationExceptionIsThrown() {
        ThymeleafTemplateEngineContext t;
        t = instantiateWithVars();
        assertThrows(ValidationException.class, () -> t.containsVariable(""));
    }
    @Test public void whenContainsVariableWithBlank_Then_AValidationExceptionIsThrown() {
        ThymeleafTemplateEngineContext t;
        t = instantiateWithVars();
        assertThrows(ValidationException.class, () -> t.containsVariable("    "));
    }
    @Test public void whenContainsVariableWithNotExistingName_Then_FalseIsGet() {
        ThymeleafTemplateEngineContext t;
        t = instantiateWithVars();
        assertFalse(t.containsVariable("XXX"));
        assertFalse(t.containsVariable("YYY"));
        assertFalse(t.containsVariable("NNN"));
        assertFalse(t.containsVariable("UUU"));
    }
    @Test public void whenContainsVariableWithExistingName_Then_TrueIsGet() {
        ThymeleafTemplateEngineContext t;
        t = instantiateWithVars();
        
        assertTrue(t.containsVariable(K_TEST_LBL));
        assertTrue(t.containsVariable(K_TEST_P));
        assertTrue(t.containsVariable(K_TEST_TXT_LBL));
    }

    @Test public void whenGetVariableNamesInVarContext_Then_TheCompleteListIsGet() {
        ThymeleafTemplateEngineContext t;
        SortedSet<String> s;
        Set<String> vars;
        
        t = instantiateWithVars();
        vars = t.getVariableNames();
        assertNotNull(vars);
        assertFalse(vars.isEmpty());
        
        s = synchronizedSortedSet(new TreeSet<>());
        
        s.addAll(vars);
        
        assertEquals(keyNames, s);
    }
    @Test public void whenGetVariableNamesInNoVarContex_Then_TheAnEmptyListIsGet() {
        ThymeleafTemplateEngineContext t;
        Set<String> vars;
        
        t = instantiateWithoutVars();
        vars = t.getVariableNames();
        assertNotNull(vars);
        assertTrue(vars.isEmpty());
    }
    
    @Test public void whenGetVariableWithNull_Then_AValidationExceptionIsThrown() {
        ThymeleafTemplateEngineContext t;
        t = instantiateWithVars();
        assertThrows(ValidationException.class, () -> t.getVariable(null));
    }
    @Test public void whenGetVariableWithEmpty_Then_AValidationExceptionIsThrown() {
        ThymeleafTemplateEngineContext t;
        t = instantiateWithVars();
        assertThrows(ValidationException.class, () -> t.getVariable(""));
    }
    @Test public void whenGetVariableWithBlank_Then_AValidationExceptionIsThrown() {
        ThymeleafTemplateEngineContext t;
        t = instantiateWithVars();
        assertThrows(ValidationException.class, () -> t.getVariable("    "));
    }
    @Test public void whenGetTemplateInstanceWithNullTemplateInstance_Then_AValidationExceptionIsThrown() {
        assertThrows(ValidationException.class, () ->templateEngineContextFactory.instantiateContext(null));
    }
    @Test public void whenGetTemplateInstanceWithInstantiatedTemplateInstance_Then_TheTemplateInstanceIsGet() {
        TemplateInstanceBean tib;
        ThymeleafTemplateEngineContext t;
        
        tib = instantiateTemplateInstanceBeanBuilder(thymeleafVarHtmlTemplateDefinition, Optional.of(varsLocale.get("ca")), Optional.of(new Locale("ca"))).build();
        assertNotNull(tib);
        t = templateEngineContextFactory.instantiateContext(tib);
        assertNotNull(t);
        assertNotNull(t.getTemplateInstance());
        assertEquals(tib, t.getTemplateInstance());
    }
    /*
     * getVariableNames
     */
    /**
     * Instantiate engine context with vars in "ca" locale language.
     * @return The newly instantiate engine context
     */
    private ThymeleafTemplateEngineContext instantiateWithVars() {
        return instantiateWithVars("ca");
    }
    /**
     * Instantiate engine context with vars in then indicated {@code lc} locale language.
     * @param lc The locale to instantate
     * @return The newly instantiate engine context
     * @throws IllegalArgumentException If no vars with indicated {@code lc} locale language.
     */
    private ThymeleafTemplateEngineContext instantiateWithVars(String lc) {
        if(varsLocale.containsKey(lc)) {
            return instantiate(thymeleafVarHtmlTemplateDefinition, Optional.of(varsLocale.get(lc)), Optional.of(new Locale(lc)));
        }
        throw new IllegalArgumentException(String.format("Locale %s not supported!", lc));
        
    }
    private ThymeleafTemplateEngineContext instantiateWithoutVars() {
        return instantiate(thymeleafSimpleHtmlTemplateDefinition);
    }
    private ThymeleafTemplateEngineContext instantiate(TemplateDefinitionBean definition) {
        return instantiate(definition, Optional.empty(), Optional.empty());
    }
    private ThymeleafTemplateEngineContext instantiate(TemplateDefinitionBean definition, Optional<Map<String, Object>> vars, Optional<Locale> locale) {
        return instantiate(instantiateTemplateInstanceBeanBuilder(definition, vars, locale).build());
    }
    private ThymeleafTemplateEngineContext instantiate(TemplateInstanceBean templateInstanceBean) {
        return templateEngineContextFactory.instantiateContext(templateInstanceBean);
    }
    private TemplateInstanceBeanBuilder<?, ?> instantiateTemplateInstanceBeanBuilder(TemplateDefinitionBean definition, Optional<Map<String, Object>> vars, Optional<Locale> locale) {
        TemplateInstanceBeanBuilder<?, ?> tbld;
        
        tbld = TemplateInstanceBean.buildInstance(thymeleafVarHtmlTemplateDefinition.toBuilder().build());
        
        if(locale.isPresent()) {
            tbld.locale(locale.get());
        }
        
        if(vars.isPresent()) {
            tbld.variables(vars.get());
        }
        return tbld;
    }
}
