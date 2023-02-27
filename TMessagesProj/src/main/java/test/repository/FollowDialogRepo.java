package test.repository;

import io.reactivex.Completable;
import test.room.model.FollowDialog;

public interface FollowDialogRepo {
    Completable saveDialog(FollowDialog followDialog);

    Completable deleteDialog(FollowDialog followDialog);
}

