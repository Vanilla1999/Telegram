package test.ui.DialogActivity.adapter;

import static test.ui.DialogActivity.TestStatckDialogActivity.getCompositeDisposable;
import static test.ui.DialogActivity.TestStatckDialogActivity.getFollowRepo;

import org.telegram.messenger.MessagesController;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import test.utils.Constants;

public class TestDialogAdapter {
    public static void updateItemList(int folderId){
        if (folderId == Constants.followDialogList) {
            getCompositeDisposable().add(getFollowRepo().getAllDialogs()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((listFollowed -> {
                        itemInternals.clear();
                        updateHasHints();
                        MessagesController messagesController = MessagesController.getInstance(currentAccount);
                        final List<TLRPC.Dialog> listDialogs = new ArrayList<TLRPC.Dialog>();
                        for(int i =0;i<listFollowed.size();i++){
                            TLRPC.Dialog test = new TLRPC.TL_dialog();
                            test.id = listFollowed.get(i).idDialog;
                            listDialogs.add(test);
                        }
                        updateItemListAfterGetData(listDialogs,messagesController);
                        super.notifyDataSetChanged();
                    }), (error -> {
                        // что то на ошибку
                    })));
        }
    }
}
