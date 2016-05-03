package com.butterfly.newyorktimesnews;

import com.butterfly.newyorktimesnews.activity.BaseActivity;
import com.butterfly.newyorktimesnews.fragment.MainFragment;
import com.butterfly.newyorktimesnews.fragment.MainFragment_;
import com.butterfly.newyorktimesnews.fragment.NewsListFragment;
import com.butterfly.newyorktimesnews.fragment.NewsListFragment_;
import com.butterfly.newyorktimesnews.fragment.NewsPreviewFragment;
import com.butterfly.newyorktimesnews.fragment.NewsPreviewFragment_;
import com.butterfly.newyorktimesnews.interfaces.OnMainFragmentListener;
import com.butterfly.newyorktimesnews.restful.model.NewsModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements OnMainFragmentListener {

    @AfterViews
    protected void afterViews() {
        toMainFragment();
    }

    void toMainFragment() {
        MainFragment mainFragment = new MainFragment_();
        replaceFragment(R.id.content_frame, mainFragment, true);
    }

    public void toListFragment(NewsModel newsModel) {
        NewsListFragment newsListFragment = NewsListFragment_.builder().newsModel(newsModel).build();
        replaceFragment(R.id.content_frame, newsListFragment, true);
    }

    public void toNewsPreviewFragment(String link) {
        NewsPreviewFragment newsPreviewFragment = NewsPreviewFragment_.builder().url(link).build();
        replaceFragment(R.id.content_frame, newsPreviewFragment, true);

    }

    @Override
    public void onCloseFragment(String tag) {

    }

    @Override
    public void onStartFragment(String tag) {

    }
}
