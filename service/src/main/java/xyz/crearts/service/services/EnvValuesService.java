package xyz.crearts.service.services;

import org.springframework.stereotype.Service;
import xyz.crearts.service.model.EnvelopmentValue;
import xyz.crearts.service.repository.EnvelopmentValueRepository;

/**
 * @author ivan.kishchenko
 */
@Service
public class EnvValuesService {
    private EnvelopmentValueRepository envelopmentValueRepository;

    public EnvValuesService(EnvelopmentValueRepository envelopmentValueRepository) {
        this.envelopmentValueRepository = envelopmentValueRepository;
    }

    public String getValue(String key, String defaultValue) {
        return envelopmentValueRepository.findByName(key).
                map(EnvelopmentValue::getVal).
                orElse(defaultValue);
    }
}
