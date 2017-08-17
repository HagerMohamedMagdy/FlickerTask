package data;

/**
 * Created by Hager.Magdy on 8/16/2017.
 */

public interface APIListener {
    public void onSuccess(int id, String url, String response);
    public void onFailure(int id, String url, String response, int responseCode);

}
