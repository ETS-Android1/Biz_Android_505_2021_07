package com.callor.library.adapter;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.library.R;
import com.callor.library.model.NaverBookDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * RecyclerView에 데이터를 보여주기 위한 Helper Class
 */
public class BookViewAdapter extends RecyclerView.Adapter {

    // RecylerView에 표현할 데이터 들
    private List<NaverBookDTO> bookList;

    /**
     * RecylerView에서 사용할 데이터(bookList)를 어떻게 외부에서 Adapter에
     * 보낼 것인가?
     * 생성자, setter()를 사용하여 주입한다
     *
     * RecylerView의 Adapter를 객체로 생성할때
     * 화면에 보여줄 데이터를 전달하자(주입하자)
     * 데이터를 매개변수가 갖는 생성자를 만든다
     *
     * 이후의 코드는 화면에 보여줄 데이터가 있다는 전제하에
     * 코드가 진행된다.
     */
    public BookViewAdapter(List<NaverBookDTO> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    /**
     * onCreateViewHolder
     * item을 그리는 item.xml 파일을 읽어서 사용할 준비를 하기
     * item.xml 파일을 view로 생성하고 데이터와 연결할 준비
     */
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //  inflater(확장, 펼치기)
        // *.xml 파일에 설정된 view 정보를 읽어서 Java Code에서 사용하기 위한
        // 객체로 생성하는 것

        // item.xml 파일을 읽어와서 Holder로 만들기 전에 확장(펼치기) 위한 도구
        LayoutInflater layoutInflater
                = LayoutInflater.from(parent.getContext());

        // book_item_view.xml 내용이 view 객체로 변환되었다
        View view = layoutInflater.inflate(
                R.layout.book_item_view,
                parent,
                false  );

        // 생성된 view 객체를 BookViewHolder 클래스의 생성자에 주입하면서
        // ViewHolder 객체를 생성하기
        BookItemHolder viewHolder = new BookItemHolder(view);

        // 생성된 viiewHolder 객체를 RecyclerView에게 return하기
        return viewHolder;
    }

    @Override
    /**
     * 생성된 Holder와 실제 데이터(한개의 데이터)를 연결하는 작업
     * 한개의 데이터를 연결하는 작업을 수행하지만
     * RecyclerView에 의해서 데이터들의 개수만큼 반복적으로 호출되어
     * 화면에 데이터를 그리는 작업을 수행
     *
     * RecyclerView가 onBindViewHolder() method를 호출하면서
     * 몇번째 데이터를 Binding 할 것인지에 대한 index로 position 변수에
     * 값을 전달해 준다
     */
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        /**
         * 매개변수로 받은 holder를
         * 우리가 선언한 BookItemHolder로 형변환을 시키고 이후 코드를 진행한다
          */
        BookItemHolder bookHolder  = (BookItemHolder) holder;

        // 전체 데이터에서 그리고자 하는 데이터(한개)를 추출한다
        // 이때 매개변수로 전달받은 position을 사용하여 데이터를 getter 한다
        NaverBookDTO bookDTO = bookList.get(position);

        String item_title = bookDTO.getTitle();
        Spanned sp_title = Html.fromHtml(item_title,Html.FROM_HTML_MODE_LEGACY);
        bookHolder.item_title.setText( sp_title );

        String item_desc = bookDTO.getDescription();
        Spanned sp_desc = Html.fromHtml(item_desc, Html.FROM_HTML_MODE_LEGACY);
        bookHolder.item_desc.setText(sp_desc);

        /**
         * naver API를 통해서 전달받은 데이터 중 image 정보 표시하기
         * naver API에서는 도서에 대한 image를 이미지 주소(link)문자열로 보낸다
         * 
         * HTML에서는 단순히 img src="" 코드를 사용하여 연결하면
         * 자동으로 다운로드를 받으면서 이미지를 보여준다
         * 
         * 하지만 안드로이드에서는 디바이스 자체가 성능이 낮기 때문에
         * 직접 이미지를 다운로드 하거나 하면 문제를 일으킬수 있다
         * 
         * 안드로이드에서 이미지 링크를 실제 이미지로 보여준데
         * Picasso, Glide와 같은 3rd party라이브러리를 사용하여
         * 화면에 이미지를 구현한다
         *
         *          picasso              Glide
         *  속도    다소느림             다소빠름
         *  메모리  적게 소모            많이 소모
         *  기능     단순무식            다양한 기능 존재
         */
        if(!bookDTO.getImage().isEmpty()) {
            Picasso.get().load(bookDTO.getImage())
                    .into(bookHolder.item_image);
        }
    }

    @Override
    /**
     * onCreateViewHolder에서 생성된 holder를 사용하여
     * onBindViewHolder가 데이터 한개를 그리기를 수행하면
     * RecyclerView에게 지금 데이터가 몇개 인지 알려주고
     * 데이터 개수만큼 반복적으로 Holder를 그려라 라는 값을
     * 알려주는 method
     */
    public int getItemCount() {
        return bookList == null ?  0 : bookList.size();
    }

    /**
     * onCreateViewHolder() method가 사용하는 클래스
     * 실제 item.xml에 작성된 각각의 view component를
     * 실제적으로 사용할수 있도록 각각 개별 객체(view 객체)로
     * 생성하기 위한 보조 class
     * <p>
     * 초기에 RecyclerView인 ListView 시절에는
     * 선택사항으로 만들지 않아도 되었는데
     * RecyclerView에서는 반드시 있어야 하는 필수 클래스이다
     *
     * 우리가 작성할 코드는
     * item.xml 에 설정된 view Component 개별 요소를
     * 추출하여 각각 객체로 만드는 일
     * 
     * book_view_item.xml 에는
     *  TextView 가 있다
     *  이 TextView를 사용할수 있도록 객체로 만드는일
     *
     */
    public static class BookItemHolder extends RecyclerView.ViewHolder {
        public TextView item_title;
        public ImageView item_image;
        public TextView item_desc;

        public BookItemHolder(@NonNull View itemView) {
            super(itemView);
            item_title = itemView.findViewById(R.id.book_item_title);
            item_desc = itemView.findViewById(R.id.book_item_desc);
            item_image = itemView.findViewById(R.id.book_item_image);
        }
    }
}



