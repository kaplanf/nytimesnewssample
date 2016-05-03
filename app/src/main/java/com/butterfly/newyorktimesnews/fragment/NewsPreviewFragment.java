package com.butterfly.newyorktimesnews.fragment;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.butterfly.newyorktimesnews.R;
import com.butterfly.newyorktimesnews.interfaces.OnMainFragmentListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Fatih Kaplan on 03/05/16.
 */
@EFragment(R.layout.news_preview_fragment)
public class NewsPreviewFragment extends BaseFragment {

    @FragmentArg
    String url;

    OnMainFragmentListener mainFragmentListener;

    @ViewById(R.id.newsPreviewWebview)
    WebView newsPreviewWebview;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainFragmentListener = (OnMainFragmentListener) context;
    }

    @AfterViews
    protected void afterViews() {
        newsPreviewWebview.getSettings().setJavaScriptEnabled(true);
        showProgressDialog();
        extractHtml(url);
    }


    @Background
    void extractHtml(String link) {
        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection con = null;
        try {
            con = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
        Matcher m = p.matcher(con.getContentType());


        String charset = m.matches() ? m.group(1) : "ISO-8859-1";
        Reader r = null;
        try {
            r = new InputStreamReader(con.getInputStream(), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder buf = new StringBuilder();
        while (true) {
            int ch = 0;
            try {
                ch = r.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (ch < 0)
                break;
            buf.append((char) ch);
        }
        String str = buf.toString();
        loadWebview(str);
    }

    @UiThread
    void loadWebview(String data) {
        newsPreviewWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
        newsPreviewWebview.loadData(data, "text/html; charset=UTF-8", null);
        hideProgressDialog();
    }

}
