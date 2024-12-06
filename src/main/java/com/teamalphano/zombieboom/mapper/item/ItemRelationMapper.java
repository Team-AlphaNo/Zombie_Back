package com.teamalphano.zombieboom.mapper.item;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface  ItemRelationMapper {

    //prodNo로 아이템 연관 삭제
    int deleteItemRelationByProdNo(Integer prodNo);
}
