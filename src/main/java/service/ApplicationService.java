package service;

import java.util.List;
import java.util.UUID;

public interface ApplicationService {
    Object create(Object product);

    Model update(Model product);

    List<Model> getAll();

    void delete(UUID uuid);

}
