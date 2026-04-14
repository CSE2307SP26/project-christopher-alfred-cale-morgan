package main;

import java.util.ArrayList;
import java.util.List;

public class UserRequestManager {

    private List<UserLimitRequest> requests;

    public UserRequestManager() {
        requests = new ArrayList<>();
    }

    public void addRequest(UserLimitRequest userRequest) {
        requests.add(userRequest);
    }

    public List<UserLimitRequest> getAllRequests() {
        return requests;
    }

}
