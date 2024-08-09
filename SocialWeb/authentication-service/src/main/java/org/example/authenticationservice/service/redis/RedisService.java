package org.example.authenticationservice.service.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final JsonMapper jsonMapper;

    public void saveObject(String key, Object value) throws JsonProcessingException {
        redisTemplate.opsForValue().set(key, jsonMapper.writeValueAsString(value));
    }

    public <T> T getObject(String key, Class<T> valueClass) throws JsonProcessingException {
        return jsonMapper.readValue((String) redisTemplate.opsForValue().get(key), valueClass);
    }
}
