package test.ui.DialogActivity;

import static test.ui.DialogActivity.TestStatckDialogActivity.clearCompositeDisposeble;
import static test.ui.DialogActivity.TestStatckDialogActivity.clearFollowRepo;
import static test.ui.DialogActivity.TestStatckDialogActivity.initCompositeDisposeble;
import static test.ui.DialogActivity.TestStatckDialogActivity.initFollowRepo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.DialogsActivity;

public class FollowDialogsActivity extends DialogsActivity {
    private ActionBarMenuItem headerItem;
    public FollowDialogsActivity(Bundle args) {
        super(args);
    }

    @Override
    public boolean onFragmentCreate() {
        return super.onFragmentCreate();
    }

    @Override
    public View createView(Context context) {
        initFollowRepo();
        initCompositeDisposeble();
        View createdView = super.createView(context);
        actionBar.setTitle(LocaleController.getString("FollowedChats", R.string.FollowedChats));
        actionBar.setBackButtonDrawable(backDrawable = new BackDrawable(false));
        actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick() {
            @Override
            public void onItemClick(int id) {
                if (id == -1) {
                    finishFragment();
                }
            }

            });
        searchItem.setVisibility(View.GONE);
        floatingButtonContainer.setVisibility(View.GONE);
        return createdView;
    }

    @Override
    public ActionBar createActionBar(Context context) {
        ActionBar actionBar = new ActionBar(context, getResourceProvider());
        actionBar.setBackgroundColor(getThemedColor(Theme.key_actionBarDefault));
        actionBar.setItemsBackgroundColor(getThemedColor(Theme.key_actionBarDefaultSelector), false);
        actionBar.setItemsBackgroundColor(getThemedColor(Theme.key_actionBarActionModeDefaultSelector), true);
        actionBar.setItemsColor(getThemedColor(Theme.key_actionBarDefaultIcon), false);
        actionBar.setItemsColor(getThemedColor(Theme.key_actionBarActionModeDefaultIcon), true);
        ActionBarMenu menu = actionBar.createMenu();
        headerItem = menu.addItem(11,R.drawable.ic_ab_other);
        headerItem.setContentDescription(LocaleController.getString("AccDescrMoreOptions", R.string.AccDescrMoreOptions));
        if (inPreviewMode || inBubbleMode) {
            actionBar.setOccupyStatusBar(false);
        }
        return actionBar;
    }

    @Override
    public void onFragmentDestroy() {
        clearFollowRepo();
        clearCompositeDisposeble();
        super.onFragmentDestroy();
    }
}
