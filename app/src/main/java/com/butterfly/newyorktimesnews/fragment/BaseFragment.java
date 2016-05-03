package com.butterfly.newyorktimesnews.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.butterfly.newyorktimesnews.R;
import com.butterfly.newyorktimesnews.interfaces.OnMainFragmentListener;
import com.butterfly.newyorktimesnews.util.DialogManager;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

@EFragment
public abstract class BaseFragment extends Fragment {

    private Dialog progressDialog;
    private SharedPreferences preferences;
    private OnMainFragmentListener listener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnMainFragmentListener) context;
        } catch (ClassCastException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (listener != null) listener.onCloseFragment(this.getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listener != null) listener.onStartFragment(this.getClass().getSimpleName());
    }

    @UiThread
    protected void fragmentTransaction(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    @UiThread
    protected void showProgressDialog() {
        if (getActivity() != null) {
            if (progressDialog == null)
                progressDialog = DialogManager.getInstance().getProgressDialog(getActivity(), R.string.loading);
            progressDialog.show();
        }
    }

    @UiThread
    protected void hideProgressDialog() {
        if (progressDialog != null) progressDialog.dismiss();
    }


    protected boolean isFMVisibleFragment(String fragmentTag) {
        Fragment fragment = getFragmentManager().findFragmentByTag(fragmentTag);
        if (fragment != null && fragment.isVisible()) return true;
        else return false;
    }

    protected boolean isChildFMVisibleFragment(String fragmentTag) {
        Fragment fragment = getChildFragmentManager().findFragmentByTag(fragmentTag);
        if (fragment != null && fragment.isVisible()) return true;
        else return false;
    }

    protected SharedPreferences getPreferences() {
        if (preferences == null)
            preferences = getActivity().getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
        return preferences;
    }

}
