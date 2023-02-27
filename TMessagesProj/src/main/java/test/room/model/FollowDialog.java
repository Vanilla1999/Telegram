package test.room.model;

import test.room.entity.FollowEntity;

public class FollowDialog {
    public int idDialog;
    public FollowDialog(int idDialog){
        this.idDialog = idDialog;
    }
    public FollowEntity convertToEntity(){
        return new FollowEntity(this.idDialog);
    }
}
