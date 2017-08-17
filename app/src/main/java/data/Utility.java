package data;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import static android.R.attr.x;

/**
 * Created by Hager.Magdy on 8/16/2017.
 */

public class Utility {
    private static ProgressDialog mProgressDialog;
    public static void showProgressDialog(Context context,
                                          String msg) {
        if(mProgressDialog==null||!mProgressDialog.isShowing()){
            mProgressDialog = ProgressDialog.show(context, "", msg);
            mProgressDialog.setCancelable(false);

        }else {

        }
    }

    public static void removeProgressDialog() {


        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;

                }

            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
