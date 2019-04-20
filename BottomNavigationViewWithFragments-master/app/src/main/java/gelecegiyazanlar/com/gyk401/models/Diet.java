package gelecegiyazanlar.com.gyk401.models;

import android.widget.ImageView;

public class Diet {
    int foodPicture;
    String foodName;
    String totalCalorie;

    public Diet(int foodPicture, String foodName, String totalCalorie) {

        this.foodPicture = foodPicture;
        this.foodName = foodName;
        this.totalCalorie = totalCalorie;
    }

    public int getFoodPicture() {
        return foodPicture;
    }

    public void setFoodPicture(int foodPicture) {
        this.foodPicture = foodPicture;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getTotalCalorie() {
        return totalCalorie;
    }

    public void setTotalCalorie(String totalCalorie) {
        this.totalCalorie = totalCalorie;
    }


}
