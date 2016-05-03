package com.butterfly.newyorktimesnews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.butterfly.newyorktimesnews.NewsApplication;
import com.butterfly.newyorktimesnews.R;
import com.butterfly.newyorktimesnews.restful.model.NewsModel;
import com.butterfly.newyorktimesnews.util.NetworkConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Fatih Kaplan on 03/05/16.
 */
public class NewsListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<NewsModel.Docs> docsArrayList;
    private Context mContext;

    public NewsListAdapter(Context context, ArrayList<NewsModel.Docs> docs) {
        mContext = context;
        docsArrayList = docs;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return docsArrayList.size();
    }

    @Override
    public NewsModel.Docs getItem(int position) {
        return docsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View vi = view;
        ViewHolder holder = null;
        if (vi == null) {
            vi = inflater.inflate(R.layout.news_list_item, parent, false);
            holder = new ViewHolder();

            holder.title = (TextView) vi.findViewById(R.id.news_item_title);
            holder.description = (TextView) vi.findViewById(R.id.news_item_description);
            holder.image = (ImageView) vi.findViewById(R.id.news_item_thumbnail);


            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        final NewsModel.Docs docs = getItem(position);

        if (docs != null) {

            holder.title.setText(docs.headline.main);
            holder.description.setText(docs.snippet);
            int thumbnailIndex = 999;
            if (docs.multimedias.length > 0) {
                {
                    int size = docs.multimedias.length;
                    for (int a = 0; a < size; a++) {
                        if (docs.multimedias[a].subtype.equals("thumbnail")) {
                            thumbnailIndex = a;
                        }
                    }
                }
                if (thumbnailIndex != 999) {
                    Picasso.with(mContext).load(NetworkConstants.NY_TIMES_BASE + docs.multimedias[thumbnailIndex].url).into(holder.image);
                    NewsApplication.getInstance().p.with(mContext).
                            load(NetworkConstants.NY_TIMES_BASE + docs.multimedias[thumbnailIndex].url).into(holder.image);
                }
            } else {
                holder.image.setImageResource(R.drawable.no_image);

            }
        }
        return vi;
    }


    static class ViewHolder {

        TextView title;

        TextView description;

        ImageView image;
    }
}
