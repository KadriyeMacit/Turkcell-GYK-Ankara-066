package com.example.gezginapp;

public class PostModel {

    // kullanılacak resim ve yazılar için değişkenler seçildi
    int postPicture;
    String postName;
    String postDescription;


    // boş constructor,
    public PostModel() {
    }

    // 3lü parametre kullanabilmek için tanımlanan constructor,
    public PostModel(int postPicture, String postName, String postDescription) {

        this.postPicture = postPicture;
        this.postName = postName;
        this.postDescription = postDescription;
    }


    // get ve set methodlarını, constructor ları
    // sayfada boş bir yere sağ tıklayıp
    // Generate -> Getter and Setter  seçerek yapabilirsiniz.


    public int getPostPicture() {
        return postPicture;
    }

    public void setPostPicture(int postPicture) {
        this.postPicture = postPicture;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }
}
