package com.bahaa.mvvm.ui.fragment.adapter.movieAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bahaa.mvvm.R;
import com.bahaa.mvvm.databinding.ItemMovieBinding;
import com.bahaa.mvvm.models.Movie;
import com.bahaa.mvvm.util.AppTools;

import java.util.ArrayList;
import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> cartList;
    private OnMovieClick onMovieClick;

    public MovieAdapter(OnMovieClick onMovieClick) {
        this.cartList = new ArrayList<>();
        this.onMovieClick = onMovieClick;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding itemCartBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movie,
                        parent, false);
        return new ViewHolder(itemCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindPeople(cartList.get(position));
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }


    public void setCartList(List<Movie> cartList) {
        this.cartList = new ArrayList<>();
        this.cartList = cartList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemMovieBinding myitemMovieBinding;

        public ViewHolder(ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            this.myitemMovieBinding = itemMovieBinding;
        }

        void bindPeople(final Movie movie) {
            MovieViewModel movieViewModel = new MovieViewModel(movie);
            myitemMovieBinding.setMovieItem(movieViewModel);
            movieViewModel.action.observe((LifecycleOwner) itemView.getContext(), action -> {
                switch (action) {
                    case AppTools.OPEN_MOVIE:
                        onMovieClick.onMovieClickListener(movie);
                        break;
                }
            });

        }
    }

    public interface OnMovieClick {
        void onMovieClickListener(Movie movie);
    }
}
