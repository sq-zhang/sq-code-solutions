package oodesign.callCenter;

import oodesign.callCenter.Employee.Role;

/**
 * @author sqzhang
 * @date 2020/5/24
 */
public class Call {

    private CallState callState;
    private Role role;
    private Employee employee;

    public Call(CallState callState, Role role, Employee employee) {
        this.callState = callState;
        this.role = role;
        this.employee = employee;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setCallState(CallState callState) {
        this.callState = callState;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public CallState getCallState() {
        return callState;
    }

    public enum CallState {
        READY, PROCESSINIG, COMPLETE
    }
}
