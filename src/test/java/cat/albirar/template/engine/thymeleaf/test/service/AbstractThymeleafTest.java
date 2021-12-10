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
package cat.albirar.template.engine.thymeleaf.test.service;

import static cat.albirar.template.engine.thymeleaf.spring.service.impl.ThymeleafSpringTemplateEngineImpl.TEMPLATE_LANGUAGE;
import static java.util.Collections.synchronizedMap;
import static java.util.Collections.synchronizedSortedSet;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeAll;

import cat.albirar.template.engine.EContentType;
import cat.albirar.template.engine.models.TemplateDefinitionBean;
import cat.albirar.template.engine.test.service.AbstractTest;

/**
 * Abstract class with some common methods and properties.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 1.0.0
 */
public abstract class AbstractThymeleafTest extends AbstractTest {
    protected static final String K_TEST_P = "test.description";
    protected static final String K_TEST_LBL = "test.uname";
    protected static final String K_TEST_TXT_LBL = "test.text.uname";
    
    protected static final String SIMPLE_HTML_TEMPLATE_TEST = "classpath:/cat/albirar/template/engine/thymeleaf/test/templates/simpleTemplate.html";
    protected static final String SIMPLE_TXT_TEMPLATE_TEST = "classpath:/cat/albirar/template/engine/thymeleaf/test/templates/simpleTemplate.txt";
    
    protected static final String VARS_HTML_TEMPLATE_TEST = "classpath:/cat/albirar/template/engine/thymeleaf/test/templates/varsTemplate.html";
    protected static final String VARS_TXT_TEMPLATE_TEST = "classpath:/cat/albirar/template/engine/thymeleaf/test/templates/varsTemplate.txt";
    protected static final String VARS_TEST_H1 = "Vars test title";
    
    protected static final String VARS_MSG_TEST_MSG_RESOURCE = "cat/albirar/template/engine/thymeleaf/test/messages/testMessages";
    protected static final String VARS_HTML_MSG_TEMPLATE_TEST = "classpath:/cat/albirar/template/engine/thymeleaf/test/templates/varsMessagesTemplate.html";
    protected static final String VARS_TXT_MSG_TEMPLATE_TEST = "classpath:/cat/albirar/template/engine/thymeleaf/test/templates/varsMessagesTemplate.txt";
    protected static final String VARS_MSG_TEST_H1 = "Vars &amp; Msg test title";
    
    protected static final String VARS_MSG_TEST_P_EN = "This is a template for test with variables";
    protected static final String VARS_MSG_TEST_P_CA = "Aquesta és una plantilla de prova amb variables";
    protected static final String VARS_MSG_TEST_P_FR = "Ceci est un modèle de test avec des variables";
    
    protected static final String VARS_MSG_TEST_LBL_EN = "User name:&nbsp;";
    protected static final String VARS_MSG_TEST_LBL_CA = "Nom d'usuari:&nbsp;";
    protected static final String VARS_MSG_TEST_LBL_FR = "Nom d'utilisateur:&nbsp;";

    protected static final String VARS_MSG_TEST_TXT_H1 = "Vars & Msg test title";
    protected static final String VARS_MSG_TEST_TXT_LBL_EN = "User name: ";
    protected static final String VARS_MSG_TEST_TXT_LBL_CA = "Nom d'usuari: ";
    protected static final String VARS_MSG_TEST_TXT_LBL_FR = "Nom d'utilisateur: ";

    protected static Map<String, Object> varsCa;
    protected static Map<String, Object> varsEn;
    protected static Map<String, Object> varsFr;
    protected static Map<String, Map<String, Object>> varsLocale;
    
    protected static SortedSet<String> keyNames;
    
    protected static final TemplateDefinitionBean thymeleafSimpleHtmlTemplateDefinition = TemplateDefinitionBean.builder()
            .name("Test 2")
            .contentType(EContentType.HTML)
            .template(SIMPLE_HTML_TEMPLATE_TEST)
            .templateEngineLanguage(TEMPLATE_LANGUAGE)
            .build()
            ;
    
    protected static final TemplateDefinitionBean thymeleafSimpleTxtTemplateDefinition = TemplateDefinitionBean.builder()
            .name("Test 2")
            .contentType(EContentType.TEXT_PLAIN)
            .template(SIMPLE_TXT_TEMPLATE_TEST)
            .templateEngineLanguage(TEMPLATE_LANGUAGE)
            .build()
            ;
    
    protected static final TemplateDefinitionBean thymeleafVarHtmlTemplateDefinition = TemplateDefinitionBean.builder()
            .name("Test 2")
            .contentType(EContentType.HTML)
            .template(VARS_HTML_TEMPLATE_TEST)
            .templateEngineLanguage(TEMPLATE_LANGUAGE)
            .build()
            ;
    
    protected static final TemplateDefinitionBean thymeleafVarMsgHtmlTemplateDefinition = TemplateDefinitionBean.builder()
            .name("Test 2")
            .contentType(EContentType.HTML)
            .template(VARS_HTML_MSG_TEMPLATE_TEST)
            .templateEngineLanguage(TEMPLATE_LANGUAGE)
            .build()
            ;
    
    protected static final TemplateDefinitionBean thymeleafVarTxtTemplateDefinition = TemplateDefinitionBean.builder()
            .name("Test 2")
            .template(VARS_TXT_TEMPLATE_TEST)
            .templateEngineLanguage(TEMPLATE_LANGUAGE)
            .build()
            ;

    protected static final TemplateDefinitionBean thymeleafVarMsgTxtTemplateDefinition = TemplateDefinitionBean.builder()
            .name("Test 2")
            .template(VARS_TXT_MSG_TEMPLATE_TEST)
            .templateEngineLanguage(TEMPLATE_LANGUAGE)
            .build()
            ;
    
    
    @BeforeAll public static void prepareTestClasses() {
        varsCa = synchronizedMap(new TreeMap<>());
        varsEn = synchronizedMap(new TreeMap<>());
        varsFr = synchronizedMap(new TreeMap<>());
        varsLocale = synchronizedMap(new TreeMap<>());
        
        varsCa.put(K_TEST_P, VARS_MSG_TEST_P_CA);
        varsCa.put(K_TEST_LBL, VARS_MSG_TEST_LBL_CA);
        varsCa.put(K_TEST_TXT_LBL, VARS_MSG_TEST_TXT_LBL_CA);
        
        varsEn.put(K_TEST_P, VARS_MSG_TEST_P_EN);
        varsEn.put(K_TEST_LBL, VARS_MSG_TEST_LBL_EN);
        varsEn.put(K_TEST_TXT_LBL, VARS_MSG_TEST_TXT_LBL_EN);
        
        varsFr.put(K_TEST_P, VARS_MSG_TEST_P_FR);
        varsFr.put(K_TEST_LBL, VARS_MSG_TEST_LBL_FR);
        varsFr.put(K_TEST_TXT_LBL, VARS_MSG_TEST_TXT_LBL_FR);
        
        varsLocale.put("ca", varsCa);
        varsLocale.put("en", varsEn);
        varsLocale.put("fr", varsFr);
        
        keyNames = synchronizedSortedSet(new TreeSet<>());
        keyNames.add(K_TEST_LBL);
        keyNames.add(K_TEST_P);
        keyNames.add(K_TEST_TXT_LBL);
    }
}
