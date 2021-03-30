package com.example.foodproject.adapter;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodproject.GlobalVariable;
import com.example.foodproject.R;
import com.example.foodproject.model.AddProductModel;
import com.example.foodproject.model.RatingModel;
import com.example.foodproject.viewModel.ViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShowProductAdapter extends RecyclerView.Adapter<ShowProductAdapter.showProductViewHolder> {

    private List<AddProductModel> addProductModels;
    private Context context;


    public ShowProductAdapter(List<AddProductModel> addProductModels, Context context) {
        this.addProductModels = addProductModels;
        this.context = context;

    }


    @NonNull
    @Override
    public showProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);
        return new showProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull showProductViewHolder holder, int position) {
        holder.name.setText(addProductModels.get(position).getName());
        holder.price.setText("$ "+addProductModels.get(position).getPrice());
        Picasso.get().load(addProductModels.get(position).getImageurl()).into(holder.imageView);

        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {


                holder.rating.setText("5/" + rating);


            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("name", addProductModels.get(position).getName());
                bundle.putString("price", addProductModels.get(position).getPrice());
                bundle.putString("image",addProductModels.get(position).getImageurl());
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_productDeatils,bundle);
            }
        });



    }

    @Override
    public int getItemCount() {
        return addProductModels.size();
    }

    public class showProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name,price;
        private RatingBar ratingBar;
        private ImageView like,dislike;
        TextView rating;
        public showProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewId);
            name = itemView.findViewById(R.id.ProductNameId);
            price = itemView.findViewById(R.id.productPriceId);
            ratingBar = itemView.findViewById(R.id.ratingBraId);
            rating = itemView.findViewById(R.id.ratinTextId);

         /*   like = itemView.findViewById(R.id.likeId);
            dislike = itemView.findViewById(R.id.dislike);*/

        }
    }


}
