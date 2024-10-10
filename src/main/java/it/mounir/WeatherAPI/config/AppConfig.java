package it.mounir.WeatherAPI.config;

import it.mounir.WeatherAPI.model.WeatherResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*
    * Cambiando il tipo di parametri accettato da RedisTemplate, da <String, String> a
    * <String, WeatherResponse>, per salvare nella cache di Redis direttamente un oggetto di tipo WeatherResponse,
    * Spring Boot non è riuscito a iniettare le dipendenze necessarie per l'oggetto perchè di default configura il
    * RedisTemplate per usare chiavi e valori di tipo String. Avendo cambiato il tipo dei valori Spring non sa più come
    * serializzare e deserializzare l'oggetto RedisTemplate, perciò bisogna configurazione personalizzata.
    *
    * Nello specifico ha bisogno che li venga specificato un serializer che possa convertire l'oggetto
    * WeatherResponse in un forato accettato da Redis (in questo caso un Json).
    * */
    @Bean
    public RedisTemplate<String, WeatherResponse> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, WeatherResponse> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Configura il serializer per le chiavi (String)
        template.setKeySerializer(new StringRedisSerializer());

        // Configura il serializer per i valori (WeatherResponse in formato JSON)
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }
}
