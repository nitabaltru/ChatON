package nitabaltru.tp2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by nitabaltru on 27/11/2017.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private List<Message> datas;

    public MessageAdapter(List<Message> datas) {

        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView1;
        private final TextView textView2;
        private final TextView textView3;
        private final ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.userTextView);
            textView2 = itemView.findViewById(R.id.contentTextView);
            textView3 = itemView.findViewById(R.id.dateTextView);
            imageView = itemView.findViewById(R.id.userImageView);


        }

        public void setData(Message data) {
            textView1.setText(data.getUserName());
            textView2.setText(data.getContent());
            Timestamp timestamp = new Timestamp(data.getTimestamp());
            Date date = new Date(timestamp.getTime());
            textView3.setText(DateFormat.getInstance().format(date));
            if( data.getUserEmail() == null) {
                Glide
                        .with(imageView.getContext())
                        .load(Utils.GRAVATAR_PREFIX)
                        .apply(RequestOptions.circleCropTransform())
                        .into(imageView);
            } else {
                Glide
                        .with(imageView.getContext())
                        .load(Utils.GRAVATAR_PREFIX + Utils.MD5(data.getUserEmail()) + ".jpg")
                        .apply(RequestOptions.circleCropTransform())
                        .into(imageView);
            }
        }
    }

    public void setData(List<Message> datas){
        this.datas = datas;
        this.notifyDataSetChanged();
    }
}