package user.task;

public enum UserTaskState {
    COMPLETED ("completed"),
    CREATED ("created"),
    PAUSE ("pause");
    private String state;

    UserTaskState(String state) {
        this.state = state;
    }

    public void NewState(String newState){
        this.state = state;
    }
}
