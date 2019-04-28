package com.example.gezginapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Profilim.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Profilim#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profilim extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    // xml dosyasında kullandıklarımızı tanımlıyoruz.
    ImageView profilePhoto;
    TextView profileName;
    TextView profileBio;
    ImageView profileUserInstagram;

    private OnFragmentInteractionListener mListener;

    public Profilim() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profilim.
     */
    // TODO: Rename and change types and number of parameters
    public static Profilim newInstance(String param1, String param2) {
        Profilim fragment = new Profilim();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // return kısmını en alta alıp layoutundaki çeviriyoruz.
        View profileView = inflater.inflate(R.layout.fragment_profilim,
                container,false);

        // idlerini atıyoruz işlem yapabilmek için
        profilePhoto = (ImageView) profileView.findViewById(R.id.profile_photo);
        profileName = (TextView) profileView.findViewById(R.id.profile_name);
        profileBio = (TextView) profileView.findViewById(R.id.profile_bio);
        profileUserInstagram = (ImageView) profileView.findViewById(R.id.profile_user_instagram);

        Button degistirmeButonu = (Button) profileView.findViewById(R.id.degistir);

        degistirmeButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddPhotoActivity.class);
                startActivity(intent);
            }
        });



        // resme tıklama özelliği veriyoruz.
        profileUserInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // aşağıdaki fonksiyonu çağırıyoruz.
                openInstagram();
            }
        });

        return profileView;
    }

    // yukarıda çağrılan fonksiyonu tanımlıyoruz.
    private void openInstagram() {

        // instagram için bir site adresi tanımlıyoruz
        // Uri kütüphanesinden yararlanarak bir değişkene atıyoruz bu adresi
        Uri instagramUri = Uri.parse("https://www.instagram.com/kadriye.macit");
        // adrese geçişini sağlar.
        Intent instagramIntent = new Intent(Intent.ACTION_VIEW, instagramUri);

        instagramIntent.setPackage("com.instagram.android");

        try {
            // hatasız bir şekilde olursa instagram açılır
            startActivity(instagramIntent);
        }
        catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/kadriye.macit")));
        }

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
