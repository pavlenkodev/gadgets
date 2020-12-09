package tech.itpark.marketplace.service;

import org.springframework.stereotype.Service;
import tech.itpark.marketplace.model.Description;
import tech.itpark.marketplace.repository.DescriptionRepository;

import java.util.List;

@Service
public class DescriptionService {

    private final DescriptionRepository descriptionRepository;

    public DescriptionService(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    public Description findById(Long id) {
        return descriptionRepository.getOne(id);
    }

    public List<Description> findAll() {
        return descriptionRepository.findAll();
    }

    public Description save(Description description) {
        return descriptionRepository.save(description);
    }

    public void removeById(Long id) {
        descriptionRepository.deleteById(id);
    }
}
