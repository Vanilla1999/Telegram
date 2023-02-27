package test.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import test.room.entity.FollowEntity;

@Dao
public interface FollowedDialogsDAO {

    @Query("SELECT * FROM followDialogTable")
    Single<List<FollowEntity>> getAll();

    @Query("SELECT * FROM followDialogTable WHERE idDialog = :idDialog")
    Single<FollowEntity> getById(long idDialog);

    @Insert
    Completable insert(FollowEntity employee);

    @Update
    Completable  update(FollowEntity employee);

    @Delete
    Completable  delete(FollowEntity employee);
}
