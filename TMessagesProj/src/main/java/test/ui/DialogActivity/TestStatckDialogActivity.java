package test.ui.DialogActivity;

import io.reactivex.disposables.CompositeDisposable;
import test.repository.FollowDialogRepo;
import test.ui.BaseTestActivity;

public class TestStatckDialogActivity extends BaseTestActivity {

    public static FollowDialogRepo getFollowRepo() {
        return followDialogRepo;
    }

    public static CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
