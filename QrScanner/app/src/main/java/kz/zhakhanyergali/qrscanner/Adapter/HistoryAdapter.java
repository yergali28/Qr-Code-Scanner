package kz.zhakhanyergali.qrscanner.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import kz.zhakhanyergali.qrscanner.Activity.MainActivity;
import kz.zhakhanyergali.qrscanner.R;

import java.util.List;

import kz.zhakhanyergali.qrscanner.Entity.History;

/**
 * Created by zhakhanyergali on 08.11.17.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    // Variables
    private List<History> historyList;
    private Context context;

    public HistoryAdapter(List<History> historyList) {
        this.historyList = historyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = null;

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.context.setText((position + 1) + ". " + historyList.get(position).getContext());
        holder.date.setText(historyList.get(position).getDate());
        holder.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/#q=" + historyList.get(position).getContext()));
                context.startActivity(intent);
            }
        });
        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", historyList.get(position).getContext());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = historyList.get(position).getContext();
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(sharingIntent, "Share "));

            }
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView context, date;
        public ImageView search, copy, share;

        public ViewHolder(View itemView) {
            super(itemView);
            context = (TextView) itemView.findViewById(R.id.contextTextView);
            date = (TextView) itemView.findViewById(R.id.dateTextView);
            search = (ImageView) itemView.findViewById(R.id.searchImageView);
            copy = (ImageView) itemView.findViewById(R.id.copyImageView);
            share = (ImageView) itemView.findViewById(R.id.shareImageView);
        }
    }

}
