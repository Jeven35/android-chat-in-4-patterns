package nju.androidchat.client.component;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.StyleableRes;

import com.bumptech.glide.Glide;

import java.util.UUID;

import lombok.Setter;
import nju.androidchat.client.R;
import nju.androidchat.client.Utils;

public class ItemTextSend extends LinearLayout implements View.OnLongClickListener {
    @StyleableRes
    int index0 = 0;

    private TextView textView;
    private ImageView imageView;
    private Context context;
    private UUID messageId;
    @Setter private OnRecallMessageRequested onRecallMessageRequested;

    public ItemTextSend(Context context, String text, UUID messageId, OnRecallMessageRequested onRecallMessageRequested) {
        super(context);
        this.context = context;
        this.messageId = messageId;
        this.onRecallMessageRequested = onRecallMessageRequested;

        if(Utils.isPicUrl(text)){
            inflate(context, R.layout.item_img_send, this);
            imageView=findViewById(R.id.image_view);
            Glide.with(context).load(Utils.getMarkdownPicUrl(text)).into(imageView);
        }else{
            inflate(context, R.layout.item_text_send, this);
            this.textView = findViewById(R.id.chat_item_content_text);
            setText(text);
        }
        this.setOnLongClickListener(this);
    }

    public String getText() {
        return textView.getText().toString();
    }

    public void setText(String text) {
        textView.setText(text);
    }

    @Override
    public boolean onLongClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确定要撤回这条消息吗？")
                .setPositiveButton("是", (dialog, which) -> {
                    if (onRecallMessageRequested != null) {
                        onRecallMessageRequested.onRecallMessageRequested(this.messageId);
                    }
                })
                .setNegativeButton("否", ((dialog, which) -> {
                }))
                .create()
                .show();

        return true;


    }

}
