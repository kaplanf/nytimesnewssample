package com.butterfly.newyorktimesnews.restful.client;

import com.butterfly.newyorktimesnews.restful.model.NewsModel;
import com.butterfly.newyorktimesnews.util.NetworkConstants;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.RestClientHeaders;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;


/**
 * Created by Fatih Kaplan on 02/05/16.
 */
@Rest(rootUrl = NetworkConstants.BASE_URL, converters = {GsonHttpMessageConverter.class, StringHttpMessageConverter.class})
public interface RestClient extends RestClientHeaders {

    @Get(NetworkConstants.SEARCH_ARTICLE)
    NewsModel getArticles(@Path String news_desk, @Path String api_key);

}
