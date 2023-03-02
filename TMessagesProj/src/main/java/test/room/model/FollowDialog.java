package test.room.model;

import test.room.entity.FollowEntity;

public class FollowDialog {
    public long idDialog;
    public FollowDialog(long idDialog){
        this.idDialog = idDialog;
    }
    public FollowEntity convertToEntity(){
        return new FollowEntity(this.idDialog);
    }
}
