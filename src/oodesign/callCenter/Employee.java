package oodesign.callCenter;

import oodesign.callCenter.Call.CallState;

/**
 * @author sqzhang
 * @date 2020/5/24
 */
public abstract class Employee {

    private Integer id;
    private String name;
    private Role role;
    private CallCenter callCenter;
    private Call call;

    public enum Role {
        OPERATOR, SUPERVISOR, DIRECTOR
    }

    public Employee(Integer id, String name, Role role, CallCenter callCenter, Call call) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.callCenter = callCenter;
        this.call = call;
    }

    public Call getCall() {
        return call;
    }

    public void takeCall(Call call) {
        call.setEmployee(this);
        call.setCallState(CallState.PROCESSINIG);
        this.call = call;
    }

    public void completeCall() {
        this.call.setCallState(CallState.COMPLETE);
        this.callCenter.notifyCallCompleted(call);
    }

    abstract public void escalateCall() throws Exception;

    public void _escalateCall() {
        this.call.setCallState(CallState.READY);
        this.callCenter.notifyCallEscalated(call);
        this.call = null;
    }
}
