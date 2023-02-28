package test.repository;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import test.room.DatabaseMain;
import test.room.entity.FollowEntity;
import test.room.model.FollowDialog;

public class FollowDialogRepoImpl implements FollowDialogRepo {
    private final DatabaseMain databaseMain;

    public FollowDialogRepoImpl(DatabaseMain databaseMain) {
        this.databaseMain = databaseMain;
    }

    @Override
    public Completable saveDialog(FollowDialog followDialog) {
        return databaseMain.followedDialogs().insert(followDialog.convertToEntity());
    }

    @Override
    public Completable deleteDialog(long dialogId) {
        return databaseMain.followedDialogs().delete(dialogId);
    }

    @Override
    public Single<List<FollowDialog>> getAllDialogs() {
        return databaseMain.followedDialogs().getAll().flatMap(followEntities -> {
            List<FollowDialog> listModel = followEntities.stream().map(FollowEntity::convertToModel).collect(Collectors.toList());
            return Single.just(listModel);
        });
    }

    @Override
    public Maybe<FollowDialog> getDialogsById(long dialogId) {
        return databaseMain.followedDialogs().getById(dialogId).flatMap(
                followEntity -> Maybe.just(followEntity.convertToModel()),
                Maybe::error,
                Maybe::empty
        );
    }
}
