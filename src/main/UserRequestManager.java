package main;

import java.util.ArrayList;
import java.util.List;

public class UserRequestManager {
    private List<UserRequest> requests;

    public UserRequestManager() {
        requests = new ArrayList<>();
    }

    public void addRequest(UserRequest userRequest) {
        requests.add(userRequest);
    }

    public void removeRequest(UserRequest userRequest) {
        requests.remove(userRequest);
    }

    public List<UserRequest> getAllRequests() {
        return requests;
    }
}