package com.butterfly.newyorktimesnews.fragment;

import android.content.Context;
import android.widget.TextView;

import com.butterfly.newyorktimesnews.MainActivity;
import com.butterfly.newyorktimesnews.NewsApplication;
import com.butterfly.newyorktimesnews.R;
import com.butterfly.newyorktimesnews.interfaces.OnMainFragmentListener;
import com.butterfly.newyorktimesnews.restful.client.RestClient;
import com.butterfly.newyorktimesnews.restful.model.NewsModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by Fatih Kaplan on 03/05/16.
 */
@EFragment(R.layout.main_fragment)
public class MainFragment extends BaseFragment {

    @RestService
    RestClient restClient;

    @ViewById(R.id.mainFragmentGatherNewsButton)
    TextView mainFragmentGatherNewsButton;

    NewsModel newsModel;

    OnMainFragmentListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnMainFragmentListener) context;
    }

    @AfterViews
    protected void afterViews() {
    }

    @Click(R.id.mainFragmentGatherNewsButton)
    void clickGather() {
        showProgressDialog();
        gatherNews();
    }

    @Background
    void gatherNews() {
        newsModel = restClient.getArticles("\"Sports\"", NewsApplication.getInstance().API_SEARCH_KEY);
        hideProgressDialog();
        ((MainActivity) getActivity()).toListFragment(newsModel);
    }
}
