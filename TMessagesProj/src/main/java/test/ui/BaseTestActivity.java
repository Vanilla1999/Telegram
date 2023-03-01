package test.ui;

import org.telegram.messenger.ApplicationLoader;

import io.reactivex.disposables.CompositeDisposable;
import test.repository.FollowDialogRepo;
import test.repository.FollowDialogRepoImpl;

public class BaseTestActivity {
    protected static CompositeDisposable compositeDisposable = null;
    protected static FollowDialogRepo followDialogRepo = null;

    public static void clearCompositeDisposeble() {
        if(compositeDisposable != null)
        compositeDisposable.clear();
    }

    public static void initCompositeDisposeble() {
        compositeDisposable = new CompositeDisposable();
    }

    public static void initFollowRepo() {
        followDialogRepo = new FollowDialogRepoImpl(ApplicationLoader.instance.getDatabase());
    }

    public static void clearFollowRepo() {
        followDialogRepo = null;
    }
}
