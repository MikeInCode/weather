package mike.weather.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mike.weather.R;
import mike.weather.data.model.City;
import mike.weather.ui.base.OnItemClickListener;

public class MainCitiesAdapter extends RecyclerView.Adapter<MainCitiesAdapter.ViewHolder> {

    private List<City> citiesList;
    private OnItemClickListener onItemClickListener;

    @Inject
    public MainCitiesAdapter() {
        citiesList = new ArrayList<>();
    }

    public void setCitiesList(List<City> citiesList) {
        this.citiesList = citiesList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.city_name)
        TextView cityName;
        @BindView(R.id.country_name)
        TextView countryName;
        @BindView(R.id.temperature)
        TextView temperature;
        @BindView(R.id.weather_icon)
        ImageView weatherIcon;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public MainCitiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_cities_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        City city = citiesList.get(position);
        holder.cityName.setText(city.getCityName());
        holder.countryName.setText(city.getCountryName());
        holder.temperature.setText(city.getTemp());
        holder.weatherIcon.setImageResource(city.getIcon());
        holder.itemView.setOnClickListener(l -> onItemClickListener.onItemClick(city));
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    public void removeItem(int position) {
        citiesList.remove(position);
        notifyItemRemoved(position);
    }

    public City getItemAtPosition(int position) {
        return citiesList.get(position);
    }
}
