package app.db;

import app.DBService;
import app.MessageSystemContext;
import messageSystem.Address;
import messageSystem.MessageSystem;
import model.User;
import service.UserService;

import java.util.List;
import java.util.Objects;

public class DBServiceImpl implements DBService {
    private final Address address;
    private final MessageSystemContext context;
    private final UserService service;

    public DBServiceImpl(MessageSystemContext context, Address address, UserService service) {
        this.context = context;
        this.address = address;
        this.service = service;
    }

    @Override
    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public User login(String id, String password) {
        User user = service.load(Long.parseLong(id, 10));
        if (Objects.requireNonNull(user).getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public List<User> showAll() {
        return service.loadAll();
    }

    @Override
    public void add(User user) {
        service.save(user);
    }


    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }


}
