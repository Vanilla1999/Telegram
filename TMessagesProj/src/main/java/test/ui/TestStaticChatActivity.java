package test.ui;

import static test.utils.Constants.followDialogList;
import static test.utils.Constants.unfollowDialogList;

import android.os.Bundle;
import org.telegram.tgnet.TLRPC;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ChatActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import test.repository.FollowDialogRepo;
import test.repository.FollowDialogRepoImpl;
import test.room.model.FollowDialog;
import test.utils.Constants;

public class TestStaticChatActivity {
    static CompositeDisposable compositeDisposable = null;
    static FollowDialogRepo followDialogRepo = null;

    public static void clearCompositeDisposeble() {
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

    public static void addFollowToHeaderItem(ActionBarMenuItem headerItem, Bundle arguments, TLRPC.Chat currentChat) {
        if (currentChat != null && !currentChat.creator && !ChatObject.hasAdminRights(currentChat)) {
            headerItem.lazilyAddSubItem(followDialogList, R.drawable.msg_report, LocaleController.getString("FollowedChatsAdd", R.string.FollowedChatsAdd));
            headerItem.lazilyAddSubItem(unfollowDialogList, R.drawable.msg_report, LocaleController.getString("FollowedChatsRemove", R.string.FollowedChatsRemove));
            headerItem.hideSubItem(followDialogList);
            headerItem.showSubItem(unfollowDialogList);
            final long chatId = arguments.getLong("chat_id", 0);
            followDialogRepo = null;
            followDialogRepo = new FollowDialogRepoImpl(ApplicationLoader.instance.getDatabase());
            compositeDisposable.add(
                    followDialogRepo.getDialogsById(-chatId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe((listFollowed -> {
                                headerItem.hideSubItem(followDialogList);
                                headerItem.showSubItem(unfollowDialogList);
                            }), (error -> {
                                // что то на ошибку

                            }), () -> {

                                headerItem.hideSubItem(unfollowDialogList);
                                headerItem.showSubItem(followDialogList);
                            }));
        }
    }

    public static void ifClickToFollowChat(ActionBarMenuItem headerItem, int id, Bundle arguments) {
        if (id == followDialogList) {
            final long chatId = arguments.getLong("chat_id", 0);
            compositeDisposable.add(followDialogRepo.saveDialog(new FollowDialog(-chatId))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            () -> {
                                headerItem.hideSubItem(followDialogList);
                                headerItem.showSubItem(unfollowDialogList);
                            }, error -> {

                            }
                    ));
        }
    }

    public static void ifClickToUnFollowChat(ActionBarMenuItem headerItem, int id, Bundle arguments) {
        if (id == unfollowDialogList) {
            final long chatId = arguments.getLong("chat_id", 0);
            compositeDisposable.add(followDialogRepo.deleteDialog(-chatId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            () -> {
                                headerItem.hideSubItem(unfollowDialogList);
                                headerItem.showSubItem(followDialogList);
                            }, error -> {

                            }
                    ));
        }
    }
}
