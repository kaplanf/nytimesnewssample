package com.butterfly.newyorktimesnews.fragment;

import android.content.Context;
import android.widget.ListView;

import com.butterfly.newyorktimesnews.MainActivity;
import com.butterfly.newyorktimesnews.R;
import com.butterfly.newyorktimesnews.adapters.NewsListAdapter;
import com.butterfly.newyorktimesnews.interfaces.OnMainFragmentListener;
import com.butterfly.newyorktimesnews.restful.model.NewsModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Fatih Kaplan on 03/05/16.
 */
@EFragment(R.layout.news_list_fragment)
public class NewsListFragment extends BaseFragment {

    @FragmentArg
    NewsModel newsModel;

    ArrayList<NewsModel.Docs> docs;

    OnMainFragmentListener mainFragmentListener;

    @ViewById(R.id.newsListview)
    ListView newsListview;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainFragmentListener = (OnMainFragmentListener) context;
    }

    @AfterViews
    protected void afterViews() {
        if (newsModel != null) {
            if (!newsModel.response.docsArrayList.isEmpty()) {
                docs = newsModel.response.docsArrayList;
                NewsListAdapter adapter = new NewsListAdapter(getActivity(), docs);
                newsListview.setAdapter(adapter);
            }
        }
    }

    @ItemClick(R.id.newsListview)
    void itemClick(int position) {
        String url = docs.get(position).web_url;
        ((MainActivity) getActivity()).toNewsPreviewFragment(url);
    }


}
