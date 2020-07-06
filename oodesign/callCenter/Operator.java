package oodesign.callCenter;

/**
 * @author sqzhang
 * @date 2020/5/24
 */
public class Operator extends Employee {

    public Operator(Integer id, String name, Role role, CallCenter callCenter, Call call) {
        super(id, name, role, callCenter, call);
    }

    @Override
    public void escalateCall() {
        this.getCall().setRole(Role.SUPERVISOR);
        super._escalateCall();
    }
}
