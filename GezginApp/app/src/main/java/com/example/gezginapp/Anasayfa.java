package com.example.gezginapp;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Anasayfa.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Anasayfa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Anasayfa extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // PostModel sınıfından bir arrayList oluşturuyoruz.
    List<PostModel> postList = new ArrayList<PostModel>();






    private OnFragmentInteractionListener mListener;

    public Anasayfa() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Anasayfa.
     */
    // TODO: Rename and change types and number of parameters
    public static Anasayfa newInstance(String param1, String param2) {
        Anasayfa fragment = new Anasayfa();
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

        View view = inflater.inflate(R.layout.fragment_anasayfa,container,false);

        // xml'deki ListView'e id atıyoruz işlem yapabilmek için.
        ListView postListView = (ListView) view.findViewById(R.id.post_list);

        // yukarıda PostModel'den oluşturduğumuz listeye
        // elemanlar ekliyoruz.
        postList.add(new PostModel(R.drawable.foto1,"Trabzon",
                "Karadeniz'in incisi olarak tabir edilen, eşsiz doğasıyla nefes kesen bir şehir Trabzon."));
        postList.add(new PostModel(R.drawable.foto2,"Mardin",
                "Dicle ve Fırat nehirleri arasında yer alan Mardin Güneydoğu Anadolu Bölgesi'nin en çok merak edilen şehirlerinden biridir."));
        postList.add(new PostModel(R.drawable.foto3,"İzmir",
                "ürkiye'nin batısında, Ege Denizi'nin kıyısında yer alan Ege'nin İncisi İzmir, Türkiye'nin 3'üncü büyük kentidir."));
        postList.add(new PostModel(R.drawable.foto4,"İstanbul",
                "Avrupa ve Asya'yı birbirine bağlayan, çok sayıda medeniyetin izlerini taşıyan istanbul"));


        // adapter tanımlıyoruz listView'i kullanabilmek için
        CustomPostAdapter customPostAdapter =
                new CustomPostAdapter(getLayoutInflater(), postList);
        postListView.setAdapter(customPostAdapter);


        // listView'deki itemlere tıklama özelliği veriyoruz.
        postListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // tıklandığında uyarı ekranı çıkmasını tanımlıyoruz
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Bilgiler");  //başlığını tanımlıyoruz

                // pozisyonuna göre seçilen itemin adını atıyoruz.
                String selectedName = postList.get(position).getPostName();

                // pozisyonuna göre seçilen itemin açıklamasını atıyoruz.
                String selectedDescription = postList.get(position).getPostDescription();

                // pozisyonuna göre seçilen itemin ikonunu atıyoruz.
                int selectedIcon = postList.get(position).getPostPicture();

                String message = selectedName + " " + selectedDescription;
                builder.setIcon(selectedIcon);
                builder.setMessage(message);
                builder.setNegativeButton("TAMAM",
                        new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.show();
            }
        });
        return view;
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
