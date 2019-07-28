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

import nju.androidchat.client.R;
import nju.androidchat.client.Utils;

public class ItemTextReceive extends LinearLayout {


    @StyleableRes
    int index0 = 0;

    private TextView textView;
    private ImageView imageView;
    private Context context;
    private UUID messageId;
    private OnRecallMessageRequested onRecallMessageRequested;


    public ItemTextReceive(Context context, String text, UUID messageId) {
        super(context);
        this.context = context;
        this.messageId = messageId;
        if(Utils.isPicUrl(text)){
            inflate(context, R.layout.item_img_receive, this);
            imageView=findViewById(R.id.image_view);
            Glide.with(context).load(Utils.getMarkdownPicUrl(text)).into(imageView);
        }else {
            inflate(context, R.layout.item_text_receive, this);
            this.textView = findViewById(R.id.chat_item_content_text);
            setText(text);
        }
    }

    public void init(Context context) {

    }

    public String getText() {
        return textView.getText().toString();
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
