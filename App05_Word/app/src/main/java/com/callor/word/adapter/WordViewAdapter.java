package com.callor.word.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.word.R;
import com.callor.word.databinding.MyWordItemBinding;
import com.callor.word.model.WordDTO;

/**
 * RecyclerView.Adapter를 상속받은 Adapter를 만들지만
 * LiveData 와 연동하기 위하여 확장된 ListAdapter를
 * 상속받아 클래스를 선언한다
 *
 * ListAdapter는 RecyclerView를 확장한 클래스로
 * Adapter를 선언할때 상속을 받으면서
 * Generic으로 RecyclerView.ViewHolder와 함께
 * "DTO 클래스를 지정해 주어야 한다"
 *
 * getItems() method는 포함하지 않는다
 *
 * 자동으로 생성되는 생성자를 반드시 만들어 두어야 한다
 *
 */
public class WordViewAdapter
        extends ListAdapter<WordDTO,RecyclerView.ViewHolder> {

    protected WordViewAdapter(@NonNull DiffUtil.ItemCallback<WordDTO> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater
                = LayoutInflater.from(parent.getContext());
        MyWordItemBinding wordItemBinding
                = MyWordItemBinding
                .inflate(
                        layoutInflater,
                        parent,
                        false);
        return new WordViewHolder(wordItemBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        WordViewHolder wordHolder = (WordViewHolder) holder;
        wordHolder.itemBiding.itemKorea.setText("");
        wordHolder.itemBiding.itemKorea.setText("");
    }

    public static class WordViewHolder extends  RecyclerView.ViewHolder {

        public MyWordItemBinding itemBiding;
        public WordViewHolder(@NonNull MyWordItemBinding itemView) {
            super(itemView.getRoot());
            itemBiding = itemView;
        }
        public void bind(MyWordItemBinding wordItemBinding) {
            itemBiding = wordItemBinding;
        }
    }
}
