package TrekkerBot;

public class UserTask {
    private User creator;
    private User responsible;
    private final String Description;
    private String Status;

    public UserTask(User creator, User responsible, String Description){
        this.creator = creator;
        this.responsible = responsible;
        this.Description = Description;
        this.Status = "в ожидании";
    }
    public User GetResponsible(){ return responsible; }
    public String GetDetails(){
        return Description;
    }
    public void ChangeStatus(String newStatus){
        this.Status = newStatus;
    }
}
//Hello
