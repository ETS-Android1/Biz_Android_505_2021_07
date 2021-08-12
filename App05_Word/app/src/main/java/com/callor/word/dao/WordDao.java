package com.callor.word.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.callor.word.model.WordDTO;

import java.util.List;

@Dao
public interface WordDao {

    /**
     * 데이터를 selectAll() 하는 method return type을 다음과 같이 지정한다
     * LiveData<데이터리스트>
     *
     * 만약 selectAll() 을 수행한 데이터가 100개 정도 된다고 가정할때
     * 화면에 실제 보여지는 데이터는 10개 단위라고 하자
     *
     * List<DTO> 타입의 method를 사용하면 100개 데이터를 모두 읽어서
     * list에 담아서 메모리에 저장을 한다
     * 이는 메모리에 부담이 되고 디바이스 시스템에 무리를 줄수 도 있다
     *
     * 하지만 LiveDate로 감싼 리스트는 안드로드이드 OS 차원에서
     * 적절히 메모리를 분배하여 리스트를 관리해주는 효과 있다
     *
     * 리스트를 보고있는 동안에 새로은 데이터가 insert 되었다면
     * 일반적인 환경에서는 다시 selectAll() 수행하여 데이터를 가져온다
     *
     * LiveData로 감싼 리스트는 새로 추가된 데이터만 select하여
     * 기존의 list에 추가해 준다
     */
    @Query("SELECT * FROM tbl_word ORDER BY word")
    public LiveData<List<WordDTO>> selectAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(WordDTO wordDTO);

    @Query("DELETE FROM tbl_word")
    public void deleteAll(); // 초기화 시키기

}




