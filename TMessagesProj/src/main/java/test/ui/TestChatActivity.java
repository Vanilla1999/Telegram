package test.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import org.telegram.messenger.ApplicationLoader;
import org.telegram.ui.ChatActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import test.repository.FollowDialogRepo;
import test.repository.FollowDialogRepoImpl;

public class TestChatActivity extends ChatActivity {
    private FollowDialogRepo followDialogRepo;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public TestChatActivity(Bundle args) {
        super(args);
    }

    @Override
    public void setFragmentView(View fragmentView) {
        followDialogRepo = null;
        followDialogRepo = new FollowDialogRepoImpl(ApplicationLoader.instance.getDatabase());
        compositeDisposable.add(
                followDialogRepo.getDialogsById(1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((listFollowed -> {
                            super.setFragmentView(fragmentView);
                        }), (error -> {
                            // что то на ошибку
                            super.setFragmentView(fragmentView);
                        }), () -> {
                            super.setFragmentView(fragmentView);
                        }));
    }
}


