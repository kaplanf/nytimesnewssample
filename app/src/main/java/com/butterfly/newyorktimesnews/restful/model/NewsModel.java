package com.butterfly.newyorktimesnews.restful.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Fatih Kaplan on 03/05/16.
 */
public class NewsModel implements Serializable {

    @SerializedName("response")
    public Response response;


    public class Response implements Serializable {
        @SerializedName("docs")
        public ArrayList<Docs> docsArrayList;
    }

    public class Docs implements Serializable {

        @SerializedName("_id")
        public String _id;

        @SerializedName("web_url")
        public String web_url;

        @SerializedName("snippet")
        public String snippet;

        @SerializedName("subsection_name")
        public String subsection_name;

        @SerializedName("multimedia")
        public Multimedia[] multimedias;

        @SerializedName("headline")
        public Headline headline;


    }

    public class Multimedia implements Serializable {
        @SerializedName("width")
        public int width;

        @SerializedName("height")
        public int height;

        @SerializedName("url")
        public String url;

        @SerializedName("subtype")
        public String subtype;

    }

    public class Headline implements Serializable {

        @SerializedName("main")
        public String main;
    }
}
