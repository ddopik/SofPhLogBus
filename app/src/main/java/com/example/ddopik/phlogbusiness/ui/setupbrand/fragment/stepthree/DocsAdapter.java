package com.example.ddopik.phlogbusiness.ui.setupbrand.fragment.stepthree;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.ddopik.phlogbusiness.R;
import com.example.ddopik.phlogbusiness.ui.setupbrand.model.Doc;
import com.example.ddopik.phlogbusiness.ui.setupbrand.view.SetupBrandView;
import com.example.ddopik.phlogbusiness.ui.setupbrand.view.SetupBrandView.Communicator;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocsAdapter extends RecyclerView.Adapter<DocsAdapter.ViewHolderX> {

    private final List<Doc> list = new ArrayList<>();
    private final ActionListener actionListener;
    private final Map<Integer, BiConsumer<Doc, Communicator.Type>> consumers = new HashMap<>();

    public DocsAdapter(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public ViewHolderX onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.doc_card_layout, viewGroup, false);
        return new ViewHolderX(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderX holder, int i) {
        Doc doc = list.get(i);
        Context context = holder.itemView.getContext();
        if (doc.getSystemName() != null)
            holder.title.setText(doc.getSystemName());
        else holder.title.setText("");
        if (doc.getUploadedFile() != null && doc.getUploadedFile().getUrl() != null)
            Glide.with(context)
                    .load(doc.getUploadedFile().getUrl())
                    .into(holder.image);
        else if (doc.path != null)
            Glide.with(context)
                    .load(doc.path)
                    .into(holder.image);
        holder.upload.setVisibility(View.GONE);
        holder.check.setVisibility(View.GONE);
        Consumer<String> consumer = s -> {
            Glide.with(context)
                    .load(s)
                    .into(holder.image);
            holder.upload.setVisibility(View.VISIBLE);
        };
        holder.itemView.setOnClickListener(v -> {
            actionListener.accept(ActionListener.Type.SELECT, consumer, doc);
        });
        holder.upload.setOnClickListener(v -> {
            holder.upload.setVisibility(View.GONE);
            holder.check.setVisibility(View.GONE);
            actionListener.accept(ActionListener.Type.UPLOAD, doc);
        });
//        if (doc.progress == 100) {
//            holder.upload.setVisibility(View.GONE);
//            holder.check.setVisibility(View.VISIBLE);
//        } else if (doc.progress > 0) {
//            holder.upload.setVisibility(View.GONE);
//            holder.check.setVisibility(View.GONE);
//        }
//        holder.progress.setProgress(doc.progress);
        BiConsumer<Doc, Communicator.Type> biConsumer = (d, type) -> {
            doc.path = d.path;
            doc.progress = d.progress;
            Glide.with(context)
                    .load(doc.path)
                    .into(holder.image);
            switch (type) {
                case ERROR:
                    holder.upload.setVisibility(View.VISIBLE);
                    break;
                case PROGRESS:
                    holder.progress.setProgress(doc.progress);
                    break;
                case DONE:
                    holder.check.setVisibility(View.VISIBLE);
                    break;
            }
        };
        consumers.put(i, biConsumer);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Doc> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

//    public void setProgress(Doc model) {
//        int id = model.getId();
//        for (Doc m : list) {
//            if (m.getId() == id) {
//                m.progress = model.progress;
//                m.path = model.path;
//                int index = list.indexOf(m);
//                if (index != -1)
//                    notifyItemChanged(index);
//                break;
//            }
//        }
//    }

    public void updateView(Doc doc, Communicator.Type type) {
        int id = doc.getId();
        for (Doc d : list) {
            if (d.getId() == id) {
                int index = list.indexOf(d);
                try {
                    consumers.get(index).accept(doc, type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public class ViewHolderX extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image, check;
        Button upload;
        ProgressBar progress;

        public ViewHolderX(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
            check = itemView.findViewById(R.id.check);
            upload = itemView.findViewById(R.id.upload);
            progress = itemView.findViewById(R.id.progress);
        }
    }

    public interface ActionListener {

        void accept(Type type, Object... objects);

        enum Type {
            SELECT, UPLOAD
        }
    }
}
