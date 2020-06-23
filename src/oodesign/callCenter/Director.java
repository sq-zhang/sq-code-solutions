package oodesign.callCenter;

/**
 * @author sqzhang
 * @date 2020/5/24
 */
public class Director extends Employee {

    public Director(Integer id, String name, Role role, CallCenter callCenter, Call call) {
        super(id, name, role, callCenter, call);
    }

    @Override
    public void escalateCall() throws Exception {
        throw new Exception("director must be able to handle any call");
    }
}
