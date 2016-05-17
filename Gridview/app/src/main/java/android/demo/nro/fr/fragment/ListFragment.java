package android.demo.nro.fr.fragment;


import android.content.Context;
import android.demo.nro.fr.OnFragmentInteractionListener;
import android.demo.nro.fr.activity.AndroidVersion;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.demo.nro.fr.gridview.R;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.ref.WeakReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private AndroidVersion[] androidVersions;


    private WeakReference<OnFragmentInteractionListener> mListener;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(AndroidVersion[] androidVersions ) {
        ListFragment fragment = new ListFragment();
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
        return inflater.inflate(R.layout.fragment_list, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(this.androidVersions!=null) {
            final ListView listView = (ListView) this.getView().findViewById(R.id.listview);
            listView.setAdapter(new ArrayAdapter<AndroidVersion>(this.getActivity(), android.R.layout.simple_list_item_1, this.androidVersions));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final AndroidVersion androidVersion = androidVersions[position];
                    ListFragment.this.mListener.get().onSelectedItem(androidVersion);
                }
            });

        }
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
