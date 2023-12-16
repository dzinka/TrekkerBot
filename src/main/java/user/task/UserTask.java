package user.task;

import user.User;

public class UserTask {
    private String identifier;
    private User creator;
    private User responsible;
    private String description;
    private UserTaskState State;
    private String dedline;

    public UserTask(User creator, User responsible, String identifier){
        this.creator = creator;
        this.responsible = responsible;
        this.identifier = identifier;
        this.State = UserTaskState.CREATED;
    }
    public String GetIdentifier() { return identifier; }
    public User GetCreator(){
        return creator;
    }
    public User GetResponsible(){ return responsible; }
    public String GetDetails(){ return description;}
    public void ChangeStatus(String newStatus){
        this.State.NewState(newStatus); 
    }
    public void ChangeDescription(String data){
        this.description = data;
    }
    public String GetAllDetails() {
        String result = "Описание задачи: " + this.description + "\n";
        if (dedline != null){
            result += "Дейдлайн: " + this.dedline;
        }
        return result;
    }
    public void ChangeDedline(String newDedline){ this.dedline = newDedline;}

}

