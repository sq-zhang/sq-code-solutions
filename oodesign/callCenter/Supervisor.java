package oodesign.callCenter;

/**
 * @author sqzhang
 * @date 2020/5/24
 */
public class Supervisor extends Employee {

    public Supervisor(Integer id, String name, Role role, CallCenter callCenter, Call call) {
        super(id, name, role, callCenter, call);
    }

    @Override
    public void escalateCall() {
        this.getCall().setRole(Role.DIRECTOR);
        super._escalateCall();
    }
}
