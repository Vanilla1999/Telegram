package test.ui.DialogActivity.adapter;

import static test.ui.DialogActivity.TestStatckDialogActivity.getCompositeDisposable;
import static test.ui.DialogActivity.TestStatckDialogActivity.getFollowRepo;

import java.util.List;
import java.util.function.Consumer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import test.room.model.FollowDialog;
import test.utils.Constants;
import test.utils.SupplierVoid;

public class TestDialogAdapter {
    public static void updateItems(int folderId, Consumer<List<FollowDialog>> function, SupplierVoid supplier){
        if (folderId == Constants.followDialogList) {
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