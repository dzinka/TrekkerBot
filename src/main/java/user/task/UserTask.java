package user.task;

import user.User;

public class UserTask {
    private User creator;
    private User responsible;
    private String Description;
    private UserTaskState State;

    private String Dedline;

    public UserTask(User creator, User responsible, String Description){
        this.creator = creator;
        this.responsible = responsible;
        this.Description = Description;
        this.State = UserTaskState.CREATED;
    }
    public User GetCreator(){
        return creator;
    }
    public User GetResponsible(){ return responsible; }
    public String GetDetails(){
        return Description;
    }
    public void ChangeStatus(String newStatus){
        this.State.NewState(newStatus); 
    }
    public void ChangeDescription(String data){
        this.Description = data;
    }

}

