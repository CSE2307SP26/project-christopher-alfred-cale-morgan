package main;

import java.util.ArrayList;
import java.util.List;

public class UserRequests {

    private List<UserLimitRequest> requests;

    public UserRequests() {
        requests = new ArrayList<>();
    }

    public void addRequest(UserLimitRequest userRequest) {
        requests.add(userRequest);
    }

    public List<UserLimitRequest> getAllRequests() {
        return requests;
    }

}
