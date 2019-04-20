package com.example.gezginapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomPostAdapter extends BaseAdapter {

    // layout dosyalarındaki elemanları java kodunda yazabilmek için tanımlıyoruz.
    LayoutInflater layoutInflater;

    // PostModel sınıfından bir liste tanımlıyoruz
    List<PostModel> postModelList;

    // 2 parametreleri constructor tanımlıyoruz.
    public CustomPostAdapter(LayoutInflater layoutInflater, List<PostModel> postModelList) {
        this.layoutInflater = layoutInflater;
        this.postModelList = postModelList;
    }


    // extends BaseAdapter yaptığımız için aşağıdaki 4 temel fonksiyonu yazmak zorundayız.

    @Override
    public int getCount() {
        return postModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return postModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // tasarım yaptığımız xml dosyasını tanımlarız.
        View postView = layoutInflater.inflate(R.layout.post_list,null);

        // tasarım yaptığımız xml'deki elemanların idlerini yazıyoruz.
        ImageView postPicture = postView.findViewById(R.id.post_picture);
        TextView postTitle = postView.findViewById(R.id.post_title);
        TextView postDescription = postView.findViewById(R.id.post_description);

        // PostModeldeki değişkenlerimizi tasarımdaki yerlere ekliyoruz.
        PostModel postModel = postModelList.get(position);
        postPicture.setImageResource(postModel.getPostPicture());
        postTitle.setText(postModel.getPostName());
        postDescription.setText(postModel.getPostDescription());

        return postView;
    }
}
