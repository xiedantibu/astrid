package com.todoroo.astrid.people;

import android.content.Context;

import com.timsu.astrid.R;
import com.todoroo.astrid.activity.FilterListFragment;
import com.todoroo.astrid.activity.FilterModeSpec;
import com.todoroo.astrid.activity.TaskListFragment;
import com.todoroo.astrid.api.Filter;
import com.todoroo.astrid.api.FilterListItem;
import com.todoroo.astrid.api.FilterWithUpdate;
import com.todoroo.astrid.helper.AsyncImageView;
import com.todoroo.astrid.ui.MainMenuPopover;

public class PeopleFilterMode implements FilterModeSpec {

    private AsyncImageView imageView;

    @Override
    public Filter getDefaultFilter(Context context) {
        Filter defaultFilter = PeopleFilterExposer.mySharedTasks(context);
        return defaultFilter;
    }

    @Override
    public Class<? extends FilterListFragment> getFilterListClass() {
        return PeopleListFragment.class;
    }

    @Override
    public void onFilterItemClickedCallback(FilterListItem item) {
        if (imageView == null)
            return;
        if (item instanceof FilterWithUpdate)
            imageView.setUrl(((FilterWithUpdate) item).imageUrl);
        else
            imageView.setUrl(null);
    }

    public void setImageView(AsyncImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public int[] getForbiddenMenuItems() {
        return FORBIDDEN_MENU_ITEMS;
    }

    @Override
    public int getMainMenuIconAttr() {
        return R.attr.asPeopleMenu;
    }

    private static final int[] FORBIDDEN_MENU_ITEMS = {
        TaskListFragment.MENU_NEW_FILTER_ID,
        TaskListFragment.MENU_ADDONS_ID,
        MainMenuPopover.MAIN_MENU_ITEM_FRIENDS
    };

    @Override
    public boolean showComments() {
        return true;
    }
}