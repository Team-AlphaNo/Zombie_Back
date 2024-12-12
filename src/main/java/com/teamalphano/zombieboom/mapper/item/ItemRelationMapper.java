package com.teamalphano.zombieboom.mapper.item;

import com.teamalphano.zombieboom.model.item.ItemData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface  ItemRelationMapper {

    //prodNo로 아이템 연관 삭제
    int deleteItemRelationByProdNo(Integer prodNo);

}
