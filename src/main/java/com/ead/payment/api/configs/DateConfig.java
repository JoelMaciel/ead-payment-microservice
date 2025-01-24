package com.ead.payment.api.configs;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfig {

    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        javaTimeModule.addSerializer(OffsetDateTime.class,
                new JsonSerializer<>() {
                    @Override
                    public void serialize(OffsetDateTime value, JsonGenerator generator, SerializerProvider serializers)
                            throws IOException {
                        generator.writeString(value.withOffsetSameInstant(ZoneOffset.UTC).format(DATE_TIME_FORMATTER));
                    }
                });

        javaTimeModule.addDeserializer(OffsetDateTime.class,
                new JsonDeserializer<>() {
                    @Override
                    public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt)
                            throws IOException {
                        return OffsetDateTime.parse(jsonParser.getText(), DATE_TIME_FORMATTER);
                    }
                });

        return new ObjectMapper()
                .registerModule(javaTimeModule)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
