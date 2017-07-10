package pl.lucash.resteasy.user;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.Session;
import pl.lucash.resteasy.infrastructure.AppConfig;
import pl.lucash.resteasy.infrastructure.DataSource;
import pl.lucash.resteasy.user.dto.UserDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    private DozerBeanMapper dozerBeanMapper;
    private DataSource dataSource;

    UserService() {
    }

    @Inject
    UserService(AppConfig appConfig) {
        this.dozerBeanMapper = appConfig.getDozerBeanMapper();
        this.dataSource = appConfig.getDataSource();
    }

    @SuppressWarnings("unchecked")
    List<UserDTO> all() {
        LOGGER.info(this.toString());
        Session session = dataSource.beginTransaction();
        List<User> result = (List<User>) session.createQuery("from User").list();
        dataSource.endTransaction(session);
        return result.stream().map(user -> dozerBeanMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    UserDTO add(UserDTO userDTO) {
        LOGGER.info(this.toString());
        User user = dozerBeanMapper.map(userDTO, User.class);
        user.setUuid(UUID.randomUUID().toString());
        Session session = dataSource.beginTransaction();
        Serializable serializable = session.save(user);
        user = (User) session.get(User.class, serializable);
        dataSource.endTransaction(session);
        return dozerBeanMapper.map(user, UserDTO.class);
    }

    int add(int i, int j) {
        return i + j;
    }
}
