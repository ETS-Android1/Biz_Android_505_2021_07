package com.callor.cacao.adpter;


import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.cacao.R;
import com.callor.cacao.model.Chatt;

import java.util.List;


/**
 * RecyclerView.Adapter 구현한 클래스
 * RecyvlerView에 데이터를 표현하고자 할때 사용하는
 *      Helper 클래스(도와주는 도구 클래스)
 */
public class ChattAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<Chatt> chattList;
    private String name;

    /**
     * 외부에서 chatt 데이터 아이템을 List를 추가하고
     * 추가된 List는 RecyclerView를 통해서
     *      화면에 다시 그려지게 될 것이다
     * @param chatt
     */
    public void addChatList(Chatt chatt) {

        // 메시지를 리스트에 추가하기
        chattList.add(chatt);

        // RecyclerView에게 chattList가 변화 되었으니
        // 다시 화면에 그려라 라고 지시하기
        // chattList의 끝(size() - 1위치에 값이 추가 되었으니)에
        // 값이 추가되었으니 다시 그려라
        notifyItemInserted(chattList.size() - 1);

    }


    /**
     * RecyclerView가 화면에 그릴 데이터들을 전달받을 통로
     * @param chattList
     */
    public ChattAdapter(List<Chatt> chattList) {
        // this.chattList = chattList;
        this(chattList,"익명");
    }
    public ChattAdapter(List<Chatt> chattList, String name) {
        this.chattList = chattList;
        this.name = name;
    }

    /**
     * chat_item.xml파일을 읽어서 한개의 아이템을 화면에 그릴 준비
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /**
         * LayoutInflater.from().inflate(chatt_item)
         *
         * chatt_item.xml 파일은 한개의 파일이 화면 전체를 구성하지 않고
         * 여기에서는 RecyclerView 내부에 각각 데이터 아이템을 그릴
         * 도구로 사용이 된다
         * 이러한 layout은 setContentView()를 사용하기 않고
         * layoutInfalter.infalte() 함수를 사용하여 만든다
         *
         */
        View item_layout
            = LayoutInflater.from( parent.getContext() )
            .inflate(R.layout.chatt_item,parent,false);

        ChattViewHolder viewHolder
                = new ChattViewHolder(item_layout);
        return viewHolder;
    }

    // chattList에서 한개의 데이터를 getter하여
    // chat_item.xml파일과 함께 rendering을 수행할 method
    @Override
    public void onBindViewHolder(@NonNull
            RecyclerView.ViewHolder holder, int position) {

        // 전체 chattList에서 현재 화면에 그릴 item추출하기
        Chatt chat = chattList.get(position);
        ChattViewHolder chattViewHolder
                = (ChattViewHolder) holder;

        // chat_item.xml 의 TextView 객체에 데이터를 담기
        chattViewHolder.item_name.setText(chat.getName());
        chattViewHolder.item_msg.setText(chat.getMsg());

        /**
         * 현재 App에서 보낸 메시지를 DB에서 가져왔으면(Fetch)
         * this.name 변수에는 App에 설정된 nickname이 담겨 있다
         * 그리고 firebase에서 가져온 데이터에서 이름이 nickname과 같으면
         * 오른쪽 정렬하여 보여라
         */
        if(this.name.equals(chat.getName())) {
            // 이름과 메시지를 오른쪽 정렬
            chattViewHolder.item_name.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            chattViewHolder.item_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            chattViewHolder.msgLinear.setGravity(Gravity.END);
            chattViewHolder.item_msg.setBackgroundColor(
                    Color.parseColor("#FFEB3B")
            );
        }
    }

    /**
     * RecyclerView가 데이터들을 화면에 표시할때
     * 참조하는 함수
     * @return
     */
    @Override
    public int getItemCount() {
        return chattList == null ? 0 : chattList.size();
    }

    // class 내부에 in class
    public static class ChattViewHolder
            extends RecyclerView.ViewHolder{

        public TextView item_name;
        public TextView item_msg;
        public LinearLayout msgLinear;

        // 각 데이터를 표현하기 위한
        // chat_item.xml의 view 객체(두개의 TextView) 를
        // 초기화(생성) 하는 method
        public ChattViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name);
            item_msg = itemView.findViewById(R.id.item_msg);
            /**
             * item_name과 item_msg를 감싸고 있는 layout(LinearLayout) 에 접근하기 위하여
             * 객체로 생성
             */
            msgLinear = itemView.findViewById(R.id.msg_linear);
        }
    }

}
