package pe.edu.leadyourway.userservice.infrastructure.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.leadyourway.userservice.domain.model.User;
import pe.edu.leadyourway.userservice.interfaces.rest.resources.CreateUserResource;
import pe.edu.leadyourway.userservice.interfaces.rest.resources.UserResource;

import java.io.Serializable;
import java.util.List;

public class UserMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public UserResource toResource(User model) {
        return mapper.map(model, UserResource.class);
    }

    public User toModel(CreateUserResource resource) {
        return mapper.map(resource, User.class);
    }

    public List<UserResource> modelListPage(List<User> modelList) {

        return mapper.mapList(modelList, UserResource.class);
    }
}
