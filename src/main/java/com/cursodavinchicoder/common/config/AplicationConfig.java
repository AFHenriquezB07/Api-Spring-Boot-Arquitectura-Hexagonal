package com.cursodavinchicoder.common.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync // anotacion para que nuestre aplicacion sea asincrona
@EnableScheduling // anotacion para que nuestra aplicacion pueda ejecutar tarear programadas
@EnableCaching // anotacion para que nuestra aplicacion pueda guardar cache
public class AplicationConfig {
}
