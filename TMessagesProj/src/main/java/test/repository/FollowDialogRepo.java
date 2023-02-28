package test.repository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import test.room.entity.FollowEntity;
import test.room.model.FollowDialog;

public interface FollowDialogRepo {
    Completable saveDialog(FollowDialog followDialog);

    Completable deleteDialog(long dialogId);

    Single<List<FollowDialog>> getAllDialogs();

    Maybe<FollowDialog> getDialogsById(long dialogId);
}

