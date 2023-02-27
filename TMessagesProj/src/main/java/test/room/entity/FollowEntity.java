package test.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import test.room.model.FollowDialog;

@Entity(
        tableName = "followDialogTable"
)
public class FollowEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public int idDialog;

    public FollowEntity(int idDialog) {
        this.idDialog = idDialog;
    }
    public FollowDialog convertToModel(){
        return new FollowDialog(this.idDialog);
    }
}
