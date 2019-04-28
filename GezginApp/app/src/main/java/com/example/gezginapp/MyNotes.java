package com.example.gezginapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyNotes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyNotes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyNotes extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<String> arrayList = new ArrayList<String>();
    String myPlaces;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    private ProgressDialog progressDialog;

    private OnFragmentInteractionListener mListener;

    public MyNotes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyNotes.
     */
    // TODO: Rename and change types and number of parameters
    public static MyNotes newInstance(String param1, String param2) {
        MyNotes fragment = new MyNotes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        arrayList = getMyNotes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Layout kısmını tanımlıyoruz
        View myNotesView = inflater.inflate(R.layout.fragment_my_notes,
                container, false);

        Button addNotesBtn = (Button) myNotesView
                .findViewById(R.id.fragment_add_notes_btn);

        // ekle butonuna basınca ekleme işleminin yapıldığı
        // activitiye yönlendirme yapılıyor
        addNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNoteIntent = new Intent(getContext(),
                        AddNoteActivity.class);
                startActivity(addNoteIntent);
            }
        });

        listView = (ListView) myNotesView.findViewById(R.id.my_notes_lv);

        arrayAdapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_list_item_1,
                        android.R.id.text1, arrayList);
        listView.setAdapter(arrayAdapter);

        return myNotesView;

    }


    private ArrayList<String> getMyNotes() {
        // aşağıda tanımladığımız fonksiyonu çağırıyoruz
        showProgressDialog();

        final ArrayList<String> myNotes = new ArrayList<>();

        // Firebase'in nesnelerini kullanabilmek için tanımlıyoruz.
        FirebaseDatabase database = FirebaseDatabase.getInstance();


        final DatabaseReference myRef =
                database.getReference().child("GezdigimYerler");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    myPlaces = ds.child("sehirAdi").getValue().toString();
                    myNotes.add(myPlaces);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                progressDialog.dismiss();
            }
        });

        return myNotes;

    }


    private void showProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Yükleniyor...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
