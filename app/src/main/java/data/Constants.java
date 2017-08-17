package data;

/**
 * Created by Hager.Magdy on 8/16/2017.
 */
import okhttp3.OkHttpClient;
public class Constants {
    public static OkHttpClient.Builder httpClient = null;
    public static final String FLICKR_BASE_URL = "http://api.flickr.com/services/rest/?method=";
    public static final String FLICKR_PHOTOS_SEARCH_STRING = "flickr.photos.search";
    public static final String METHOD_GET = "get";
    public static final String METHOD_POST = "post";
    public static final String GET_PHOTOS = "flickr.galleries.getPhotos";
}
