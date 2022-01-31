package com.example.practicewithrecyclerviewpicaassoandvolley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Recycker_Row extends RecyclerView.Adapter<Recycker_Row.RecyclerViewHolder> {

    private Context context;
    private ArrayList<EachItemAttributes> eachItemAttributes;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public Recycker_Row(Context ctx, ArrayList<EachItemAttributes> eachItemAttributes){
        context = ctx;
        this.eachItemAttributes = eachItemAttributes;
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView nameView;
        private TextView likesView;
        private ImageView imageView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.show_name);
            likesView = itemView.findViewById(R.id.show_likes);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener  != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }


    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_row_layouts, parent, false);

        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        EachItemAttributes item = eachItemAttributes.get(position);
        String catNAme = item.getName_cat();
        String catID = item.getImage_ID();
        int catLikes = item.getNumber_likes();

        holder.nameView.setText(catNAme);
        holder.likesView.setText("Likes: " + catLikes);
        Picasso.with(context).load(catID).fit().centerInside().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
         return eachItemAttributes.size();
    }
}
