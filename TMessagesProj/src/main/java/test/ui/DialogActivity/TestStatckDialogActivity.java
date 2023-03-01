package test.ui.DialogActivity;

import org.telegram.messenger.ApplicationLoader;

import io.reactivex.disposables.CompositeDisposable;
import test.repository.FollowDialogRepo;
import test.repository.FollowDialogRepoImpl;

public class TestStatckDialogActivity  {

    private static CompositeDisposable compositeDisposable = null;
    private static FollowDialogRepo followDialogRepo = null;

    public static void clearCompositeDisposeble() {
        if(compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
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

    public static FollowDialogRepo getFollowRepo() {
        return followDialogRepo;
    }

    public static CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
