package test.ui.DialogActivity.adapter;

import static test.ui.DialogActivity.TestStatckDialogActivity.getCompositeDisposable;
import static test.ui.DialogActivity.TestStatckDialogActivity.getFollowRepo;

import org.telegram.ui.DialogsActivity;

import java.util.List;
import java.util.function.Consumer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import test.room.model.FollowDialog;
import test.ui.DialogActivity.FollowDialogsActivity;
import test.utils.SupplierVoid;

public class TestDialogAdapter {
    public static void updateItems(DialogsActivity parrentFragment, Consumer<List<FollowDialog>> function, SupplierVoid supplier){
        if (parrentFragment instanceof FollowDialogsActivity) {
            getCompositeDisposable().add(getFollowRepo().getAllDialogs()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((function::accept), (error -> {
                        // что то на ошибку
                    })));
        } else{
            supplier.get();
        }
    }
}