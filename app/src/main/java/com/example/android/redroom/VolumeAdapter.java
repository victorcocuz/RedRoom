package com.example.android.redroom;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by victo on 7/2/2017.
 */

class VolumeAdapter extends RecyclerView.Adapter<VolumeAdapter.ViewHolder> {

    private ArrayList<VolumeCard> mVolumes;

    public VolumeAdapter(ArrayList<VolumeCard> mVolumes) {
        this.mVolumes = mVolumes;
    }

    @Override
    public VolumeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.volume_card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(VolumeAdapter.ViewHolder holder, int position) {
        holder.mTitle.setText(mVolumes.get(position).getVolumeTitle());
        holder.mAuthor.setText(mVolumes.get(position).getVolumeAuthor());

    }

    @Override
    public int getItemCount() {
        return mVolumes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle;
        public TextView mAuthor;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.volume_card_title);
            mAuthor = (TextView) itemView.findViewById(R.id.volume_card_author);
        }
    }
}
