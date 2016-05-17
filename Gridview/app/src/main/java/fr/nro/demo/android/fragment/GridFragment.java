package fr.nro.demo.android.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.lang.ref.WeakReference;

import fr.nro.demo.android.OnFragmentInteractionListener;
import fr.nro.demo.android.R;
import fr.nro.demo.android.adapter.GridViewAdapter;
import fr.nro.demo.android.model.AndroidVersion;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GridFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private AndroidVersion[] androidVersions;


    private WeakReference<OnFragmentInteractionListener> mListener;

    public GridFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param androidVersions Parameter 1.
     * @return A new instance of fragment GridFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GridFragment newInstance(AndroidVersion[] androidVersions) {
        GridFragment fragment = new GridFragment();
        Bundle args = new Bundle();
        args.putParcelableArray(ARG_PARAM1, androidVersions);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            androidVersions = (AndroidVersion[]) getArguments().getParcelableArray(ARG_PARAM1);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(this.androidVersions!=null) {
            final GridView gridView = (GridView) this.getView().findViewById(R.id.gridview);
            gridView.setAdapter(new GridViewAdapter(this.getActivity(), android.R.layout.simple_list_item_1, this.androidVersions));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final AndroidVersion androidVersion = androidVersions[position];
                    GridFragment.this.mListener.get().onSelectedItem(androidVersion);
                }
            });

        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    private void onAttachContext(Context context){
        if (context instanceof OnFragmentInteractionListener) {
            mListener = new WeakReference<OnFragmentInteractionListener>((OnFragmentInteractionListener) context);
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.onAttachContext(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
