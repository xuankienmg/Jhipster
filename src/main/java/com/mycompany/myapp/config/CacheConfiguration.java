package com.mycompany.myapp.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.mycompany.myapp.domain.User.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Authority.class.getName());
            createCache(cm, com.mycompany.myapp.domain.User.class.getName() + ".authorities");
            createCache(cm, com.mycompany.myapp.domain.DqRuleTypes.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DqRuleCategories.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DqRuleStatus.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DqRuleRiskLevels.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DqRuleActions.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DqRules.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DqRules.class.getName() + ".stds");
            createCache(cm, com.mycompany.myapp.domain.DqRules.class.getName() + ".cols");
            createCache(cm, com.mycompany.myapp.domain.DqNotifications.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DqStandardTypes.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DqStandards.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DqStandards.class.getName() + ".rules");
            createCache(cm, com.mycompany.myapp.domain.DqStandardDetailsEntityVarchar.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DqStandardDetailsEntityText.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DqStandardDetailsEntityInt.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DqStandardDetailsEntityDecimal.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DqStandardDetailsEntityDatetime.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DqStandardDetailsEntityTime.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DsColumns.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DsColumns.class.getName() + ".rules");
            createCache(cm, com.mycompany.myapp.domain.DataDefinition.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DsColumnTypes.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DsTables.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DsTableTypes.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DpSourceTables.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DpSourceColumns.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DataMapping.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DsStores.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DsDbmsTypes.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DsCollations.class.getName());
            createCache(cm, com.mycompany.myapp.domain.EtlStatus.class.getName());
            createCache(cm, com.mycompany.myapp.domain.EtlPackages.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DataFlows.class.getName());
            createCache(cm, com.mycompany.myapp.domain.EventCategories.class.getName());
            createCache(cm, com.mycompany.myapp.domain.EventTypes.class.getName());
            createCache(cm, com.mycompany.myapp.domain.EventLogs.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

}
