package me.seo.studyjpa;


import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

// bean을 등록 시킬 수 도 있음 
public class register implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(testregister.class);
        beanDefinition.getPropertyValues().add("name","test");
        registry.registerBeanDefinition("registrytest",beanDefinition);
    }
}
