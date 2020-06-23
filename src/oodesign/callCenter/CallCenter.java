package oodesign.callCenter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import oodesign.callCenter.Employee.Role;

/**
 * @author sqzhang
 * @date 2020/5/24
 */
public class CallCenter {
    private List<Operator> operators;
    private List<Supervisor> supervisors;
    private List<Director> directors;
    private Queue<Call> queue;

    public CallCenter(List<Operator> operators, List<Supervisor> supervisors, List<Director> directors) {
        this.operators = operators;
        this.supervisors = supervisors;
        this.directors = directors;
        this.queue = new LinkedList<>();
    }

    public void dispatchCall(Call call) {
        Employee employee = null;
        if (Role.OPERATOR.equals(call.getRole())) {
            employee = getFreeEmployee(call, this.operators);
        }
        if (Role.SUPERVISOR.equals(call.getRole()) || employee == null) {
            employee = getFreeEmployee(call, this.supervisors);
        }
        if (Role.DIRECTOR.equals(call.getRole()) || employee == null) {
            employee = getFreeEmployee(call, this.directors);
        }
        if (employee == null) {
            this.queue.add(call);
        }
    }

    public void notifyCallEscalated(Call call) {

    }

    public void notifyCallCompleted(Call call) {

    }

    public void dispatchQueueCallToFreeEmployee(Call call, Employee employee) {
        employee.takeCall(call);
    }

    private <T extends Employee> T getFreeEmployee(Call call, List<T> employees) {
        for(T employee : employees) {
            if (employee.getCall() == null) {
                employee.takeCall(call);
                return employee;
            }
        }
        return null;
    }
}
