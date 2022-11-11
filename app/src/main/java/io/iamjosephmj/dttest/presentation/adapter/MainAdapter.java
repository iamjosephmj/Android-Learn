package io.iamjosephmj.dttest.presentation.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import io.iamjosephmj.dttest.R;
import io.iamjosephmj.dttest.domain.Feed;
import io.iamjosephmj.dttest.domain.FeedsResponse;

// Not seeing a benefit for using adapter delegation.
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private FeedsResponse driverList;

    public MainAdapter(FeedsResponse driverList) {
        this.driverList = driverList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(FeedsResponse driverList) {
        this.driverList = driverList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView image;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.title);
            image = (ImageView) view.findViewById(R.id.image);
        }

        public TextView getTitle() {
            return textView;
        }

        public ImageView getImage() {
            return image;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_feed, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Feed feed = driverList.getFeeds().get(position);
        viewHolder.getTitle().setText(feed.getTitle());
        Picasso.get()
                .load(feed.getImage())
                .into(viewHolder.getImage());
    }

    @Override
    public int getItemCount() {
        return driverList.getFeeds().size();
    }
}
