package test.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import test.room.dao.FollowedDialogsDAO;
import test.room.entity.FollowEntity;

@Database(
        version = 1,
        entities = {FollowEntity.class}
)
public abstract class DatabaseMain extends RoomDatabase {
    public abstract FollowedDialogsDAO followedDialogs();
}