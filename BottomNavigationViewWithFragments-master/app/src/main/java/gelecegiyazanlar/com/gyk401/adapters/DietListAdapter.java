package gelecegiyazanlar.com.gyk401.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gelecegiyazanlar.com.gyk401.R;
import gelecegiyazanlar.com.gyk401.models.Diet;

public class DietListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<Diet> dietList;

    public DietListAdapter(LayoutInflater layoutInflater, List<Diet> dietList) {
        this.layoutInflater = layoutInflater;
        this.dietList = dietList;
    }

    @Override
    public int getCount() {
        return dietList.size();
    }

    @Override
    public Object getItem(int i) {
        return dietList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View dietView = layoutInflater.inflate(R.layout.custom_diet_layout,null);
        ImageView foodPicture = (ImageView) dietView.findViewById(R.id.custom_diet_food_picture);
        TextView foodName = (TextView) dietView.findViewById(R.id.custom_diet_food_name);
        TextView foodCalorie = (TextView) dietView.findViewById(R.id.custom_diet_food_calorie);

        Diet diet = dietList.get(i);
        foodPicture.setImageResource(diet.getFoodPicture());
        foodName.setText(diet.getFoodName());
        foodCalorie.setText(diet.getTotalCalorie());

        return dietView;
    }
}
