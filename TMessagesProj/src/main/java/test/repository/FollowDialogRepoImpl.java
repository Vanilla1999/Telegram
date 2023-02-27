package test.repository;

import io.reactivex.Completable;
import test.room.DatabaseMain;
import test.room.model.FollowDialog;

public class FollowDialogRepoImpl implements FollowDialogRepo {
    private final DatabaseMain databaseMain;
    FollowDialogRepoImpl(DatabaseMain databaseMain){
        this.databaseMain = databaseMain;
    }

    @Override
    public Completable saveDialog(FollowDialog followDialog) {
       return databaseMain.followedDialogs().update(followDialog.convertToEntity());
    }

    @Override
    public Completable deleteDialog(FollowDialog followDialog) {
        return databaseMain.followedDialogs().delete(followDialog.convertToEntity());
    }
}
